package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
    **문제**: 특정 자원에 접근할 때 **타임아웃을 설정**하여,
 * 일정 시간 동안 락을 획득하지 못하면 접근을 포기하고 다른 작업을 수행하는 프로그램을 구현하세요.

    #### 요구사항
    1. `TimeoutResourceAccess` 클래스를 작성하세요. 이 클래스에는 다음 메서드가 포함됩니다:
    - `accessResource()`: 자원에 접근할 때 1초 동안만 락을 시도합니다. 락을 획득하면
 `"Resource accessed"`를 출력하고, 1초 내에 획득하지 못하면
 `"Failed to acquire lock"`을 출력한 후 다른 작업을 수행하도록 합니다.

    2. **타임아웃 설정**:
    - `ReentrantLock`의 `tryLock(timeout, TimeUnit)` 메서드를 활용하여 타임아웃을 설정합니다.

    #### 구현 예시
    `TimeoutResourceAccess` 클래스의 `accessResource()` 메서드를 작성해 보세요.

    ```java
    import java.util.concurrent.TimeUnit;
    import java.util.concurrent.locks.ReentrantLock;

 **/

public class TimeoutResourceAccess {
  private final ReentrantLock lock = new ReentrantLock();

  public void accessResource() {
    // 타임아웃을 설정한 자원 접근 메서드 구현
    try {
      if (!lock.tryLock(1, TimeUnit.SECONDS)) {
        System.out.println("Failed to acquire lock");
        return;
      }
      System.out.println("Resource accessed");
    }catch (InterruptedException ie){
      Thread.currentThread().interrupt();
    }finally {
      if (lock.isHeldByCurrentThread()) {
        lock.unlock(); // 락을 보유한 경우에만 해제
      }
    }
  }

  public static void main(String[] args) {
    TimeoutResourceAccess resource = new TimeoutResourceAccess();
    Runnable task = () -> {
      while (true) {
        resource.accessResource();
        try {
          Thread.sleep(500); // 다른 작업 수행 간격
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    };

    // 여러 스레드가 자원에 접근 시도
    for (int i = 0; i < 5; i++) {
      new Thread(task).start();
    }
  }
}