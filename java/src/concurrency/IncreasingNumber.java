package concurrency;

/**
 * 1. 동기화 블록을 사용한 멀티스레드 카운터
 * 목표: 두 개의 스레드가 동시에 하나의 공유 카운터 값을 증가시키는 프로그램을 작성하세요.
 * 조건:
 * 동기화 블록을 사용하여 스레드 안전성을 보장하세요.
 * 두 개의 스레드가 번갈아 가면서 카운터 값을 증가시킨 후, 최종 값을 출력하세요.
 * 카운터는 1000번씩 증가하도록 하세요.
 */
public class IncreasingNumber{

	private int number;

	public IncreasingNumber(){
		this.number = 0;
	}

	public void increase(){
		for(int i=0; i<1000; i++){
			synchronized(this) {
				this.number++;
			}
		}
	}

	public int getNumber(){
		return this.number;
	}

	public static void main(String[] args){
		IncreasingNumber increasingNumber = new IncreasingNumber();
		Thread thread1 = new Thread(() -> increasingNumber.increase());
		Thread thread2 = new Thread(() -> increasingNumber.increase());
		thread1.start();
		thread2.start();

		try{
			thread1.join();
			thread2.join();
		}catch (InterruptedException ie){
			ie.printStackTrace();
		}

		System.out.println(increasingNumber.getNumber());
	}
}
