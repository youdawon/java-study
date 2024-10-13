package stream;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import javax.swing.*;

public class Quiz7 {

	/**
	 * 1. 컬렉션에서 스트림 생성 및 활용
	 * 문제: 다음 리스트에서 짝수만 출력해보세요.
	 *
	 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	 *
	 * // 스트림을 생성하고, 짝수만 출력하는 코드 작성
	 **/
	public static void printEvens(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		numbers.stream()
			.filter(number -> number % 2 == 0)
			.forEach(System.out::println);
	}

	 /** 2. 중간 연산 연습 (map, filter, distinct, sorted)
	 * 문제 1: 리스트의 각 숫자를 제곱한 후, 짝수만 필터링하여 출력하세요.
	 *
	 * java
	 * 코드 복사
	 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	 *
	 * // map을 사용하여 숫자를 제곱하고, filter를 사용하여 짝수만 필터링하여 출력
	  **/
	 public static void squareNumbers(){
		 List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		 numbers.stream()
			 .map(number -> number * number)
			 .filter(number -> number % 2 == 0)
			 .forEach(System.out::println);
	 }

	 /**
	 * 문제 2: 주어진 리스트에서 중복된 값을 제거하고, 오름차순으로 정렬하여 출력하세요.
	 *
	 * java
	 * 코드 복사
	 * List<Integer> numbers = Arrays.asList(5, 3, 6, 2, 5, 6, 2, 1);
	 *
	 * // distinct()로 중복을 제거하고, sorted()로 정렬 후 출력
	  **/
	 public static void distinctNumbers(){
		 List<Integer> numbers = Arrays.asList(5, 3, 6, 2, 5, 6, 2, 1);

		 numbers.stream()
			 .distinct()
			 .sorted()
			 .forEach(number -> System.out.println(number));
	 }

	 /**
	 * 3. 최종 연산 연습 (forEach, collect, reduce)
	 * 문제 1: 리스트의 각 요소에 1을 더한 후, 그 결과를 새로운 리스트로 수집하세요.
	 *
	 * java
	 * 코드 복사
	 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
	 *
	 * // map()을 사용하여 1씩 더한 후 collect()로 결과를 새로운 리스트에 저장
	 **/
	 public static void createNewList(){
		 List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		 List<Integer> result = numbers.stream()
			 .map(number -> number + 1)
			 .collect(Collectors.toList());

		 result.stream()
			 .forEach(System.out::println);
	 }

	 /** 문제 2: 리스트의 숫자들의 합을 구하세요.
	 *
	 * java
	 * 코드 복사
	 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
	 *
	 * // reduce()를 사용하여 모든 요소의 합을 계산
	**/
	public static void sum(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		int sum = numbers.stream()
			.mapToInt(a -> a)
			.sum();

		System.out.println(sum);

		int sum2 = numbers.stream()
			.reduce(0, (a, b) -> a + b);


		System.out.println(sum2);
	}
	 /**
	 * 문제 3: 주어진 문자열 리스트에서 각 문자열의 길이를 출력하세요.
	 *
	 * java
	 * 코드 복사
	 * List<String> strings = Arrays.asList("apple", "banana", "orange", "kiwi");
	 *
	 * // map()으로 각 문자열의 길이를 계산하고, forEach로 출력
	 */
	 public static void getLength(){
		 List<String> strings = Arrays.asList("apple", "banana", "orange", "kiwi");

		 strings.stream()
			 .forEach(str -> System.out.println(str.length()));
	 }

	/**
	 * 리스트에서 숫자들의 평균을 구하세요.
	 * java
	 * 코드 복사
	 * List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	 *
	 * // 스트림을 이용해 숫자들의 평균을 계산하세요.
	 **/
	public static void getAverage(){

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		OptionalDouble average = numbers.stream()
			.mapToInt(number -> number)
			.average();

		average.ifPresent(System.out::println);

	}

	/**
	 * 주어진 리스트에서 가장 긴 문자열을 찾아 출력하세요.
	 * java
	 * 코드 복사
	 * List<String> strings = Arrays.asList("apple", "banana", "orange", "kiwi", "watermelon");
	 *
	 * // map()과 max()를 이용해 가장 긴 문자열을 찾으세요.
	 */
	public static void getMaxLength(){
		List<String> strings = Arrays.asList("apple", "banana", "orange", "kiwi", "watermelon");

		Optional<Integer> maxLength = strings.stream()
			.map(str -> str.length())
			.max(Integer::compareTo);

		maxLength.ifPresent(System.out::println);

	}


	public static void main(String[] args) {
		printEvens();
		squareNumbers();
		distinctNumbers();
		sum();
		getAverage();
		getMaxLength();
	}
}
