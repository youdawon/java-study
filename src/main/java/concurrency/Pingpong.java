package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 문제 1: Ping-Pong 메시지 출력
 * 설명: 두 개의 스레드가 번갈아가며 "Ping"과 "Pong"을 출력하도록 합니다.
 * 요구사항:
 * 첫 번째 스레드는 "Ping"을 출력하고, 두 번째 스레드는 "Pong"을 출력합니다.
 * "Ping"과 "Pong"이 번갈아가며 출력되어야 합니다. 예를 들어,
 * 출력은 Ping-Pong-Ping-Pong 형태로 나타나야 합니다.
 */
public class Pingpong {

//  private static volatile boolean isPong = false;
//
//  public static void pingpong1(){
//
//    Runnable pingSystem = () -> {
//      System.out.println("ping");
//      isPong = true;
//    };
//
//    Runnable pongSystem = () -> {
//      while(!isPong){
//
//      }
//      System.out.println("pong");
//    };
//    pingSystem.run();
//    pongSystem.run();
//  }

//  private static boolean isPong = false;
//
//  public static void pingpong1(){
//
//    Runnable pingSystem = () -> {
//      System.out.println("ping");
//      isPong = true;
//    };
//
//    Runnable pongSystem = () -> {
//      while(!isPong){
//
//      }
//      System.out.println("pong");
//    };
//
//    Thread pingThread = new Thread(pingSystem);
//    Thread pongThread = new Thread(pongSystem);
//
//    pingThread.start();
//    pongThread.start();
//  }
//  private boolean pingong = false;
//
//  public synchronized void ping(){
//    while(pingong){
//      try {
//        wait();
//      }catch (InterruptedException ie){
//        ie.printStackTrace();
//      }
//    }
//    System.out.println("ping");
//    pingong = true;
//    notifyAll();
//  }
//
//  public synchronized void pong(){
//    while(!pingong){
//      try {
//        wait();
//      }catch (InterruptedException ie){
//        ie.printStackTrace();
//      }
//    }
//    System.out.println("pong");
//    pingong = false;
//    notifyAll();
//  }

//  Semaphore pingSemaphore;
//  Semaphore pongSemaphore;
//
//  public Pingpong(Semaphore ping, Semaphore pong) {
//    this.pingSemaphore = ping;
//    this.pongSemaphore = pong;
//  }
//
//  public void ping() {
//    try {
//      pingSemaphore.acquire(); // pong 세마포어를 얻기 전까지 대기
//      System.out.println("ping");
//      pongSemaphore.release(); // ping 세마포어를 열어 ping 메서드가 실행되도록 허용
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//  }
//
//  public void pong() {
//    try {
//      pongSemaphore.acquire(); // pong 세마포어를 얻기 전까지 대기
//      System.out.println("pong");
//      pingSemaphore.release(); // ping 세마포어를 열어 ping 메서드가 실행되도록 허용
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//  }

  private ReentrantLock lock;
  private boolean isPong;

  public Pingpong(){
    this.lock = new ReentrantLock();
    isPong = false;
  }

  public void ping(){
    while(!isPong){
      lock.lock();
    }
    System.out.println("ping");
  }

  public void pong(){
    System.out.println("ping");
    
  }

  public static void main(String[] args) throws Exception{
//    pingpong1();

//    Pingpong pingpong = new Pingpong();
//
//    Thread pingThread = new Thread(pingponge::ping);
//    Thread pongThread = new Thread(pingpong::pong);

//    Semaphore pingSemaphore = new Semaphore(1);
//    Semaphore pongSemaphore = new Semaphore(0);
//
//    Pingpong pingpong = new Pingpong(pingSemaphore, pongSemaphore);
//
//    Thread pingThread = new Thread(pingpong::ping);
//    Thread pongThread = new Thread(pingpong::pong);
//
//    pingThread.start();
//    pongThread.start();

    Pingpong pingpong = new Pingpong();

    Thread pingThread = new Thread(pingpong::ping);
    Thread pongThread = new Thread(pingpong::pong);

    pingThread.start();
    pongThread.start();

  }
}
