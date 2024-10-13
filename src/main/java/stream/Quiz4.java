package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

class Person{
	String name;
	String age;
}

public class Quiz4 {

	/**
	 * 문제 1: 짝수와 홀수 합 구하기
	 * 주어진 정수 리스트에서 짝수와 홀수의 합을 각각 구한 후 출력하세요.
	 *
	 * 조건:
	 * 람다 표현식과 Stream API를 사용하여 짝수와 홀수를 필터링하고 각각의 합을 구하세요.
	 * 결과는 다음과 같은 형식으로 출력하세요: 짝수 합: 30, 홀수 합: 25
	 * 입력: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
	 *
	 * 출력:
	 * 코드 복사
	 * 짝수 합: 30
	 * 홀수 합: 25
	 * */
	public static void getEvenOddSum(){

		List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		//짝수
		int even = integerList
			.stream()
			.filter(num -> num % 2 == 0)
			.mapToInt(Integer::intValue)
			.sum();

		//짝수
		int odd = integerList
			.stream()
			.filter(num -> num % 2 != 0)
			.mapToInt(Integer::intValue)
			.sum();

		Consumer<Integer> printInt = num -> System.out.println(num);
		printInt.accept(even);
		printInt.accept(odd);
	}

	/**
	 * 문제 2: 문자열 리스트에서 길이가 가장 긴 문자열 찾기
	 * 주어진 문자열 리스트에서 가장 긴 문자열을 찾아 출력하세요. 문자열이 여러 개일 경우 첫 번째로 등장한 문자열을 출력합니다.
	 *
	 * 조건:
	 * Stream API와 Optional을 사용하세요.
	 * 입력: ["apple", "banana", "grape", "pineapple", "orange"]
	 *
	 * 출력:
	 *
	 * 코드 복사
	 * 가장 긴 문자열: pineapple
	  **/
	 public static void getMaxLength(){

		 List<String> stringList = Arrays.asList("apple", "banana", "grape", "pineapple", "orange");

		 Optional<String> result = stringList
			 .stream()
			 .max((a, b) -> Integer.compare(a.length(), b.length()));

		 Consumer<Optional<String>> printResult = optional -> optional.ifPresent(System.out::println);

		 printResult.accept(result);
	 }


	 /**
	 * 문제 3: 두 문자열의 접두사 찾기
	 * 주어진 두 문자열에서 공통 접두사를 찾아 출력하세요. 공통 접두사가 없으면 "공통 접두사가 없습니다"를 출력하세요.
	 *
	 * 조건:
	 * 람다 표현식을 사용하여 두 문자열을 비교하고 접두사를 찾으세요.
	 * Optional을 사용하여 결과가 없을 때 안전하게 처리하세요.
	 * 입력: "prefix", "prelude"
	 *
	 * 출력:
	 *
	 * 코드 복사
	 * 공통 접두사: pre
	  **/
	 public static void getPrefix(){

		 String s1 = "prefix";
		 String s2 = "prelude";

		 int minLength = Math.min(s1.length(), s2.length());

		 // int commonLength =
	 }

	/**
	 *  문제 4: 짝수의 제곱 중에서 최댓값 구하기
	 * 주어진 정수 리스트에서 짝수만 필터링한 후, 그 짝수들의 제곱 중에서 최댓값을 구해 출력하세요. 짝수가 없으면 "짝수가 없습니다"를 출력하세요.
	 *
	 * 조건:
	 * 메서드 참조와 Optional을 사용하세요.
	 * 입력: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
	 *
	 * 출력:
	 *
	 * makefile
	 * 코드 복사
	 * 최댓값: 100
	 **/
	public static void getMax(){

		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		Optional<Integer> max = integers
			.stream()
			.filter(num -> num % 2 == 0)
			.map(num -> num * num)
			.max(Integer::compareTo);

		Consumer<Optional<Integer>> printMax = optional -> optional.ifPresent(System.out::println);

		printMax.accept(max);
	}

	 /**
	 * 문제 5: 생성자 참조를 사용해 객체 생성
	 * Person 클래스를 사용하여 주어진 이름과 나이 정보를 기반으로 Person 객체를 생성하고, 각 사람의 정보를 출력하세요.
	 *
	 * 조건:
	 * 생성자 참조를 사용하여 Person 객체를 생성하세요.
	 * 각 사람의 이름과 나이를 출력하세요.
	 * 입력:
	 *
	 * java
	 * 코드 복사
	 * [
	 *     {"name": "Alice", "age": 25},
	 *     {"name": "Bob", "age": 30},
	 *     {"name": "Charlie", "age": 35}
	 * ]
	 * 출력:
	 * Name: Alice, Age: 25
	 * Name: Bob, Age: 30
	 * Name: Charlie, Age: 35
	  **/
	 public static void printPersons(){


	 }

	  /**
	 * 문제 6: 문자열 리스트에서 각 단어의 길이를 구하라
	 * 주어진 문자열 리스트에서 각 단어의 길이를 구하여 출력하세요.
	 *
	 * 조건:
	 * 람다 표현식과 메서드 참조를 사용하여 각 단어의 길이를 계산하세요.
	 * 입력: ["hello", "world", "java", "stream"]
	 *
	 * 출력:
	 * hello: 5
	 * world: 5
	 * java: 4
	 * stream: 6
	 */
	  public static void getLength(){
		  List<String> list = Arrays.asList("hello", "world", "java", "stream");

		  list.stream()
			  .forEach(value -> System.out.println(value  + ": " + value.length()));
	  }

	 public static void main(String[] args){
		 getMaxLength();
		 getMax();
		 getLength();
	 }
}
