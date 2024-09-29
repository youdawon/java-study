package concurrency;

/**
 * 실습 1: 모니터 락(Monitor Lock)과 객체 동기화
 * 실습 목표: 두 개의 스레드가 동일한 객체에 접근할 때, 동기화를 사용하여 하나의 스레드만 작업을 완료한 후 다른 스레드가 실행되도록 하세요.
 * 실습 문제:
 * 두 개의 스레드가 동일한 메서드를 호출합니다.
 * 스레드가 동시에 실행되지 않도록 동기화하여 하나의 스레드만 실행 중일 때 다른 스레드는 대기하도록 하세요.
 */
public class MonitorLockExample {
	private final Object lock = new Object();  // 동기화에 사용할 객체

	public void execute() {
		synchronized (lock){
			System.out.println(Thread.currentThread().getName());
			try{
				Thread.sleep(1000);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				System.out.println("Hello");
			}
		};
		thread1.start();

		try {
			thread1.join();
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}

		System.out.println("1초 대기");

		Thread thread2 = new Thread(){
			@Override
			public void run() {
				System.out.println("world");
			}
		};
		thread2.start();
	}
}