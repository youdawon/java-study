package concurrency;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 다음 실습은 **동적 스레드 풀(Cached Thread Pool)**을 사용하는 방법에 대한 것입니다.
 Cached Thread Pool은 필요에 따라 스레드를 동적으로 생성하고, 사용하지 않으면 제거하는 방식으로 동작합니다.
 이번 실습을 통해 동적으로 스레드를 생성하는 방법과 그 활용 방식을 배울 수 있습니다.

 실습 문제:
 **동적 스레드 풀(Cached Thread Pool)**을 사용하여 5개의 작업을 처리하는 프로그램을 작성하세요.
 각 작업은 2초 동안 실행되며, 작업의 시작 시간과 완료 시간을 출력하세요.
 스레드 풀이 동적으로 스레드를 생성하는 것을 확인할 수 있도록, 로그를 통해 스레드 생성 및 작업 완료를 확인하세요.
 */
public class DynamicThreadPool {

	public static void main(String[] args){

		ExecutorService executor = Executors.newCachedThreadPool();

		for(int i=0;  i<5; i++) {
			Runnable thread = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " started : " + LocalTime.now());
					try{
						Thread.sleep(2000);
					}catch (Exception e){
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " finished : " + LocalTime.now());
				}
			};
			executor.execute(thread);

			ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executor;
			System.out.println("Active thread : " + poolExecutor.getActiveCount());
		}

		executor.shutdown();
		while(!executor.isTerminated()){

		}
		System.out.println("job finished!!");

	}
}
