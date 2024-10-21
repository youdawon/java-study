package concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 문제 1: 동기화된 카운터
 * 요구사항:
 *
 * SynchronizedCounter 클래스를 구현하세요. 이 클래스는 다음과 같은 메서드를 가져야 합니다:
 *
 * increment(): 카운터를 1 증가시킵니다.
 * getCount(): 현재 카운터 값을 반환합니다.
 * 여러 스레드를 생성하여 동시에 카운터를 증가시키는 시뮬레이션을 구현하세요.
 */
public class SynchronizedCounter {

  AtomicInteger number = new AtomicInteger(0);

  public void increment(){
    number.incrementAndGet();
  }

  public int getCount(){
    return number.get();
  }

  public static void main(String[] args){

    SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

    // 생산자 스레드 생성
    Thread producer1 = new Thread(() -> {
      for (int i = 1; i <= 10; i++) {
        synchronizedCounter.increment();
        try {
          Thread.sleep(50); // 잠시 대기하여 동시성 테스트
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    Thread producer2 = new Thread(() -> {
      for (int i = 11; i <= 20; i++) {
        System.out.println("getCount + " + synchronizedCounter.getCount());
        try {
          Thread.sleep(50); // 잠시 대기하여 동시성 테스트
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    // 소비자 스레드 생성
    Thread consumer1 = new Thread(() -> {
      for (int i = 1; i <= 10; i++) {
        synchronizedCounter.increment();
        try {
          Thread.sleep(100); // 잠시 대기하여 동시성 테스트
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    Thread consumer2 = new Thread(() -> {
      for (int i = 1; i <= 10; i++) {
        System.out.println("getCount + " + synchronizedCounter.getCount());
        try {
          Thread.sleep(100); // 잠시 대기하여 동시성 테스트
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    // 스레드 시작
    producer1.start();
    producer2.start();
    consumer1.start();
    consumer2.start();

    try {
      producer1.join();
      producer2.join();
      consumer1.join();
      consumer2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }


    System.out.println("Final count: " + synchronizedCounter.getCount());
    System.out.println("Simulation completed.");
  }

}
