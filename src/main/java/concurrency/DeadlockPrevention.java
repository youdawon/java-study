package concurrency;

/**
좋습니다! 다음 실습은 **데드락(Deadlock) 방지**에 관한 문제입니다.
 이번 실습에서는 두 개의 스레드가 두 개의 다른 객체에 대해 각각 동기화된 자원을 획득할 때,
 **데드락**이 발생하지 않도록 설계하는 방법을 실습하겠습니다.

	### **실습 3: 데드락 방지**
	- **목표**: 두 개의 스레드가 두 개의 다른 객체에 대해 각각 동기화된 자원을 사용하지만, **데드락**이 발생하지 않도록 설계하세요.
	- **조건**: 두 개의 스레드가 서로 다른 순서로 자원을 요청할 때,
 잘못된 동기화 순서로 인해 서로 자원을 기다리며 멈추는 데드락 상태를 방지합니다.

	### **실습 문제**:
	1. 두 스레드가 각자 두 개의 자원(`lock1`, `lock2`)을 **순차적으로 사용**하려고 합니다.
	2. 동기화된 자원을 사용하기 위해 **동일한 순서**로 자원을 요청하여 **데드락**을 방지하세요.
**/

public class DeadlockPrevention {
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();

	public void method1() {
		synchronized (lock1) {
			System.out.println(Thread.currentThread().getName() + " acquired lock1");
			synchronized (lock2) {
				System.out.println(Thread.currentThread().getName() + " acquired lock2");
			}
		}
	}

	public void method2() {
		synchronized (lock1) {  // 같은 순서로 lock1 -> lock2 획득
			System.out.println(Thread.currentThread().getName() + " acquired lock1");
			synchronized (lock2) {
				System.out.println(Thread.currentThread().getName() + " acquired lock2");
			}
		}
	}


	public static void main(String[] args) {
		DeadlockPrevention example = new DeadlockPrevention();

		Thread thread1 = new Thread(example::method1, "Thread-1");
		Thread thread2 = new Thread(example::method2, "Thread-2");

		thread1.start();
		thread2.start();
	}
}