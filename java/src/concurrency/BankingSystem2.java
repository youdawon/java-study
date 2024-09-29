package concurrency;

/**
 *
 3. synchronized 메서드를 사용한 은행 계좌 관리
 목표: 여러 스레드가 동시에 하나의 은행 계좌에 대해 입금과 출금을 수행하도록 만드세요.
 조건:
 synchronized 메서드를 사용해 계좌의 잔고를 안전하게 관리하세요.
 여러 스레드가 동시에 출금과 입금을 수행할 수 있도록 하되, 동시에 발생하는 작업이 데이터에 문제를 일으키지 않도록 하세요.
 계좌의 초기 잔고는 1000, 각 스레드는 100번 입금 또는 출금을 하도록 설정하세요.
 */
public class BankingSystem2 {

	private int balance = 1000;

	private int getBalance(){
		return this.balance;
	}

	public synchronized void deposit(){
		for(int i=0; i<100; i++){
			this.balance++;
		}
	}

	public synchronized void credit(){
		for(int i=0; i<100; i++){
			this.balance--;
		}
	}

	public static void main(String[] args){
		BankingSystem2 bankingSystem = new BankingSystem2();

		Thread thread1 = new Thread(() -> bankingSystem.deposit());
		Thread thread2 = new Thread(() -> bankingSystem.credit());

		thread1.start();
		thread2.start();

		try{
			thread1.join();
			thread2.join();
		}catch (InterruptedException ie){
			ie.printStackTrace();
		}

		System.out.println(bankingSystem.getBalance());
	}
}
