package concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 실습 예제
 * 우리는 3개의 스레드를 만들고, Semaphore를 통해 동시에 2개의 스레드만 자원에 접근할 수 있도록 제어해볼 것입니다.
 * 나머지 스레드는 대기합니다. 자원에 접근한 스레드는 1초간 작업을 수행한 후 자원을 반환하게 됩니다.
 *
 * 실습 문제:
 * Semaphore를 사용하여 2개의 스레드만 동시에 자원에 접근할 수 있도록 제어하세요.
 * 각 스레드는 자원을 1초 동안 사용하고, 사용이 끝나면 자원을 반환합니다.
 * 자원을 사용할 수 없는 스레드는 자원이 반환될 때까지 대기합니다.
 */
public class SemaphoreExample {
	public static void main(String[] args){
		Semaphore semaphore = new Semaphore(2);

		// 3개의 스레드 생성
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new WorkerTask(semaphore), "Worker-" + i);
			thread.start();
		}

	}

	static class WorkerTask implements Runnable {

		private Semaphore semaphore;

		public WorkerTask(Semaphore semaphore){
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				this.semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + " is working");
				Thread.sleep(1000);  // 작업 수행
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				this.semaphore.release();
				System.out.println(Thread.currentThread().getName() + " released the shmaphore.");
			}
		}
	}
}
