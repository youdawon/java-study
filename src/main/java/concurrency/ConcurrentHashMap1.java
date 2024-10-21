package concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. ConcurrentHashMap 문제
 * 다음과 같은 요구사항이 있습니다:
 *
 * ConcurrentHashMap을 사용하여 1부터 100까지의 정수의 제곱을 저장하는 프로그램을 작성하세요.
 * 두 개의 스레드가 각각 홀수와 짝수의 제곱을 계산하고 맵에 저장해야 합니다.
 * 결과는 Key: 값 형식으로 출력되어야 합니다.
 */
public class ConcurrentHashMap1 {

  static Map<Integer, Integer> map = new ConcurrentHashMap<>();

  public static void main(String[] args) throws InterruptedException {

    Thread first = new Thread(() -> {
      for(int i=1; i<=100; i++){
        if(i % 2 == 0){
          map.put(i, i*i);
        }
      }
    });

    Thread second = new Thread(() -> {
      for(int i=1; i<=100; i++){
        if(i % 2 != 0){
          map.put(i, i*i);
        }
      }

    });

    first.start();
    second.start();

    first.join();
    second.join();

    map.forEach((a, b) -> {
      System.out.println("Key : " + a + " value : " + b);
    });
  }
}
