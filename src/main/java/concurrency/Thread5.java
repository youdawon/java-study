package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 문제 1: 스레드 풀을 사용하여 다중 계산 작업 처리
 * ExecutorService를 사용해 스레드 풀을 생성하고,
 * 세 개의 Callable 작업을 각각 제출하여 다음 계산 작업을 수행하세요.
 * 첫 번째 Callable: 1부터 50까지의 합을 계산합니다.
 * 두 번째 Callable: 51부터 100까지의 합을 계산합니다.
 * 세 번째 Callable: 두 합을 더하여 최종 합계를 반환합니다.
 * Future를 사용하여 각 작업의 결과를 받고, 최종 합계를 출력하세요.
 */
public class Thread5 {

  public static void main(String[] args){
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    Future<Integer> result1 = executorService.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {

        int sum = 0;

        for (int i = 0; i <= 50; i++) {
          sum += i;
        }

        return sum;
      };
    });

    Future<Integer> result2 = executorService.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int sum = 0;

        for(int i=51; i<=100; i++){
          sum += i;
        }

        return sum;
      }
    });

    Future<Integer> result3 = executorService.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        return result1.get() + result2.get();
      }
    });

    try {
      System.out.println(result3.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    executorService.shutdown();
  }
}
