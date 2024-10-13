package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Quiz3 {

	/**
	 * 문제 1: 정적 메서드 참조
	 * 주어진 문자열 리스트에서 문자열을 정수로 변환한 후, 그 중 짝수만 출력하는 프로그램을 작성하세요.
	 *
	 * 정적 메서드 참조: Integer::parseInt
	 * 람다 표현식 대신 메서드 참조 사용하기
	 * 입력: ["1", "2", "3", "4", "5"] 출력:
	 */
	public static void getEvens(){
		List<String> stringList = List.of("1", "2", "3", "4", "5");

		List<Integer> result = stringList
			.stream()
			.map(Integer::parseInt)
			.filter(num -> num % 2 == 0)
			.collect(Collectors.toList());

		Consumer<List<Integer>> printList = list -> list.forEach(System.out::println);

		printList.accept(result);
	}

	/**
	 * 문제 2: 인스턴스 메서드 참조 (임의 객체)
	 * 주어진 문자열 리스트에서 모든 문자열을 대문자로 변환한 후 출력하세요.
	 *
	 * 인스턴스 메서드 참조: String::toUpperCase
	 * 입력: ["hello", "world", "java"] 출력:
	 */
	public static void getUpperCase(){
		List<String> stringList = Arrays.asList("hello", "world", "java");

		List<String> result = stringList
			.stream()
			.map(String::toUpperCase)
			.collect(Collectors.toList());

		Consumer<List<String>> printLetters = list -> list.forEach(System.out::println);

		printLetters.accept(result);
	}

	/**
	 * 문제 4: 정렬된 리스트에서 특정 요소 찾기 (Optional 사용)
	 * 주어진 정수 리스트에서 가장 큰 짝수를 찾아 출력하세요. 만약 짝수가 없다면, "짝수가 없습니다"를 출력하세요.
	 *
	 * Optional을 사용하여 값을 안전하게 처리하세요.
	 * 정적 메서드 참조: Integer::max
	 * 입력: [3, 7, 5, 10, 8, 6] 출력:
	 */
	public static void getMax(){
		List<Integer> integers = Arrays.asList(3, 7, 5, 10, 8, 6);

		Optional<Integer> maxOptional = integers
			.stream()
			.filter(num -> num % 2 == 0)
			.max(Integer::compareTo);

		Consumer<Optional<Integer>> printMax = optional -> optional
			.ifPresentOrElse(max -> System.out.println(max),
				() -> System.out.println("짝수가 없습니다"));

		printMax.accept(maxOptional);
	}


	public static void main(String[] args){
		getEvens();
		getUpperCase();
	}
}
