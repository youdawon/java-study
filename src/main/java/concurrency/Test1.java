package concurrency;

/**
 * 실습 문제 1: 멀티스레드 HelloWorld
 * 문제: 두 개의 스레드를 만들어 각각 "Hello"와 "World"를 출력하는 프로그램을 작성하세요.
 *
 * 각 스레드는 1초 간격으로 5번씩 메시지를 출력합니다.
 * Thread 또는 Runnable을 사용해 스레드를 생성할 수 있습니다.
 * 메시지와 스레드 이름을 함께 출력하도록 구현하세요.
 * 이 문제를 해결한 후 코드나 궁금한 점이 있으면 알려주세요!
 */
public class Test1 implements Runnable {

	private String message;

	private Test1(String message){
		this.message = message;
	}

	@Override
	public void run() {
		for(int i=0; i<5; i++){
			System.out.println(Thread.currentThread().getName() +" : "+ this.message);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public static void main(String args[]){
		Thread thread1 = new Thread(new Test1("hello"));
		Thread thread2 = new Thread(new Test1("world"));
		thread1.start();
		thread2.start();
	}
}
