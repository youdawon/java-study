package concurrency;

import java.time.LocalTime;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 실습 문제:
 * 고정된 스레드 풀을 생성하여 5개의 작업을 처리하는 프로그램을 작성하세요. 각 작업은 3초 동안 실행됩니다.
 * 각 작업의 시작과 완료 시간을 출력하여 스레드가 올바르게 실행되고 있는지 확인하세요.
 * 이 문제를 풀어보고, 필요하다면 추가 설명을 드리겠습니다!
 */
public class FixedThreadPool {

	public static void main(String[] ars){
		ExecutorService executor = Executors.newFixedThreadPool(2);

		for(int i=0; i<5; i++){
			Runnable thread = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " started : " + LocalTime.now());
					try{
						Thread.sleep(3000);
					}catch (Exception e){
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " finished : " + LocalTime.now());
				}
			};
			executor.execute(thread);
		}
		executor.shutdown();

		while(!executor.isTerminated()){

		}

		System.out.println("job finished");
	}
}
