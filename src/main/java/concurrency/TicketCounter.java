package concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 문제: 스레드 안전한 티켓 판매 시스템 만들기
 * 다중 스레드 환경에서 티켓을 판매하는 간단한 시스템을 만들어 보세요.
 * ReentrantLock을 사용해, 여러 스레드가 동시에 접근하더라도 티켓이 정확하게 판매될 수 있도록 하세요.
 *
 * 요구사항
 * TicketCounter 클래스를 구현하세요. 이 클래스에는 다음과 같은 메서드가 있습니다:
 *
 * sellTicket(): 티켓을 판매합니다. 티켓이 남아 있는 경우, 한 장을 판매하고 남은 티켓 수를 출력합니다.
 * 조건: 판매 가능한 티켓 수는 100장으로 제한됩니다. 티켓이 매진되면 "Tickets sold out"을 출력합니다.
 * ReentrantLock 사용: sellTicket() 메서드가 여러 스레드에 의해 동시에 호출되더라도 정확하게
 * 티켓 수가 감소하도록 하세요.
 *
 * main 메서드에서는 10개의 스레드를 생성하고, 각각 20장의 티켓을 판매하도록 합니다.
 *
 * 구현 예시
 * 아래 코드의 sellTicket() 메서드를 구현해 보세요.
 */
public class TicketCounter {

  private int tickets = 100;  // 초기 티켓 수
  private final ReentrantLock lock = new ReentrantLock();

  public void sellTicket() {
    try{
      lock.lock();
      if(this.tickets <= 0){
        System.out.println("Tickets sold out");
        return;
      }
      this.tickets--;
      System.out.println("remained tickets  : " + this.tickets);
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    TicketCounter counter = new TicketCounter();
    Thread[] threads = new Thread[10];

    for (int i = 0; i < 10; i++) {
      threads[i] = new Thread(() -> {
        for (int j = 0; j < 20; j++) {
          counter.sellTicket();
        }
      });
      threads[i].start();
    }

    for (Thread t : threads) {
      t.join();
    }

    System.out.println("Ticket selling process completed.");
  }
}