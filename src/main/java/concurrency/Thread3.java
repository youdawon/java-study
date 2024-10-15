package concurrency;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 문제 4: Callable과 Future 사용하기
 * Callable을 사용하여 두 개의 계산 작업을 수행하는 스레드를 작성하세요.
 * 각 Callable은 1부터 100까지 숫자의 합을 구합니다.
 * ExecutorService와 Future를 사용하여 두 작업의 결과를 출력하세요.
 */
public class Thread3 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    Future<Integer> result = executorService.submit(new Callable<Integer>(){
      @Override
      public Integer call() throws Exception {
        int sum=0;
        for(int i=0; i<=100; i++){
          sum += i;
        }
        return sum;
      }

    });

    Future<Integer> result2 = executorService.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int sum=0;
        for(int i=0; i<=100; i++){
          sum += i;
        }
        return sum;
      }
    });

    System.out.println(result.get());
    System.out.println(result2.get());


    executorService.shutdown();

    if(!executorService.awaitTermination(5, TimeUnit.SECONDS)){
      executorService.shutdownNow();
    }
  }
}
