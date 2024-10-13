package concurrency;

/**

	### **이중 검증 잠금(DCL) 요약**:
	- 이중 검증 잠금(DCL)은 **싱글톤 패턴**에서 **불필요한 동기화 비용**을 줄이면서도 **스레드 안전성**을 보장하기 위해 사용됩니다.
	- 두 번의 검증을 통해, **객체가 이미 생성되었는지 확인한 후**에, 필요한 경우에만 **동기화**를 적용합니다.

	### **실습 목표**:
	- **싱글톤 패턴**을 구현하고, 이중 검증 잠금을 사용하여 **성능 최적화**와 **스레드 안전성**을 동시에 만족시키세요.
	- 여러 스레드가 동시에 객체를 요청해도 **단 한 번만 객체가 생성**되도록 구현하세요.
/**/
public class Singleton {
	// volatile 키워드를 사용해 인스턴스가 여러 스레드에서 올바르게 보이도록 보장합니다.
	private static volatile Singleton instance;

	// private 생성자 (외부에서 객체 생성 불가)
	private Singleton() {}

	// 싱글톤 객체 반환 메서드
	public static Singleton getInstance() {
		if(instance == null){
			synchronized (Singleton.class){
				if(instance == null)
					instance = new Singleton();
			}
		}
		return instance;
	}
}