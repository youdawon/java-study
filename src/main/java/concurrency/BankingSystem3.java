package concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 문제:
 * 한 은행 시스템에서는 고객의 계좌 잔액을 관리해야 합니다.
 * 각 고객은 여러 계좌를 가질 수 있으며, 특정 상황에서는 계좌 간 자금 이체가 필요합니다.
 *
 * 은행 시스템은 아래와 같은 기능이 필요합니다:
 *
 * 요구사항:
 * 잔액 확인 기능 (getBalance):
 *
 * 주어진 계좌 ID에 대해 잔액을 반환합니다.
 * 여러 스레드가 동시에 잔액을 조회할 수 있어야 합니다.
 * 입금 기능 (deposit):
 *
 * 주어진 계좌 ID에 특정 금액을 입금합니다.
 * 입금 작업이 수행되는 동안 다른 쓰기 작업이 동시에 발생하지 않도록 해야 합니다.
 * 출금 기능 (withdraw):
 *
 * 주어진 계좌 ID에서 특정 금액을 출금합니다.
 * 계좌에 충분한 잔액이 있는 경우에만 출금을 진행합니다.
 * 출금 작업이 수행되는 동안 다른 쓰기 작업이 동시에 발생하지 않도록 해야 합니다.
 * 이체 기능 (transfer):
 *
 * 주어진 두 계좌 ID 사이에 자금을 이체합니다.
 * 이체는 원자적으로 이루어져야 하며, 두 계좌 간의 잔액 수정 작업이 동시에 발생하지 않도록 해야 합니다.
 * 이체 작업 중에 두 계좌의 잔액이 동시에 잠겨 있어야 합니다.
 * 클래스 설계:
 *
 * ReadWriteLock을 사용하여 동시성을 제어하세요.
 * 계좌 데이터를 저장할 컬렉션은 Map<String, Integer> 형태로 사용하세요 (String은 계좌 ID, Integer는 잔액).
 * 추가 도전 (선택 사항):
 *
 * transfer 메서드에서 **교착 상태(Deadlock)**를 방지하도록 구현하세요.
 * 두 계좌가 이미 다른 스레드에 의해 잠금이 걸렸다면, 이체 작업이 타임아웃되도록 설정해 보세요.
 */
public class BankingSystem3 {

  Map<String, Integer> accounts = new ConcurrentHashMap<>();
  ReadWriteLock lock = new ReentrantReadWriteLock();

  public int getBalance(String accountId){
    try {
      lock.readLock().lock();

      return accounts.getOrDefault(accountId, 0);
    }finally {
      lock.readLock().unlock();
    }
  }

  public void withdraw(String accountId, int amount){
    try{
      lock.writeLock().lock();
      accounts.put(accountId, accounts.getOrDefault(accountId, 0)-amount);
    }finally {
      lock.writeLock().unlock();
    }
  }

  public void deposit(String accountId, int amount){
    try{
      lock.writeLock().lock();
      accounts.put(accountId, accounts.getOrDefault(accountId, 0)+amount);
    }finally {
      lock.writeLock().unlock();
    }
  }

  public void transfer(String from, String to, int amount){

    try {
      if (!lock.writeLock().tryLock(1, TimeUnit.SECONDS)) {
        return;
      }

      try {
        accounts.put(from, accounts.get(from) - amount);
        accounts.put(to, accounts.get(to) + amount);
      } finally {
          lock.writeLock().unlock();
        }
    } catch (InterruptedException ie){
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    // 은행 시스템 객체 생성
    BankingSystem3 bankSystem = new BankingSystem3();

    // 초기 계좌 생성 및 잔액 설정
    bankSystem.deposit("account1", 1000); // 계좌1에 1000 입금
    bankSystem.deposit("account2", 2000); // 계좌2에 2000 입금

    // 스레드 풀 생성
    ExecutorService executor = Executors.newFixedThreadPool(5);

    // 입금 테스트 스레드
    executor.submit(() -> {
      bankSystem.deposit("account1", 500);
      System.out.println("Deposited 500 to account1, new balance: " + bankSystem.getBalance("account1"));
    });

    // 출금 테스트 스레드
    executor.submit(() -> {
      bankSystem.withdraw("account2", 300);
      System.out.println("Withdrew 300 from account2, new balance: " + bankSystem.getBalance("account2"));
    });

    // 이체 테스트 스레드
    executor.submit(() -> {
      bankSystem.transfer("account1", "account2", 200);
      System.out.println("Transferred 200 from account1 to account2");
      System.out.println("account1 balance after transfer: " + bankSystem.getBalance("account1"));
      System.out.println("account2 balance after transfer: " + bankSystem.getBalance("account2"));
    });

    // 추가 입금 테스트 스레드
    executor.submit(() -> {
      bankSystem.deposit("account2", 400);
      System.out.println("Deposited 400 to account2, new balance: " + bankSystem.getBalance("account2"));
    });

    // 추가 출금 테스트 스레드
    executor.submit(() -> {
      bankSystem.withdraw("account1", 100);
      System.out.println("Withdrew 100 from account1, new balance: " + bankSystem.getBalance("account1"));
    });

    // ExecutorService 종료
    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);
    System.out.println("All tasks completed.");
  }

}
