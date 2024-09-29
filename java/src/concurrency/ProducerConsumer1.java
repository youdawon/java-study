package concurrency;

/**
*
 2. 생산자-소비자 문제 (wait()와 notify() 사용)
 목표: **생산자(Producer)**와 **소비자(Consumer)**가 공유 버퍼를 사용하여 데이터를 주고받는 프로그램을 작성하세요.
 조건:
 **wait()와 notify()**를 사용해, 생산자가 데이터를 버퍼에 추가하면 소비자가 그 데이터를 가져가는 구조로 만드세요.
 버퍼가 가득 차면 생산자는 대기하고, 버퍼가 비면 소비자는 대기하도록 설계하세요.
 버퍼의 크기는 5로 설정하세요.
 */
public class ProducerConsumer1 {

	private int maxBuffer = 5;
	private int buffer = 0;
	private Object lock = new Object();

	public ProducerConsumer1(){
	}

	public int getBuffer(){
		return this.buffer;
	}

	public void consumer(){
		synchronized (lock){
			while(buffer == 0){
				try {
					System.out.println("소비자: 버퍼가 비었습니다. 대기 중...");
					lock.wait();
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
			this.buffer--;
			System.out.println("소비자: 데이터를 소비했습니다. 남은 버퍼: " + buffer);
			lock.notify();
		}
	}

	public void producer(){
		synchronized (lock){
			while(buffer >= maxBuffer){
				try {
					System.out.println("생산자: 버퍼가 가득 찼습니다. 대기 중...");
					lock.wait();
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
			this.buffer++;
			System.out.println("생산자: 데이터를 추가했습니다. 남은 버퍼: " + buffer);
			lock.notify();
		}
	}

	public static void main(String[] args){
		ProducerConsumer1 producerConsumer = new ProducerConsumer1();

		Thread producerThread = new Thread(() -> {
			while(true){
				producerConsumer.producer();
				try{
					Thread.sleep(1000);
				}catch (InterruptedException ie){
					ie.printStackTrace();
				}
			}
		});
		Thread consumerThread = new Thread(() -> {
			while(true){
				producerConsumer.consumer();
				try{
					Thread.sleep(1500);
				}catch (InterruptedException ie){
					ie.printStackTrace();
				}
			}
		});

		producerThread.start();
		consumerThread.start();
	}
}
