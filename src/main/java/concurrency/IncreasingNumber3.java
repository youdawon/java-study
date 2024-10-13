package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 실습 문제: ReentrantLock을 사용한 타임아웃 처리
 * 목표:
 * 두 개의 스레드가 동시에 하나의 공유 자원(변수)에 접근하려고 할 때, 일정 시간 동안만 락을 시도하고,
 * 그 시간이 지나면 다른 작업을 하도록 구현하세요.
 *
 * 조건:
 * ReentrantLock의 tryLock(long time, TimeUnit unit) 메서드를 사용하세요.
 * 한 스레드는 락을 획득하고 자원을 업데이트하지만, 다른 스레드는 일정 시간 동안 시도한 후,
 * 락을 얻지 못하면 "락 획득 실패" 메시지를 출력하고 종료합니다.
 * 락을 얻은 스레드는 1초 간격으로 공유 자원을 업데이트하고, 락을 얻지 못한 스레드는 2초 동안 대기한 후 종료합니다.
 */
public class IncreasingNumber3 {

	Lock lock = new ReentrantLock();
	int count = 0;
	int MAX_COUNT = 10;

	public IncreasingNumber3(){

	}

	public void increase(){
		try {
			if(lock.tryLock(1, TimeUnit.SECONDS)){
				try {
					while (count <= MAX_COUNT) {
						System.out.println(Thread.currentThread().getName() + " " + count++);
						Thread.sleep(1000);
					}
				} finally {
					lock.unlock();
				}

			}else{
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " 락 획득 실패");
			}
		}catch (InterruptedException ie){
			ie.printStackTrace();
		}
	}


	public static void main(String[] args) {

		IncreasingNumber3 increasingNumber = new IncreasingNumber3();

		Thread thread1 = new Thread(increasingNumber::increase);
		Thread thread2 = new Thread(increasingNumber::increase);

		thread1.start();
		thread2.start();
	}
}
