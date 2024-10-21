package concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 3. CopyOnWriteArrayList 문제
 * 다음과 같은 요구사항이 있습니다:
 *
 * CopyOnWriteArrayList를 사용하여 멀티스레드 환경에서 안전하게 문자열을 추가하고 읽는 프로그램을 작성하세요.
 * 하나의 스레드는 리스트에 5개의 문자열을 추가하고, 다른 스레드는 리스트의 모든 문자열을 읽어 출력해야 합니다.
 * 추가할 문자열은 "A", "B", "C", "D", "E"입니다.
 * 이 문제들을 풀면서 각 컬렉션의 특징과 동작 방식을 이해할 수 있을 거예요. 해결이 어렵거나
 * 도움이 필요하면 언제든지 질문해 주세요!
 */
public class CopyOnWriteList1 {

  static List<Character> list = new CopyOnWriteArrayList<>();

  public static void main(String[] args) throws InterruptedException {

    Thread producer = new Thread(() -> {
      for(int i=0; i<5; i++){
        list.add((char) ('A' + i));
        System.out.println("Added: " + (char) ('A' + i));
      }
    });

    Thread consumer = new Thread(() -> {
      try {
        Thread.sleep(100); // 짧은 시간 대기 (생산자가 추가하는 동안 대기)
        for(int i=0; i<5; i++){
          System.out.println("Read: " + (char) ('A' + i));
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    producer.start();
    consumer.start();


    producer.join();
    consumer.join();
  }
}
