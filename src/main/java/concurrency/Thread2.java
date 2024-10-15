package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
ExecutorService를 사용하여 10개의 작업을 처리하는 스레드 풀을 생성하세요.
각 작업은 "Task X is running"을 출력하고, X는 작업의 번호로 1부터 10까지입니다.
모든 작업이 완료되면 스레드 풀을 종료하도록 하세요.

 */
public class Thread2 {

  public static void main(String[] args){
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    for(int i=0; i<10; i++){
      final int index = i;
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println("Task " + index + " is running.");
        }
      });
    }

    executorService.shutdown();
    try{
      if(!executorService.awaitTermination(5, TimeUnit.SECONDS)){
        executorService.shutdownNow();
      }
    }catch (InterruptedException ie){
      ie.printStackTrace();
      executorService.shutdownNow();
    }
  }
}
