package concurrency;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 문제: 멀티스레드 생산자-소비자 시뮬레이션
 * 요구사항:
 *
 * BlockingQueue를 사용하여 생산자와 소비자 간의 통신을 구현하세요.
 * 생산자 스레드는 1부터 50까지의 정수를 생성하여 큐에 추가해야 합니다.
 * 소비자 스레드는 큐에서 정수를 꺼내어 해당 정수를 제곱하여 ConcurrentHashMap에 저장해야 합니다.
 * 키는 정수, 값은 그 정수의 제곱입니다.
 * 모든 작업이 완료된 후, ConcurrentHashMap의 내용을 출력하세요.
 * 예시
 * 생산자: 1, 2, 3, ..., 50을 큐에 추가
 * 소비자: 큐에서 꺼낸 후 각 숫자의 제곱을 ConcurrentHashMap에 저장
 * 최종 출력: 1=1, 2=4, 3=9, ..., 50=2500
 * 추가 사항:
 * 생산자 스레드가 정수를 큐에 추가할 때는 put() 메서드를 사용하여 큐가 가득 찬 경우 대기하게 합니다.
 * 소비자 스레드는 take() 메서드를 사용하여 큐에서 값을 꺼내고, 큐가 비어있는 경우 대기하게 합니다.
 * 이 문제를 해결하면서 BlockingQueue와 ConcurrentHashMap의 동작 방식을 연습할 수 있습니다.
 * 코드를 작성한 후 결과를 공유해 주시면 피드백을 드리겠습니다!
 */
public class BlockingQueue2 {

  static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
  static Map<Integer, Integer> map = new ConcurrentHashMap<>();

  public static void main(String[] args) throws InterruptedException {

    Thread producer = new Thread(() -> {
      try {
        for (int i = 1; i <= 50; i++) {
          blockingQueue.put(i);
        }
      }catch (InterruptedException ie){
        Thread.currentThread().interrupt();
      }
    });

    Thread consumer = new Thread(() -> {
      try {
        for (int i = 1; i <= 50; i++) {
          int n = blockingQueue.take();
          map.put(n, n*n);
        }
      }catch (InterruptedException ie){
        Thread.currentThread().interrupt();
      }
    });

    producer.start();
    consumer.start();

    producer.join();
    consumer.join();

    map.forEach((a, b) ->
        System.out.println("Key: " + a + ", Value: " + b));
  }
}
