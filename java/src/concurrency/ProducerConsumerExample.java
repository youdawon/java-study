package concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {

	private static final int BUFFER_SIZE = 5;  // 버퍼 크기
	private final Queue<Integer> buffer = new LinkedList<>();

	public static void main(String[] args) {
		concurrency.ProducerConsumerExample example = new concurrency.ProducerConsumerExample();

		Thread producer = new Thread(example.new Producer(), "Producer");
		Thread consumer = new Thread(example.new Consumer(), "Consumer");

		producer.start();
		consumer.start();
	}

	// 생산자 (Producer)
	class Producer implements Runnable {
		@Override
		public void run() {
			int value = 0;
			while (true) {
				synchronized (buffer) {
					while (buffer.size() == BUFFER_SIZE) {
						try {
							buffer.wait();  // 버퍼가 가득 차면 대기
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					buffer.add(++value);  // 버퍼에 데이터 추가
					System.out.println("Produced: " + value);
					buffer.notify();  // 소비자를 깨움
				}
				try {
					Thread.sleep(1000);  // 생산 지연 시간
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 소비자 (Consumer)
	class Consumer implements Runnable {
		@Override
		public void run() {
			while (true) {
				synchronized (buffer) {
					while (buffer.isEmpty()) {
						try {
							buffer.wait();  // 버퍼가 비면 대기
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int value = buffer.poll();  // 버퍼에서 데이터 소비
					System.out.println("Consumed: " + value);
					buffer.notify();  // 생산자를 깨움
				}
				try {
					Thread.sleep(1500);  // 소비 지연 시간
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
