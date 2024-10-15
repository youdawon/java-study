package concurrency;

/**
문제 2: 도서관 좌석 예약 시스템 - 타임아웃과 인터럽트 기능 활용
    문제: 도서관 좌석을 예약하는 시스템을 구현하세요. 특정 시간 동안 좌석을 예약하려고 시도하지만,
 시간이 지나면 포기하고 다른 좌석을 시도해야 합니다.
 또한, 사용자가 인터럽트를 요청하면 대기 중인 스레드가 예약을 중단합니다.

    요구사항
    SeatReservation 클래스를 작성하세요. 이 클래스는 다음 메서드를 포함합니다:

    reserveSeat(): 좌석을 예약하는 메서드로, 좌석이 이미 예약 중이라면 1초 동안 대기하며 락을 시도하고,
 획득하지 못하면 "Reservation failed" 메시지를 출력합니다.
    cancelReservation(): 좌석 예약을 취소합니다.
    ReentrantLock의 tryLock과 lockInterruptibly()를 활용:

    reserveSeat()에서 tryLock(1, TimeUnit.SECONDS)로 타임아웃을 설정하고, 예약에 실패하면 포기합니다.
    main 메서드에서 특정 스레드에 인터럽트를 걸어 예약 대기 중인 스레드가 안전하게 중단되도록 구현하세요.

 **/
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SeatReservation {
  private final ReentrantLock lock = new ReentrantLock();

  public void reserveSeat() {
    try {
      if(!lock.tryLock(1, TimeUnit.SECONDS)){
        System.out.println("Reservation failed");
        return;
      }
      System.out.println("Reservation successful");
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally{
      if(this.lock.isHeldByCurrentThread()){
        this.lock.unlock();
      }
    }
  }

  public void cancelReservation() {
    lock.lock();
    try {
      System.out.println("Reservation cancelled.");
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    SeatReservation reservation = new SeatReservation();

    Runnable reserver = () -> {
      while (true) {
        reservation.reserveSeat();
        try {
          Thread.sleep(500); // 예약 시도 간 간격
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          break;
        }
      }
    };

    Thread[] threads = new Thread[5];
    for (int i = 0; i < 5; i++) {
      threads[i] = new Thread(reserver);
      threads[i].start();
    }

    Thread.sleep(2000); // 2초 후 특정 스레드에 인터럽트를 발생시킴
    threads[0].interrupt();
  }
}