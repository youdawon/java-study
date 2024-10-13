package main.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Quiz2 {

	/**
	 * 문제: 배열에서 짝수만 골라 제곱한 값을 출력하세요
	 * 주어진 정수 배열에서 짝수만 골라내고, 그 짝수들의 제곱 값을 리스트로 반환하는 프로그램을 작성하세요. 이를 위해 Predicate, Function, Consumer를 적절히 사용하여 문제를 해결하세요.
	 *
	 * 조건:
	 * 배열은 정수들로 이루어져 있으며, Predicate를 사용해 짝수를 필터링하세요.
	 * Function을 사용해 짝수를 제곱하세요.
	 * Consumer를 사용해 결과를 출력하세요.
	 * 예시:
	 * less
	 * 코드 복사
	 * 입력 배열: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
	 * 출력: [4, 16, 36, 64, 100]
	 * 힌트:
	 * Arrays.stream()을 사용하여 배열을 스트림으로 변환하세요.
	 * filter()와 map() 함수를 활용하여 짝수 필터링과 제곱 연산을 적용할 수 있습니다.
	 * 최종 결과는 forEach()나 Consumer를 통해 출력합니다.
 	 */
	public static void getEvens(){
		int[] arr =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		List<Integer> result = Arrays.stream(arr)
			.boxed()
			.filter(num -> num % 2 == 0)
			.map(even -> even * even)
			.collect(Collectors.toList());

		Consumer<List<Integer>> printList = list -> list.forEach(num -> System.out.println(num));

		printList.accept(result);
	}

	/**
	 * 문제 2: 문자열 리스트에서 특정 길이 이상의 문자열만 필터링하라
	 * 주어진 문자열 리스트에서 3글자 이상인 문자열만 필터링하여 출력하는 프로그램을 작성하세요.
	 *
	 * 조건:
	 * Predicate를 사용하여 문자열 길이를 필터링.
	 * Consumer를 사용하여 결과 리스트 출력.
	 * 입력: {"a", "ab", "abc", "abcd", "abcde"} 출력:
	 */
	public static void filterLength(){
		List<String> strList = List.of("a", "ab", "abc", "abcd", "abcde");

		List<String> result = strList.stream()
			.filter(str -> str.length() >= 3)
			.collect(Collectors.toList());

		Consumer<List<String>> printStrings = list -> list.forEach(str -> System.out.println(str));

		printStrings.accept(result);
 	}

	/**
	 * 문제 3: 숫자 배열에서 모든 숫자를 두 배로 변환하라
	 * 주어진 정수 배열에서 모든 숫자를 두 배로 변환한 값을 배열로 반환하는 프로그램을 작성하세요.
	 *
	 * 조건:
	 * Function을 사용하여 두 배로 변환.
	 * 결과 배열을 출력하세요.
	 * 입력: {1, 2, 3, 4, 5} 출력:
	 */
	public static void getSquares(){
		List<Integer> intList = List.of(1, 2, 3, 4, 5);

		List<Integer> result = intList
			.stream()
			.map(num -> num * num)
			.collect(Collectors.toList());

		Consumer<List<Integer>> printNums = list -> list.forEach(square -> System.out.println(square));

		printNums.accept(result);
	}

	/**
	 * 주어진 정수 배열에서 짝수만 필터링하고 그 합을 구하는 프로그램을 작성하세요.
	 *
	 * 조건:
	 * Predicate를 사용하여 짝수 필터링.
	 * 결과를 출력하세요.
	 * 입력: {1, 2, 3, 4, 5, 6, 7, 8, 9, 10} 출력:
	 */
	public static void getSum(){

		List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		int sum = intList
			.stream()
			.filter(num -> num % 2 == 0)
			.mapToInt(Integer::intValue)
			.sum();

		Consumer<Integer> printSum = totSum -> System.out.println(totSum);

		printSum.accept(sum);
	}

	public static void main(String[] args){
		getEvens();
		filterLength();
		getSquares();
	}
}
