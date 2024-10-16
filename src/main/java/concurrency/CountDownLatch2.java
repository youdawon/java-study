package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 문제 1: CountDownLatch 문제
 * 한 웹 애플리케이션에서는 초기화 작업으로 여러 서버에 데이터를 동기화해야 합니다.
 * 이 초기화 작업은 총 3개의 독립적인 서버에서 이루어지며,
 * 모든 서버가 초기화를 완료한 후에만 본 서비스가 시작될 수 있습니다.
 *
 * CountDownLatch를 사용해 아래 요구사항을 만족하는 프로그램을 작성하세요:
 *
 * 요구사항
 * 3개의 서버 스레드를 생성하고, 각각 초기화 작업을 완료하면 카운트를 감소시킵니다.
 * 메인 스레드는 3개의 서버가 초기화를 완료할 때까지 대기하고,
 * 초기화 완료 후 "Service is now ready" 메시지를 출력합니다.
 * 서버 초기화는 각기 다른 시간이 소요되며, 이를 시뮬레이션하기 위해 Thread.sleep()을 사용합니다.
 */
public class CountDownLatch {

  public static void main(String[] args){

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    executorService.execute(() -> {

    });
  }
}