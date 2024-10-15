package concurrency;

/**
 *
 문제 2: 두 개의 스레드가 번갈아 출력하는 프로그램
 첫 번째 스레드는 1부터 3까지 숫자를 출력하고, 두 번째 스레드는 "A", "B", "C"를 순서대로 출력하는 프로그램을 작성하세요.
 두 스레드가 번갈아가며 숫자와 문자를 출력할 수 있도록 wait()와 notify()를 사용하여 동기화하세요.
 */

class FirstThread implements Runnable{

  private Object lock;

  public FirstThread(Object lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    synchronized (lock){
      try {
        for(int i=1; i<=3; i++) {
          System.out.println(i);
          lock.notify();
          if (i < 3) {
            lock.wait();
          }
        }
      }catch(InterruptedException ie){
        ie.printStackTrace();
      }
    }
  }
}

class SecondClass implements Runnable{

  private Object lock;

  public SecondClass(Object lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    synchronized (lock){
      try {
        for(int i=0; i<3; i++) {
          System.out.println((char)('A'+i));
          lock.notify();
          if (i < 2) {
            lock.wait();
          }
        }
      }catch(InterruptedException ie){
        ie.printStackTrace();
      }
    }
  }
}

public class Thread6 {

  public static void main(String[] arg){

    Object lock = new Object();

    Thread thread1 = new Thread(new FirstThread(lock));
    Thread thread2 = new Thread(new SecondClass(lock));



    thread1.start();
    thread2.start();
  }


}
