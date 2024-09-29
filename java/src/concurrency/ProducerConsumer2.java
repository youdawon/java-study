package concurrency;

/**
 * 3. 생산자-소비자 패턴 확장
 * 목표: 기존의 생산자-소비자 패턴을 확장하여 여러 생산자와 여러 소비자가 함께 데이터를 주고받을 수 있는 프로그램을 작성하세요.
 * 조건:
 * 기존의 wait()와 notifyAll()을 사용한 구조를 사용해, 여러 생산자와 여러 소비자가 하나의 버퍼를 공유하도록 확장하세요.
 * 생산자 스레드와 소비자 스레드 각각 3개씩을 만들고, 번갈아 가며 데이터를 처리하도록 만드세요.
 * 모든 스레드가 정상적으로 실행되고 작업을 마칠 수 있도록 동기화를 처리하세요.
 */
public class ProducerConsumer2 {

	private int buffer = 0;
	private int BUFFER_SIZE = 5;
	private Object lock = new Object();

	public ProducerConsumer2(){
	}

	public int getBuffer(){
		return this.buffer;
	}

	class Producer implements Runnable{

		public Producer(){
		}

		@Override
		public void run() {
			while(true){
				synchronized (lock) {
					while(buffer >= BUFFER_SIZE){
						System.out.println(Thread.currentThread().getName() + ": 버퍼가 가득 찼습니다. 대기 중...");
						try {
							lock.wait();
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
					}
					buffer++;
					System.out.println(Thread.currentThread().getName() + ": 데이터를 추가했습니다. 남은 버퍼: " + buffer);
					lock.notifyAll();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

	class Consumer implements Runnable {
		public Consumer() {
		}

		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					while (buffer == 0) {
						System.out.println(Thread.currentThread().getName() + ": 버퍼가 비었습니다. 대기 중...");
						try {
							lock.wait();
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
					}
					buffer--;
					System.out.println(Thread.currentThread().getName() + ": 데이터를 소비했습니다. 남은 버퍼: " + buffer);
					lock.notifyAll();

				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args){
		ProducerConsumer2 producerConsumer = new ProducerConsumer2();

		Thread consumerThread1 = new Thread(producerConsumer.new Consumer(), "consumer-1");
		Thread consumerThread2 = new Thread(producerConsumer.new Consumer(), "consumer-2");
		Thread consumerThread3 = new Thread(producerConsumer.new Consumer(), "consumer-3");
		Thread producerThread1 = new Thread(producerConsumer.new Producer(), "producer-1");
		Thread producerThread2 = new Thread(producerConsumer.new Producer(), "producer-2");
		Thread producerThread3 = new Thread(producerConsumer.new Producer(), "producer-3");

		producerThread1.start();
		producerThread2.start();
		producerThread3.start();
		consumerThread1.start();
		consumerThread2.start();
		consumerThread3.start();
	}
}
