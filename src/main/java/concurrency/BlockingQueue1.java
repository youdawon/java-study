package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 2. BlockingQueue 문제
 * 다음과 같은 요구사항이 있습니다:
 *
 * BlockingQueue를 사용하여 생산자와 소비자 간의 통신을 구현하세요.
 * 생산자 스레드는 1부터 20까지의 정수를 생성하고 큐에 추가해야 하며, 소비자 스레드는 큐에서 값을 꺼내고 출력해야 합니다.
 * 소비자 스레드는 값을 꺼낼 때마다 1초 대기해야 합니다.
 */
public class BlockingQueue1 {

  static BlockingQueue blockingQueue = new ArrayBlockingQueue<>(5);

  public static void main(String[] args){

    Thread producer = new Thread(() -> {
      try {
        for(int i=1; i<=20; i++){
          blockingQueue.put(i);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread consumer = new Thread(() -> {
      try {
        for(int i=1; i<=20; i++){
          Thread.sleep(1000);
          System.out.println(blockingQueue.take());
        }
      }catch (InterruptedException ie){
        Thread.currentThread().interrupt();
      }
    });

    producer.start();
    consumer.start();
  }

}
