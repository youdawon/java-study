package concurrency.effectivejava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EffectiveJava81_3 {

  public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException{

    CountDownLatch ready = new CountDownLatch(concurrency);
    CountDownLatch start = new CountDownLatch(1);
    CountDownLatch done = new CountDownLatch(concurrency);

    for(int i=0; i<concurrency; i++){
      executor.execute(() -> {
        ready.countDown();

        try{
          start.await();
          action.run();

        }catch (InterruptedException e){
          Thread.currentThread().interrupt();
        } finally {
          done.countDown();
        }
      });
    }
    ready.await();
    long startNanos = System.nanoTime();
    start.countDown();
    done.await();
    return System.nanoTime() - startNanos;
  }

  public static void main(String[] args) throws Exception{
    Executor executor = Executors.newFixedThreadPool(3); // Create a thread pool executor
    Runnable action = () -> {
      System.out.println(Thread.currentThread().getName() + " doing work");
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        System.out.println(Thread.currentThread().getName() + " finished");
      };
    };

    long elapsedTime = time(executor, 3, action); // Call the time method
    System.out.println("Elapsed time: " + elapsedTime + " nanoseconds");

    // Shutdown the executor
    if (executor instanceof ExecutorService) {
      ((ExecutorService) executor).shutdown();
    }
  }
}
