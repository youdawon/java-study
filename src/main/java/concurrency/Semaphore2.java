package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 제 2: Semaphore 문제
 * 한 온라인 게임 서버에서 최대 3명의 플레이어가 동시에 특정 인스턴스 던전에 입장할 수 있습니다.
 * 던전이 가득 찼을 때, 대기 중인 플레이어는 이전 플레이어가 던전을 나갈 때까지 기다려야 합니다.
 *
 * Semaphore를 사용해 아래 요구사항을 만족하는 프로그램을 작성하세요:
 *
 * 요구사항
 * 플레이어 스레드를 5개 생성하여 던전에 입장하도록 합니다.
 * 한 번에 최대 3명의 플레이어만 던전에 입장할 수 있으며, 나머지는 대기합니다.
 * 각 플레이어는 던전에 입장하면 2초 동안 플레이하고 나가면서 허가를 반환합니다.
 * 플레이어가 던전에 들어가거나 나갈 때, 관련 메시지를 출력합니다 (Player X entered the dungeon,
 * Player X left the dungeon).
 * 이 두 문제를 통해 CountDownLatch와 Semaphore의 작동 방식과 활용 방법을 연습할 수 있습니다.
 */
public class Semaphore2 {

  public static void main(String[] args){

    Semaphore semaphore = new Semaphore(3);

    for(int i=0; i < 10; i++){
      new Thread(() -> {
        try {
          semaphore.acquire();
          System.out.println("player " + Thread.currentThread().getName() + "entered the dungeon");
          Thread.sleep(2000);

        }catch (InterruptedException ie){
          Thread.currentThread().interrupt();
        }finally {
          System.out.println("Player " +  Thread.currentThread().getName()+ " left the dungeon");
          semaphore.release();
        }
      }).start();
    }
  }
}
