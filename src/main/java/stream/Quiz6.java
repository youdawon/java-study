package main.stream;

public class Quiz6 {

	@FunctionalInterface
	public interface Function<T> {
		double apply(T t);
	}

	private static Student[] students = {
		new Student("홍길동", 90, 96),
		new Student("신용권", 95, 93)
	};

	public static double avg(Function<Student> function){
		double sum = 0;
		for (Student student : students) {
			sum += function.apply(student);  // 학생별 점수를 합산
		}
		return sum / students.length;
	}

	public static void main(String[] args) {
		// 영어 점수 평균 계산
		double englishAvg = avg(Student::getEnglishScore);
		System.out.println("영어 평균 점수: " + englishAvg);

		// 수학 점수 평균 계산
		double mathAvg = avg(Student::getMathScore);
		System.out.println("수학 평균 점수: " + mathAvg);
	}
}
