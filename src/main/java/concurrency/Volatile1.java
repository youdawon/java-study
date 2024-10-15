package concurrency;

import java.util.ArrayList;
import java.util.List;
import javax.imageio.plugins.tiff.TIFFImageReadParam;


/**
 * 문제: 스레드 안전한 카운터 만들기
 * 여러 스레드가 동시에 접근할 수 있는 Counter 클래스를 작성하세요.
 * 이 클래스에는 두 가지 메서드가 있습니다:
 * increment(): count를 1씩 증가시킵니다.
 * getCount(): 현재 count 값을 반환합니다.
 * 단, count 변수는 스레드 안전하고 정확한 값을 가져야 합니다.
 * 조건:
 * count 변수에는 volatile 키워드를 사용할 수 있습니다.
 * increment()는 각 스레드가 동시에 호출해도 모든 증가 연산이 반영되도록 해야 합니다.
 */
public class Volatile1 {

  static class Counter{

    private volatile int count;

    public Counter() {
    }

    public int getCount(){
      return this.count;
    }

    public void increment(){
      this.count += 1;
    }
  }


  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();
    List<Thread> threads = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      Thread t = new Thread(() -> {
        for (int j = 0; j < 100; j++) {
          counter.increment();
        }
      });
      threads.add(t);
      t.start();
    }

    for (Thread t : threads) {
      t.join();
    }

    System.out.println("Final count: " + counter.getCount());
  }
}
