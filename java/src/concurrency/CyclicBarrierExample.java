package concurrency;

import java.util.concurrent.CyclicBarrier;

/***
 *
 2. CyclicBarrier
 학습 목표:
 CyclicBarrier를 사용하여 여러 스레드가 동시에 특정 지점에 도달했을 때 동기화하는 방법을 배웁니다.
 주로 스레드들이 같은 시점에서 협업해야 하는 상황에서 사용됩니다.
 심화 예제:
 3명의 작업자가 동시에 작업을 시작하고, 각 작업이 끝나면 모두가 바리어 지점에 도달한 후 다시 작업을 시작하는 구조로 만들어 보세요.
 */
public class CyclicBarrierExample {

	public static void main(String[] args){
		int countOfWorkers = 3;
		CyclicBarrier barrier = new CyclicBarrier(countOfWorkers, () -> System.out.println("jobs are finished"));

		for(int i=0; i<countOfWorkers; i++){
			new Thread(new WorkerTask(barrier)).start();
		}
	}

	static class WorkerTask implements Runnable {
		private CyclicBarrier barrier;

		public WorkerTask(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " is working");
				Thread.sleep(1000);  // 작업 수행
				System.out.println(Thread.currentThread().getName() + " reached the barrier");
				barrier.await();  // 바리어에 도착해서 대기
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
