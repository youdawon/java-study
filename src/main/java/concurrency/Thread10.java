package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 좋아요! 다음 문제는 `Future`와 `Callable`을 활용하여 멀티스레드 환경에서 더 복잡한 작업을 처리하는 것입니다.
 *
 * ### 문제: 멀티스레드로 문자열 길이 계산하기
 *
 * **요구사항**:
 * 1. 문자열 배열이 주어집니다. 각 문자열의 길이를 계산하여 반환하는 `Callable` 작업을 구현하세요.
 * 2. 배열의 각 문자열에 대해 별도의 스레드를 생성하여 길이를 계산하고, 그 결과를 `Future`를 통해 가져옵니다.
 * 3. 최종적으로 모든 문자열의 길이를 출력하세요.
 *
 * **예시**:
 * ```java
 * String[] strings = {"Hello", "Concurrency", "Java", "Future", "Thread"};
 * ```
 * **출력 예시**:
 * ```
 * Length of "Hello": 5
 * Length of "Concurrency": 12
 * Length of "Java": 4
 * Length of "Future": 6
 * Length of "Thread": 6
 * ```
 *
 * ### 추가 사항:
 * - 각 문자열의 길이를 계산하는 스레드에서 결과를 출력할 때는 문자열과 함께 길이를 출력하세요.
 * - 모든 스레드의 결과를 가져온 후, 메인 스레드에서 최종 결과를 출력하도록 하세요.
 *
 * 이 문제를 해결하면서 `Future`와 `Callable`을 사용하는 방법을 더 잘 이해할 수 있을 것입니다.
 * 코드를 작성한 후 결과를 공유해 주시면 피드백을 드리겠습니다!
 */
public class Thread10 {

  public static void main(String[] args){
    String[] strings = {"Hello", "Concurrency", "Java", "Future", "Thread"};
    ExecutorService executorService = Executors.newFixedThreadPool(strings.length);

    for(String str : strings){
      Callable<Integer> thread = () -> {
        System.out.println("Length of Thread: " + str.length());
        return str.length();
      };
    }
  }
}
