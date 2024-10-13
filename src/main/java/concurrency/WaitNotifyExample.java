package concurrency;


/**
 * public class WaitNotifyExample {
 * 	실습 2: wait()와 notify()를 사용한 스레드 간 통신
 * 	목표: 하나의 스레드가 작업을 완료한 후, 다른 스레드를 깨워서 작업을 이어받도록 만드세요.
 * 	조건: wait()는 스레드를 대기 상태로 만들고, notify()는 대기 중인 스레드를 깨웁니다. 두 스레드는 동기화된 자원을 공유하며,
 * 	하나의 스레드가 완료될 때까지 다른 스레드는 대기해야 합니다.
 * 	실습 문제:
 * 	하나의 스레드가 자원을 작업하는 동안 다른 스레드는 대기합니다.
 * 	첫 번째 스레드가 작업을 완료하면 notify()로 두 번째 스레드를 깨워, 작업을 이어받도록 하세요.
 */
public class                              WaitNotifyExample {
	private final Object lock = new Object();  // 동기화에 사용할 객체
	private boolean isDone = false;  // 작업 완료 여부를 나타내는 플래그

	public void produce() {
		synchronized (lock){
			System.out.println("produce");
			isDone = true;
			lock.notify();
		}
		// 동기화된 블록에서 작업 수행 후 notify 호출
	}

	public void consume() {
		// 동기화된 블록에서 wait를 사용해 대기 후 작업 수행
		synchronized (lock){
			while(!isDone) {
				try {
					lock.wait();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			System.out.println("consume");
		}
	}

	public static void main(String[] args) {
		WaitNotifyExample example = new WaitNotifyExample();

		Thread producer = new Thread(() -> example.produce());
		Thread consumer = new Thread(() -> example.consume());

		consumer.start();  // 소비자가 먼저 시작하여 대기 상태로 진입
		producer.start();  // 생산자가 이후 작업을 완료한 후 소비자를 깨움
	}
}
