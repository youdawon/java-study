package concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 3. 실습 문제 2: 은행 계좌 문제
 * 이제 동기화 문제를 해결하는 실습을 해볼 차례입니다.
 *
 * 문제: 두 개의 스레드를 만들어 같은 은행 계좌에서 돈을 인출하는 프로그램을 작성하세요.
 *
 * 각 스레드는 동시에 동일한 계좌에서 1씩 차감하는 작업을 100번 수행합니다.
 * 계좌 잔고가 음수가 되지 않도록 동기화를 사용하여 문제를 해결하세요.
 * 코드를 작성한 후, 질문이나 어려움이 있으면 알려주세요!
 */
public class BankingSystem implements Runnable{

	private int account = 1000;
	private ReentrantLock lock = new ReentrantLock();

	public  void depositAccount() {
		lock.lock();;
		this.account -= 1;
		try {
			Thread.sleep(10); // 인위적으로 지연을 추가
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			depositAccount();
			System.out.println(Thread.currentThread().getName() + " : " +this.account);
		}
	}

	public static void main(String[] args){
		BankingSystem bankingSystem = new BankingSystem();

		Thread thread1 = new Thread(bankingSystem);
		Thread thread2 = new Thread(bankingSystem);
		Thread thread3 = new Thread(bankingSystem);
		Thread thread4 = new Thread(bankingSystem);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
