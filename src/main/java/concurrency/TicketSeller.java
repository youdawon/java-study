package concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 좋습니다! 이번에는 ReentrantLock의 고급 기능을 활용한 문제를 내드리겠습니다.
 이 문제들은 타임아웃, 공정성, 인터럽트 가능성 등의 ReentrantLock 특징을 다루어,
 다양한 상황에서 ReentrantLock을 활용하는 연습이 될 것입니다.

 문제 1: 공정성(fairness) 설정된 티켓 판매 시스템
 문제: 공정성 모드를 활성화한 티켓 판매 시스템을 구현하세요.
 여러 스레드가 동시에 티켓을 구매할 때 구매 요청 순서대로 티켓을 구매하도록 합니다.

 요구사항
 TicketSeller 클래스를 구현하세요. 이 클래스는 다음 메서드를 포함합니다:
 sellTicket(): 티켓을 판매하며, 공정성을 보장하기 위해 대기 중인 스레드가 FIFO 순서로 티켓을 구매할 수 있어야 합니다.
 조건: 티켓 수는 50장으로 제한하며, 모든 티켓이 판매된 후에는 "Tickets sold out" 메시지를 출력합니다.
 공정성 모드 설정:
 ReentrantLock을 공정성 모드(true)로 생성해 스레드가 대기 순서대로 락을 획득할 수 있게 합니다.

 */
public class TicketSeller {

  private int tickets = 50;
  private final ReentrantLock lock = new ReentrantLock(true); // 공정 모드 활성화

  public void sellTicket() {
    lock.lock();
    try {
      // 작업 수행
      if (this.tickets <= 0) {
        System.out.println("Tickets sold out");
        return;
      }
      this.tickets--;
      System.out.println("remained tickets : " + this.tickets);
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    TicketSeller seller = new TicketSeller();
    Runnable task = () -> {
      while (true) {
        seller.sellTicket();
        try {
          Thread.sleep(100); // 티켓 구매 시도 간 간격
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    };

    // 10명의 스레드가 동시에 티켓을 구매
    for (int i = 0; i < 10; i++) {
      new Thread(task).start();
    }
  }
}
