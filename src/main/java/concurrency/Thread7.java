package concurrency;

/**
 * 문제 4: 안전한 스레드 종료
 * 무한 루프를 사용하는 스레드를 생성하고, Thread.sleep(500)으로 0.5초 간격마다 "Running..."을 출력하게 하세요.
 * 메인 스레드에서 2초 후 interrupt()를 호출하여 스레드를 안전하게 종료합니다.
 * 스레드가 종료되면 "Thread safely exited"를 출력하세요.
 */
public class Thread7 {

  public static void main(String[] args){

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Running...");
            Thread.sleep(500);
          }
        }catch (InterruptedException ie){
          Thread.currentThread().interrupt();
        }
      }
    });

    thread.start();

    try{
      Thread.sleep(2000);
      thread.interrupt();
      thread.join();
    }catch (InterruptedException ie){
      ie.printStackTrace();
    }

    System.out.println("Thread safely exited");
  }
}
