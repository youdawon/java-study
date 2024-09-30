package concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 실습 문제:
 * ScheduledThreadPool을 사용하여 3초 후에 실행되는 작업을 작성하세요.
 * 각 작업은 1초 동안 실행되며, 작업의 시작 시간과 완료 시간을 출력하세요.
 * 주기적으로 5초 간격으로 반복 실행되도록 작업을 스케줄링하세요.
 */
public class ScheduledThreadPool {

	public static void main(String[] args){
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

		Runnable thread1 = new Runnable() {
			@Override
			public void run() {
				try{
					System.out.println(Thread.currentThread().getName() + " started");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + " ended");
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		};
		scheduler.schedule(thread1, 3, TimeUnit.SECONDS);

		Runnable thread2 = new Runnable() {
			@Override
			public void run() {
				try{
					System.out.println(Thread.currentThread().getName() + " started");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + " ended");
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		};
		scheduler.scheduleAtFixedRate(thread2, 3, 5, TimeUnit.SECONDS);

		scheduler.schedule(() -> scheduler.shutdown(), 20, TimeUnit.SECONDS);
	}
}
