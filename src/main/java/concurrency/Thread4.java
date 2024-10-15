package concurrency;

/**
 *
 문제 1: 스레드 생성 및 실행
 Runnable 인터페이스와 Thread 클래스를 사용하여 두 개의 스레드를 생성하세요.
 첫 번째 스레드는 1부터 5까지 숫자를 출력하고,
 두 번째 스레드는 5부터 1까지 숫자를 출력하도록 하세요. 두 스레드가 번갈아가며 숫자를 출력하도록 해보세요.
 */

class Test1 implements Runnable{
  public Object lock;

  public Test1(Object lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    synchronized (lock){
      for(int i=1; i<=5; i++){
        System.out.println(i);
        lock.notify();
        try {
          if (i < 5) {  // 마지막 반복에서는 wait()를 호출하지 않음
            lock.wait();  // 현재 스레드를 대기 상태로
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

class Test2 implements Runnable{
  public Object lock;

  public Test2(Object lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    synchronized (lock){
      for(int i=5; i>=1; i--){
          System.out.println(i);
          lock.notify();
          try {
            if (i > 1) {  // 마지막 반복에서는 wait()를 호출하지 않음
              lock.wait();  // 현재 스레드를 대기 상태로
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
    }
  }
}

public class Thread4 {

  public static void main(String[] args){

    Object lock = new Object();

   Thread thread1 = new Thread(new Test1(lock));
   Thread thread = new Thread(new Test2(lock));

   thread1.start();
   thread.start();
  }
}
