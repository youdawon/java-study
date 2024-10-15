package concurrency;

/**
 * 문제 4: 안전한 종료
 * 무한 루프를 사용하는 스레드를 만들고, interrupt() 메서드를 이용하여 안전하게 종료하는 방법을 구현해 보세요.
 * 스레드는 Thread.sleep() 메서드로 일정 시간마다 대기하도록 하세요.
 */
public class Thread1 {


  public static void main(String[] args){

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (!Thread.currentThread().isInterrupted()) {
          try {
            System.out.println("run");
            Thread.sleep(500);
          } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            break;
          }
        }
      }
    });

    thread.start();

    try{
      Thread.sleep(2000);
    }catch (InterruptedException ie){
      ie.printStackTrace();
    }
    thread.interrupt();
  }
}
