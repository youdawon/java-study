package main.stream;

public class Quiz5 {

	public static void main(String[] args){
		Thread thread = new Thread(
			() -> System.out.println("작업 쓰레드가 실행됩니다."));
		thread.start();
	}
}