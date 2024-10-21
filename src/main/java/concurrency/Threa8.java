package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 좋아요! 다음 문제를 풀어보면서 멀티스레딩과 관련된 개념을 더 깊이 이해해보세요.

 문제: 스레드 간의 동기화
 요구사항:

 두 개의 스레드를 생성하여 실행하세요.
 첫 번째 스레드는 1부터 10까지의 정수를 계산하여 그 합을 출력합니다.
 두 번째 스레드는 11부터 20까지의 정수를 계산하여 그 합을 출력합니다.
 메인 스레드는 두 스레드가 모두 종료될 때까지 기다렸다가 최종적으로 두 스레드의 합을 합산하여 출력합니다.
 예시 출력:

 mathematica
 Copy code
 Thread 1 Sum: 55
 Thread 2 Sum: 155
 Total Sum: 210
 */
public class Threa8 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(2);

    Callable<Integer> thread1 = () -> {
      int sum = 0;
      for(int i=1; i<=10; i++){
        sum += i;
      }
      return sum;
    };

    Callable<Integer> thread2 = () -> {
      int sum = 0;
      for(int i=10; i<=20; i++){
        sum += i;
      }
      return sum;
    };

    Future<Integer> result1 = executor.submit(thread1);
    Future<Integer> result2 = executor.submit(thread2);

    System.out.println(result1.get() + result2.get());

    executor.shutdown();
  }

}
