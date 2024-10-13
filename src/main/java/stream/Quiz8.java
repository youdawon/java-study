package stream;

import java.util.*;
import java.util.stream.Collectors;

public class Quiz8 {

	/**1. 컬렉션에서 스트림 생성과 활용
	문제 1: 리스트에서 짝수만 추출하여 새로운 리스트로 변환하세요.
	java
	코드 복사
	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

	// 짝수만 포함된 리스트를 생성하고 출력
	 **/
	public static void getEvens(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		List<Integer> result = numbers.stream()
			.filter(number -> number % 2 == 0)
			.collect(Collectors.toList());

		result.stream()
			.forEach(System.out::println);
	}

	/**
	문제 2: 주어진 문자열 리스트에서 문자열의 길이가 5 이상인 문자열만 필터링하여 출력하세요.
	java
	코드 복사
	List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "watermelon");

	// 길이가 5 이상인 문자열만 출력
	**/
	public static void getLength(){

		List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "watermelon");

		strings.stream()
			.filter(string -> string.length() >= 5)
			.forEach(System.out::println);
	}

	 /**
2. 중간 연산(map, filter, distinct, sorted)
	문제 3: 숫자 리스트에서 각 숫자에 3을 곱한 후 중복된 값을 제거하고 오름차순으로 정렬하세요.
	java
	코드 복사
	List<Integer> numbers = Arrays.asList(3, 6, 9, 12, 15, 3, 6, 18, 21);

	// 중복을 제거하고, 3을 곱한 값을 오름차순으로 정렬하여 출력
	**/
	public static void getOrders(){
		List<Integer> numbers = Arrays.asList(3, 6, 9, 12, 15, 3, 6, 18, 21);

		numbers.stream()
			.distinct()
			.map(number -> number * 3)
			.sorted()
			.forEach(System.out::println);
	}

  	/**
	문제 4: 주어진 문자열 리스트에서 각 문자열을 대문자로 변환한 후, 중복을 제거하여 정렬된 리스트로 변환하세요.
	java
	코드 복사
	List<String> strings = Arrays.asList("apple", "banana", "Apple", "banana", "orange");

	// 문자열을 대문자로 변환하고, 중복을 제거한 후 출력
	 **/
	public static void getUpper(){
		List<String> strings = Arrays.asList("apple", "banana", "Apple", "banana", "orange");

		strings.stream()
			.map(str -> str.toUpperCase())
			.distinct()
			.sorted()
			.forEach(System.out::println);
	}

	/**
	 3. 최종 연산(forEach, collect, reduce)
	 문제 5: 숫자 리스트에서 각 숫자에 2를 더한 후, 그 결과를 새로운 리스트로 변환하세요.
	 java
	 코드 복사
	 List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

	 // 각 숫자에 2를 더한 새로운 리스트를 생성하고 출력
	 **/
	public static void getNewList(){

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		List<Integer> result = numbers.stream()
			.map(number -> number + 2)
			.collect(Collectors.toList());

		result.stream().forEach(System.out::println);

	}

	/**
	문제 6: 숫자 리스트에서 모든 숫자의 곱을 구하세요.
	java
	코드 복사
	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

	// 모든 숫자를 곱하여 출력
	 **/
	public static void getMultiply(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		int result = numbers.stream()
			.reduce(1, (a, b) -> a * b);

		System.out.println(result);
	}

	/**
4. 종합 문제
	문제 7: 주어진 학생 리스트에서 나이가 20세 이상인 학생들만 추출하여 이름을 오름차순으로 정렬한 후 출력하세요.
		java
	코드 복사
	 /**/

	static class Student {
		String name;
		int age;

		public Student(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}
	}
	// 나이가 20세 이상인 학생들의 이름을 오름차순으로 정렬하여 출력
	public static void getOrder(){

		List<Student> students = Arrays.asList(
			new Student("Alice", 18),
			new Student("Bob", 22),
			new Student("Charlie", 19),
			new Student("Dave", 20),
			new Student("Eve", 21)
		);

		students.stream()
			.filter(student -> student.getAge() >= 20)
			.map(Student::getName)
			.sorted()
			.forEach(System.out::println);
	}


	public static void main(String[] args){
		getOrder();
	}
}
