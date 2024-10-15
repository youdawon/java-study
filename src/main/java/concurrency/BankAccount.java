package concurrency;

/**
문제 1: 은행 계좌 시스템 - 입출금과 잔액 확인 동시성 처리
    문제: 다중 스레드 환경에서 은행 계좌의 입출금 작업을 안전하게 처리하는 시스템을 만드세요.
 잔액을 조회하는 작업도 동시 실행됩니다. ReentrantLock을 사용해 스레드 안전성을 보장하세요.

    요구사항
    BankAccount 클래스를 구현하세요. 이 클래스는 다음 메서드를 포함합니다:

    deposit(int amount): 계좌에 금액을 입금합니다.
    withdraw(int amount): 계좌에서 금액을 출금합니다.
 잔액이 부족하면 "Insufficient balance" 메시지를 출력합니다.
    getBalance(): 현재 잔액을 출력합니다.
    ReentrantLock 사용:

    deposit()과 withdraw()는 스레드 안전하게 구현해야 하며,
 잔액이 부족한 경우 "Insufficient balance"를 출력하고 작업을 취소합니다.
    getBalance()는 ReentrantLock을 사용하여 잔액을 안전하게 조회합니다.
    main 메서드에서는 여러 스레드가 동시에 입출금과 잔액 조회를 수행하도록 합니다.

   **/

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
  private int balance = 0;
  private final ReentrantLock lock = new ReentrantLock();

  public void deposit(int amount) {
    try{
      this.lock.lock();
      this.balance += amount;
    }finally {
      this.lock.unlock();
    }
  }

  public void withdraw(int amount) {
    try{
      this.lock.lock();
      if(this.balance < amount){
        System.out.println("Insufficient balance");
        return;
      }
      this.balance -= amount;
    }finally {
      this.lock.unlock();
    }
  }

  public int getBalance() {
    try {
      this.lock.lock();
      // 잔액 조회 메서드 구현
      return this.balance;
    }finally {
      this.lock.unlock();
    }
  }

  public static void main(String[] args) {
    BankAccount account = new BankAccount();

    Runnable depositor = () -> {
      for (int i = 0; i < 5; i++) {
        account.deposit(100);
      }
    };

    Runnable withdrawer = () -> {
      for (int i = 0; i < 5; i++) {
        account.withdraw(50);
      }
    };

    Runnable balanceChecker = () -> {
      for (int i = 0; i < 5; i++) {
        System.out.println("Balance: " + account.getBalance());
      }
    };

    // 5개의 입금, 출금, 잔액 확인 스레드를 각각 시작
    for (int i = 0; i < 5; i++) {
      new Thread(depositor).start();
      new Thread(withdrawer).start();
      new Thread(balanceChecker).start();
    }
  }
}