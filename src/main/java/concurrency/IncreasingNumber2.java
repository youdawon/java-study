package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 실습 문제 1: ReentrantLock을 사용한 동기화 처리
 목표:
 두 개의 스레드가 동시에 하나의 공유 자원(변수)을 증가시키는 프로그램을 작성하세요.

 조건:
 ReentrantLock을 사용하여 스레드 안전성을 보장하세요.
 공평한 락을 사용해 두 스레드가 번갈아 가며 작업하도록 만드세요.
 1초마다 변수 값을 출력하세요.
 **/

public class IncreasingNumber2 {

	Lock lock = new ReentrantLock(true);
	private int count = 0;
	private static final int MAX_COUNT = 10;


	public IncreasingNumber2(){

	}

	public void increase() {
		while (true) {
			lock.lock();  // 락 획득
			try {
				if (count >= MAX_COUNT) {
					break;  // 최대 카운트에 도달하면 루프 종료
				}
				System.out.println(Thread.currentThread().getName() + " 작업 시작");
				// 임계 영역 코드
				count++;
				System.out.println(Thread.currentThread().getName() + " 작업 완료");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();  // 락 해제
			}
		}
	}

	public static void main(String[] args) {

		IncreasingNumber2 increasingNumber = new IncreasingNumber2();

		Thread thread1 = new Thread(increasingNumber::increase);
		Thread thread2 = new Thread(increasingNumber::increase);

		thread1.start();
		thread2.start();
	}
}
