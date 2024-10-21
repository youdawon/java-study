package concurrency;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 좋습니다! 다음은 다양한 주제를 포함한 몇 가지 문제입니다.
 *
 * 문제 1: 동시성 안전한 스택 구현
 * 요구사항:
 *
 * ConcurrentStack 클래스를 구현하세요. 이 클래스는 다음과 같은 메서드를 가져야 합니다:
 * push(T item): 스택에 아이템을 추가합니다.
 * T pop(): 스택에서 아이템을 제거하고 반환합니다.
 * boolean isEmpty(): 스택이 비어있는지 확인합니다.
 * 여러 스레드를 생성하여 동시에 아이템을 추가하고 제거하는 시뮬레이션을 구현하세요.
 */
public class ConcurrentStack<T> {

  Stack<T> stack = new Stack<>();
  ReentrantLock reentrantLock = new ReentrantLock();

  public void push(T item) {
    try {
      reentrantLock.lock();
      stack.push(item);
      System.out.println("push : " + item);
    } finally {
      reentrantLock.unlock();
    }
  }

  public T pop() {
    try{
      if (stack.isEmpty()) {
        throw new EmptyStackException();
      }
      T item = stack.pop();
      System.out.println("Popped: " + item);
      return item;
    } finally{
      reentrantLock.unlock();
    }
  }

  public boolean isEmpty(){
    try {
      reentrantLock.lock();
      return stack.isEmpty();
    } finally {
      reentrantLock.unlock();
    }
  }


  public static void main(String[] args) {
    ConcurrentStack<Integer> stack = new ConcurrentStack<>();

    // 생산자 스레드 생성
    Thread producer1 = new Thread(() -> {
      for (int i = 1; i <= 10; i++) {
        stack.push(i);
        try {
          Thread.sleep(50); // 잠시 대기하여 동시성 테스트
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    Thread producer2 = new Thread(() -> {
      for (int i = 11; i <= 20; i++) {
        stack.push(i);
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
        stack.pop();
        try {
          Thread.sleep(100); // 잠시 대기하여 동시성 테스트
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    Thread consumer2 = new Thread(() -> {
      for (int i = 1; i <= 10; i++) {
        stack.pop();
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

    // 스레드가 종료될 때까지 대기
    try {
      producer1.join();
      producer2.join();
      consumer1.join();
      consumer2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("Simulation completed.");
  }
}