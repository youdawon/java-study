package concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 실습 문제:
 * ReadWriteLock을 사용하여, 여러 스레드가 동시에 자원을 읽을 수 있도록 하고, 단일 스레드만 자원을 쓸 수 있도록 하세요.
 * 3개의 읽기 작업 스레드와 1개의 쓰기 작업 스레드를 만들어 작업을 수행하게 합니다.
 * 쓰기 작업이 진행 중일 때는 다른 스레드가 읽기나 쓰기 작업을 수행하지 못하게 만듭니다.
 */
public class ReadWriteLockExample {

	public static void main(String[] args){
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		SharedResource sharedResource = new SharedResource(readWriteLock);

		for(int i=0; i<3; i++){
			new Thread(() -> sharedResource.readResource()).start();
		}

		for(int i=0; i<1; i++){
			new Thread(() -> sharedResource.writeResource()).start();
		}
	}

	static class SharedResource{

		ReadWriteLock readWriteLock;
		int resource = 0;

		public SharedResource(ReadWriteLock readWriteLock){
			this.readWriteLock = readWriteLock;
		}

		public void readResource(){
			try {
				readWriteLock.readLock().lock();
				System.out.println(Thread.currentThread().getName() + " is reading resource: " + resource);
				Thread.sleep(1000);  // 1초 동안 읽기 작업 수행
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				readWriteLock.readLock().unlock();
			}

		}

		public void writeResource(){
			try {
				readWriteLock.writeLock().lock();
				resource++;
				System.out.println(Thread.currentThread().getName() + " is writing resource: " + resource);
				Thread.sleep(1000);  // 1초 동안 읽기 작업 수행
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				readWriteLock.writeLock().unlock();
			}

		}
	}
}
