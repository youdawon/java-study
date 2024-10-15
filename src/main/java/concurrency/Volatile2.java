package concurrency;

/**
 *
 문제: 상태 플래그를 통한 스레드 종료 제어
 volatile을 사용하여 여러 스레드가 특정 상태 플래그를 확인해 종료될 수 있도록 간단한 프로그램을 작성해보세요.

 요구사항
 Worker 클래스를 만듭니다. 이 클래스에는 start()와 stop() 메서드가 있습니다.
 start() 메서드는 새로운 스레드를 시작해, 스레드가 주기적으로 "Working..."을 출력하게 합니다.
 stop() 메서드는 volatile 플래그를 false로 설정하여 스레드가 안전하게 종료되도록 합니다.
 */
public class Volatile2 {

  static class Worker{

    volatile boolean isWorking;

    public Worker() {
      this.isWorking = true;
    }

    public void start(){
        new Thread(() -> {
          while(this.isWorking){
            System.out.println("Working");
          }
      }).start();
    }

    public void stop(){
      this.isWorking = false;
    }
  }

  public static void main(String[] args) throws InterruptedException{
    Worker worker = new Worker();
    worker.start();

    Thread.sleep(2000); // 2초 동안 작업
    worker.stop(); // 안전하게 종료
  }
}
