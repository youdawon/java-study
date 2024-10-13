package main.design.bank.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import main.design.bank.account.BankAccount;
import main.design.bank.account.CheckingAccount;
import main.design.bank.account.Person;
import main.design.bank.account.SavingsAccount;

public class AccountService {

	ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
	AtomicLong id = new AtomicLong(0);

	public BankAccount createAccount(Person person, int pinNumber, double amount, double cardLimit, double interestRate,  boolean isChecking) throws Exception {
		long accountNumber = this.id.incrementAndGet();
		BankAccount bankAccount;
		if(isChecking){
			bankAccount = createCheckingAccount(person, accountNumber, pinNumber, cardLimit, amount);
		} else {
			bankAccount =  createSavingsAccount(person, accountNumber, pinNumber, amount, interestRate);
		}
		return addAccount(accountNumber, bankAccount);
	}

	private BankAccount createCheckingAccount(Person person, Long accountNumber, int pinNumber, double cardLimit, double amount){
		return new CheckingAccount(person, accountNumber, pinNumber, amount, cardLimit);
	}

	private BankAccount createSavingsAccount(Person person, Long accountNumber, int pinNumber, double amount, double interestRate){
		return new SavingsAccount(person, accountNumber, pinNumber, amount, interestRate);
	}

	public boolean authenticateUser(Long accountNumber, int pinNumber) throws Exception{
		if(!isAccountExist(accountNumber)){
			throw new Exception("Account not Exist");
		}
		return this.accounts.get(accountNumber).getPinNumber() == pinNumber;

	}

	public BankAccount getBankAccount(Long accountNumber) throws Exception{
		if(!isAccountExist(accountNumber)){
			throw new Exception("Account not Exist");
		}
		return this.accounts.get(accountNumber);
	}

	private boolean isAccountExist(Long accountNumber){
		return accounts.containsKey(accountNumber);
	}

	private BankAccount addAccount(Long accountNumber, BankAccount account) {
		this.accounts.put(accountNumber, account);
		return this.accounts.get(accountNumber);
	}

	public double getBalance(Long accountNumber){
		return accounts.get(accountNumber).getBalance();
	}
}
