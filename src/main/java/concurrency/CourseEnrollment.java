package concurrency;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 맞아요, 구조가 유사하네요. 그렇다면 이번엔 조금 더 복잡한 문제를 드려볼게요.
 이번 문제는 동시성 제어와 잠금 시간 제한을 함께 고려해야 하는 문제입니다.

 문제:
 한 온라인 교육 플랫폼에서는 과목별 수강생 수를 실시간으로 관리하고 있습니다.
 이 시스템에서는 수강생 수를 확인하고, 수강 신청과 수강 취소를 관리하는 클래스가 필요합니다.

 요구사항:
 수강생 수 확인 기능 (getStudentCount 메서드):

 주어진 과목 ID에 대한 현재 수강생 수를 반환합니다.
 다수의 스레드가 동시에 수강생 수를 확인할 수 있어야 합니다.
 수강 신청 기능 (enrollStudent 메서드):

 주어진 과목 ID에 대해 수강생 수를 1 증가시킵니다.
 이 과정에서 쓰기 잠금을 획득하는 데 1초 이상 걸리면 타임아웃이 발생하도록 합니다.
 타임아웃 발생 시 수강 신청을 포기하고, 수강 신청 실패 메시지를 출력합니다.
 타임아웃 없이 수강 신청이 성공하면, "수강 신청 완료" 메시지를 출력합니다.
 수강 취소 기능 (cancelEnrollment 메서드):

 주어진 과목 ID에 대해 수강생 수를 1 감소시킵니다.
 수강 취소 과정에서도 쓰기 잠금을 획득하는 데 1초 이상 걸리면 타임아웃이 발생하여 수강 취소를 포기하고,
 수강 취소 실패 메시지를 출력합니다.
 타임아웃 없이 수강 취소가 성공하면, "수강 취소 완료" 메시지를 출력합니다.
 클래스 설계:

 ReadWriteLock을 사용하여 동시성을 제어하세요.
 과목별 수강생 수 데이터를 저장할 컬렉션은 Map<String, Integer> 형태로 사용하세요
 (String은 과목 ID, Integer는 수강생 수).
 추가 도전 (선택 사항):

 만약 과목 ID가 존재하지 않는 경우,
 getStudentCount, enrollStudent, 또는 cancelEnrollment 메서드가 해당 과목 ID를 자동으로 추가하고
 초기 수강생 수를 0으로 설정하도록 만들어 보세요.
 */
public class Library {

  Map<String, Integer> students = new ConcurrentHashMap<>();
  ReadWriteLock lock = new ReentrantReadWriteLock();

  public int getStudentCount(String id) {
    try {
      lock.readLock().lock();
      return students.put(id, students.getOrDefault(id, 0));
    } finally {
      lock.readLock().unlock();
    }
  }

  public void enrollStudent(String id){
    try {
        if (!lock.writeLock().tryLock(1, TimeUnit.SECONDS)) {
          System.out.println("수강 신청 실패");
          return;
        }
        students.put(id, students.getOrDefault(id, 0)+1);
        System.out.println("수강 신청 완료");
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }finally {
      lock.writeLock().unlock();
    }
  }

  public void cancelEnrollment(String id){
    try {
      if (!lock.writeLock().tryLock(1, TimeUnit.SECONDS)) {
        System.out.println("수강 취소 실패");
        return;
      }
      students.put(id, students.getOrDefault(id, 0)-1);
      System.out.println("수강 취소 완료");
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    } finally {
      lock.writeLock().unlock();
    }
  }
}
