package concurrency;

/**
 * 실습 문제: volatile을 활용한 스레드 간 통신
 * 문제:
 * 두 개의 스레드가 각각 flag 값을 확인하고 수정하는 프로그램을 작성하세요.
 * 한 스레드는 flag 값을 true로 변경하고, 다른 스레드는 flag 값이 true로 변경되었을 때 종료 메시지를 출력하세요.
 * volatile 키워드를 사용하여 스레드 간 변경 사항이 즉시 반영되도록 하세요.
 * 이 문제를 풀어보시고, 결과를 확인해 보겠습니다!
 */
public class Flag {

	private volatile  boolean isChanged;

	public Flag(){
		isChanged = false;
	}

	public void changeFlagToTrue(){
		try {
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		isChanged = true;
		System.out.println(Thread.currentThread().getName() + " changed a flag");
	}

	public void existIfTrue(){
		while(!isChanged){
		}
		System.out.println(Thread.currentThread().getName() + " closed");
	}

	public static void main(String[] args) {

		Flag flag = new Flag();

		Thread thread1 = new Thread(flag::changeFlagToTrue);
		Thread thread2 = new Thread(flag::existIfTrue);

		thread1.start();
		thread2.start();
	}
}
