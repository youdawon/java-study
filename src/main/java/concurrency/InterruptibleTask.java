package concurrency;
/**
문제 2: 인터럽트 가능한 대기 메서드 사용하기
    문제: 스레드가 대기 중 인터럽트를 통해 작업을 중단할 수 있도록 ReentrantLock의
 lockInterruptibly() 메서드를 활용한 프로그램을 작성하세요.

    요구사항
    InterruptibleTask 클래스를 작성하세요. 이 클래스에는 다음 메서드가 포함됩니다:
    performTask(): 인터럽트 가능한 방식으로 작업을 수행합니다.
 작업 중에 다른 스레드에 의해 인터럽트가 발생하면 "Task was interrupted" 메시지를 출력하고 중단합니다.
    인터럽트 발생:
    main 메서드에서 5개의 스레드를 생성하고 각 스레드가 performTask()를 호출하도록 합니다.
 2초 후 하나의 스레드에 인터럽트를 걸어, 작업을 중단시키세요.
    구현 예시
    InterruptibleTask 클래스의 performTask() 메서드를 작성해 보세요.

 */


import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleTask {
  private final ReentrantLock lock = new ReentrantLock();

  public void performTask() {

    try{
      lock.lockInterruptibly();
    }catch (InterruptedException ie){
      System.out.println("Task was interrupted");
      Thread.currentThread().interrupt();
    }finally {
      if(lock.isHeldByCurrentThread()){
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    InterruptibleTask task = new InterruptibleTask();
    Thread[] threads = new Thread[5];

    for (int i = 0; i < 5; i++) {
      threads[i] = new Thread(task::performTask);
      threads[i].start();
    }

    Thread.sleep(2000); // 2초 후에 인터럽트를 발생시킴
    threads[0].interrupt();
  }
}