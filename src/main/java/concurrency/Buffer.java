package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;



/**
 * 문제: 스레드 안전한 생산자-소비자 시스템 만들기
 * 생산자-소비자 패턴을 구현하여 생산자 스레드가 상품을 생성하고,
 * 소비자 스레드가 이를 소비하는 스레드 안전한 시스템을 만들어 보세요.
 * 이 시스템에서는 ReentrantLock과 Condition을 사용해 동기화합니다.
 *
 * 요구사항
 * Buffer 클래스를 구현하세요. 이 클래스는 **고정된 크기의 버퍼(큐)**로, 다음 메서드를 포함합니다.
 *
 * produce(int item): 버퍼에 상품을 추가하는 메서드입니다. 버퍼가 가득 차면, 공간이 생길 때까지 대기합니다.
 * consume(): 버퍼에서 상품을 꺼내 소비하는 메서드입니다. 버퍼가 비어 있으면, 상품이 추가될 때까지 대기합니다.
 * ReentrantLock 및 Condition 사용:
 *
 * 생산자와 소비자는 각각 **다른 조건(Condition)**을 사용하여 필요한 상태(가득 참 또는 비어 있음)를 기다립니다.
 * main 메서드에서 2개의 생산자 스레드와 2개의 소비자 스레드를 생성해,
 * 각 스레드가 무한 루프로 produce()와 consume()을 호출하도록 합니다.
 *
 * 구현 예시
 * 아래 코드를 참고하여 Buffer 클래스의 produce()와 consume() 메서드를 구현해 보세요.
 */
public class Buffer {
  private final Queue<Integer> queue = new LinkedList<>();
  private final int capacity = 10;
  private final ReentrantLock lock = new ReentrantLock();
  private final Condition notFull = lock.newCondition();
  private final Condition notEmpty = lock.newCondition();

  public void produce(int item) throws InterruptedException {
    lock.lock();
    try  {
      while(this.queue.size() == this.capacity){
        notFull.await();
      }
      System.out.println("Insert " + item);
      queue.add(item);
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public int consume() throws InterruptedException {
    lock.lock();
    try {
      while(this.queue.size() == 0){
        notEmpty.await();
      }
      int item = queue.poll();
      System.out.println("Remove " + item);
      notFull.signal();
      return item;
    } finally {
      lock.unlock();
     }
  }

  public static void main(String[] args) {
    Buffer buffer = new Buffer();

    Runnable producer = () -> {
      try {
        int item = 0;
        while (true) {
          buffer.produce(item++);
          Thread.sleep(500);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    Runnable consumer = () -> {
      try {
        while (true) {
          buffer.consume();
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    for (int i = 0; i < 2; i++) {
      new Thread(producer).start();
      new Thread(consumer).start();
    }
  }
}