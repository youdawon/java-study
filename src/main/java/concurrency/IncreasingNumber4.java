package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 5. Atomic 클래스 실습:
 * 문제: AtomicInteger를 사용한 원자적 연산 구현
 * 두 개의 스레드가 동시에 **AtomicInteger**를 사용하여 값을 1씩 증가시키는 프로그램을 작성하세요.
 * 스레드가 번갈아가며 값을 증가시키고, 최종 값을 출력하도록 만드세요.
 * 이 문제를 직접 풀어보시고, 궁금한 점이 있으면 알려주세요!
 */
public class IncreasingNumber4 {

	private final AtomicInteger number;

	public IncreasingNumber4(){
		this.number = new AtomicInteger(0);
	}

	public void increase(){
		this.number.incrementAndGet();
	}

	public int getNumber(){
		return this.number.intValue();
	}

	public static void main(String[] args) {

		IncreasingNumber4 increasingNumber = new IncreasingNumber4();

		Thread thread1 = new Thread(increasingNumber::increase);
		Thread thread2 = new Thread(increasingNumber::increase);

		thread1.start();
		thread2.start();

		try{
			thread1.join();
			thread2.join();

		}catch (Exception e){
			e.printStackTrace();
		}

		System.out.println(increasingNumber.getNumber());
	}
}
