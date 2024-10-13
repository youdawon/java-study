package concurrency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 문제 1: 동기화 메서드를 사용한 파일 처리
 * 목표: 여러 스레드가 하나의 파일을 읽고 쓰는 작업을 동시성 문제 없이 처리하는 프로그램을 작성하세요.
 * 조건:
 * **동기화 메서드(synchronized method)**를 사용하여 여러 스레드가 파일에 동시에 쓰는 것을 방지하세요.
 * 각 스레드는 파일에 데이터를 쓰고, 쓰기가 끝나면 그 결과를 파일에서 읽습니다.
 * 파일에는 하나의 스레드만 동시에 쓰고, 읽기는 여러 스레드가 동시에 할 수 있도록 만드세요.
 */
public class ReadWriteFile {
	//
	// Object writeLock = new Object();
	// File file;
	//
	// public ReadWriteFile(){
	// 	file = new File("/Users/yudawon/Desktop/test.txt");
	// }
	//
	// class Read implements Runnable{
	//
	// 	public Read(){
	//
	// 	}
	//
	// 	@Override
	// 	public void run() {
	// 			try {
	// 				System.out.println(Thread.currentThread().getName() + ": 파일 읽기 시작");
	// 				System.out.println(Files.readString(Path.of(file.getPath()), Charset.forName("UTF-8")));
	// 				System.out.println(Thread.currentThread().getName() + ": 파일 읽기 완료");
	// 			} catch (Exception e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	}
	// }
	//
	// class Write implements Runnable{
	//
	// 	public Write(){
	//
	// 	}
	//
	// 	@Override
	// 	public void run() {
	// 		synchronized (writeLock){
	// 			try {
	// 				if (!file.exists()) {
	// 					file.createNewFile();
	// 				}
	// 				try (OutputStream os = new FileOutputStream(file, true);
	// 					 Writer writer = new OutputStreamWriter(os, Charset.forName("UTF-8"))) {
	// 					writer.write("쓰레드 테스트: " + Thread.currentThread().getName() + "\n");
	// 				}
	// 				System.out.println(Thread.currentThread().getName() + ": 파일 쓰기 완료");
	// 			}catch(Exception e){
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	}
	// }
	//
	// public static void main(String[] args){
	// 	ReadWriteFile readWriteFile = new ReadWriteFile();
	//
	// 	Thread readThread1 = new Thread(readWriteFile.new Read(), "read-1");
	// 	Thread readThread2 = new Thread(readWriteFile.new Read(), "read-2");
	// 	Thread readThread3 = new Thread(readWriteFile.new Read(), "read-3");
	// 	Thread writeThread1 = new Thread(readWriteFile.new Write(), "write-1");
	// 	Thread writeThread2 = new Thread(readWriteFile.new Write(), "write-2");
	//
	// 	writeThread1.start();
	// 	writeThread2.start();
	// 	readThread1.start();
	// 	readThread2.start();
	// 	readThread3.start();
	// }
}
