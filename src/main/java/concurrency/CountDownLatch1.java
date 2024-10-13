package concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch1 {

	public static void main(String[] args) throws InterruptedException{
		int numberOfWorkers = 3;
		CountDownLatch countDownLatch = new CountDownLatch(3);

		for (int i = 0; i < numberOfWorkers; i++) {
			Runnable thread1 = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " doing work");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						System.out.println(Thread.currentThread().getName() + " finished");
						countDownLatch.countDown();
					}
				}
			};

			new Thread(thread1).start();
		}

		countDownLatch.await();
		System.out.println("All workers have finished their tasks! Main task starting..");
	}
}
