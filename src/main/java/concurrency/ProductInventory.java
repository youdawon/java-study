package concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 좋아요! `ReadWriteLock`과 관련된 문제를 만들어 볼게요.
 *
 * ### 문제:
 * 아래와 같은 시나리오가 있다고 가정해 보세요.
 *
 * 한 대형 전자상거래 시스템에서 **상품 재고 데이터**를 관리하는 클래스를 만들려고 합니다.
 * 여러 스레드가 **상품 재고 수량을 조회**할 수 있고, **관리자 스레드는 재고를 업데이트**할 수 있습니다.
 * 여기서 **조회 작업이 압도적으로 많고**, **재고 업데이트는 가끔** 이루어집니다.
 *
 * 이를 해결하기 위해 `ReadWriteLock`을 사용하여 다음 요구사항을 만족하는 클래스를 만들어보세요.
 *
 * ### 요구사항:
 * 1. **상품 재고 조회** 기능 (`getStock` 메서드):
 *    - 주어진 상품 ID에 대한 재고를 반환합니다.
 *    - 다수의 스레드가 동시에 조회할 수 있어야 합니다.
 *
 * 2. **재고 업데이트** 기능 (`updateStock` 메서드):
 *    - 주어진 상품 ID에 대해 재고 수량을 변경합니다.
 *    - 재고를 변경하는 동안 다른 스레드가 해당 상품의 재고를 조회할 수 없도록 해야 합니다.
 *
 * 3. **클래스 설계**:
 *    - `ReadWriteLock`을 이용하여 동시성을 제어하세요.
 *    - 재고 데이터를 저장할 컬렉션은 `Map<String, Integer>`
 *    형태로 사용하세요 (`String`은 상품 ID, `Integer`는 재고 수량).
 *
 * ### 추가 도전 (선택 사항):
 * - 만약 상품 ID가 존재하지 않는 경우, `getStock` 메서드가 기본값 `0`을 반환하도록 만들어 보세요.
 *
 * 이 문제를 풀어보면서 `ReadWriteLock`의 읽기 잠금과 쓰기 잠금을 어떻게 사용하는지 연습해 보세요.
 */
public class ProductInventory {

  Map<String, Integer> inventories = new ConcurrentHashMap<>();
  ReadWriteLock lock = new ReentrantReadWriteLock();

  public int getStock(String id) {
    lock.readLock().lock(); // 읽기 잠금
    try {
      return inventories.getOrDefault(id, 0);
    } finally {
      lock.readLock().unlock();
    }
  }

  public void updateStock(String id, int amount) {

    try {

      lock.writeLock().lock();

      inventories.put(id, inventories.getOrDefault(id, 0) + amount);
    } finally {
      lock.writeLock().unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {

    // Inventory 클래스를 생성
    ProductInventory inventory = new ProductInventory();

    // 초기 재고 설정
    inventory.updateStock("item1", 100);

    // 스레드 풀 생성 (읽기 및 쓰기 작업을 동시에 시도할 수 있도록)
    ExecutorService executor = Executors.newFixedThreadPool(5);

    // 다수의 읽기 스레드 생성
    for (int i = 0; i < 3; i++) {
      executor.submit(() -> {
        System.out.println("Reading stock for item1: " + inventory.getStock("item1"));
      });
    }

    // 쓰기 스레드 생성 (재고 업데이트)
    executor.submit(() -> {
      System.out.println("Updating stock for item1 to 80.");
      inventory.updateStock("item1", 80);
    });

    // 또 다른 읽기 스레드 생성
    executor.submit(() -> {
      System.out.println("Reading stock for item1 after update: " + inventory.getStock("item1"));
    });

    // ExecutorService 종료
    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);
    System.out.println("All tasks completed.");
  }
}