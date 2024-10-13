package main.stream;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface Calculator{
	public int calculate(int a, int b);
}

public class Quiz1 {

	/**
	 * 람다 표현식을 사용하여 두 숫자를 더하는 프로그램을 작성하세요.
	 * 두 숫자를 입력으로 받아 더한 결과를 출력합니다.
	 */
	public static int add(){
		Scanner scanner = new Scanner(System.in);

		System.out.print("첫번째 숫자 입력 ");
		int first = scanner.nextInt();
		System.out.print("두번째 숫자 입력 ");
		int second = scanner.nextInt();

		Calculator calculator = ( a,  b) -> (a+b);

		return calculator.calculate(first, second);
	}

	/**
	 * . Function<T, R>
	 * 하나의 입력을 받아서 하나의 출력을 반환하는 함수형 인터페이스입니다.
	 * 예시: 입력받은 숫자를 제곱하는 함수.
	 * java
	 */
	public static int useFunction(){

		Scanner scanner = new Scanner(System.in);

		System.out.println("값을 입력해주세요.");
		int num = scanner.nextInt();

		Function<Integer, Integer> square = a -> a * a;

		return square.apply(num);
	}

	/**
	 * 2. Consumer<T>
	 * 입력을 받아서 소비만 하고 반환값은 없는 함수형 인터페이스입니다.
	 * 예시: 입력받은 문자열을 출력하는 함수.
	 */
	public static void useConsumer(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("값을 입력하세요.");
		int num = scanner.nextInt();
		Consumer<Integer> print = a -> System.out.println(a);
		print.accept(num);
	}

	/**
	 * 3. Supplier<T>
	 * 아무런 입력 없이 값을 반환하는 함수형 인터페이스입니다.
	 * 예시: 현재 시간을 반환하는 함수.
	 */
	public static void useSupplier(){
		Supplier<LocalTime> currentTime = () -> LocalTime.now();
		System.out.println(currentTime.get());
	}

	/**
	 * 4. Predicate<T>
	 * 입력을 받아서 true 또는 false를 반환하는 함수형 인터페이스입니다.
	 * 예시: 입력받은 숫자가 짝수인지 확인하는 함수.
	 */
	public static boolean usePrediccate(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("값을 입력하세요.");
		int num = scanner.nextInt();
		Predicate<Integer> even = a -> a % 2 == 0 ? true : false;

		return even.test(num);
	}

	/**
	 목표: 숫자를 입력받아, 그 숫자의 제곱을 반환하는 프로그램을 작성하세요. Function 인터페이스를 사용해 구현하세요.

	 조건:

	 Function<Integer, Integer>를 사용하여, 숫자를 제곱하는 람다 표현식을 작성하세요.
	 숫자를 입력받아 그 제곱을 출력하세요.
	 */
	public static void useFunction2(){

		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();

		Function<Integer, Integer> square = a -> a * a;

		System.out.println(square.apply(num));

	}

	/**
	 * 목표: 문자열을 입력받아, 그 문자열을 출력하는 프로그램을 작성하세요. Consumer 인터페이스를 사용해 구현하세요.
	 *
	 * 조건:
	 *
	 * Consumer<String>을 사용하여, 문자열을 출력하는 람다 표현식을 작성하세요.
	 * 사용자가 입력한 문자열을 출력하세요.
	 */
	public static void useConsumer2(){

		Scanner scanner = new Scanner(System.in);
		String inputStr = scanner.next();

		Consumer<String> printStr = a -> System.out.println(a);

		printStr.accept(inputStr);
	}

	/**
	 *
	 * 목표: 현재 시간을 출력하는 프로그램을 작성하세요. Supplier 인터페이스를 사용해 구현하세요.
	 *
	 * 조건:
	 *
	 * Supplier<LocalTime>을 사용하여 현재 시간을 반환하는 람다 표현식을 작성하세요.
	 * 현재 시간을 출력하세요.
	 */
	public static void useSupplier2(){

		Supplier<LocalTime> localTimeSupplier = () -> LocalTime.now();
		System.out.println(localTimeSupplier.get());
	}

	/**
	 * 문제 4: Predicate<T>
	 * 목표: 숫자를 입력받아, 그 숫자가 짝수인지 아닌지를 확인하는 프로그램을 작성하세요. Predicate 인터페이스를 사용해 구현하세요.
	 *
	 * 조건:
	 *
	 * Predicate<Integer>를 사용하여, 숫자가 짝수인지 확인하는 람다 표현식을 작성하세요.
	 * 숫자를 입력받아 짝수 여부를 출력하세요.
	 */
	public static void usePredicate(){
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();

		Predicate<Integer> evenPredicate = a -> a % 2 == 0 ? true : false;
		System.out.println(evenPredicate.test(num));
	}

	public static void main(String[] args){

		// System.out.println(useFunction());
		// useConsumer();
		// useSupplier();
		System.out.println(usePrediccate());
	}
}