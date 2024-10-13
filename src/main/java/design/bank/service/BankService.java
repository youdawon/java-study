package main.design.bank.service;

import java.util.List;

import main.design.bank.account.BankAccount;
import main.design.bank.account.CheckingAccount;
import main.design.bank.account.Person;
import main.design.bank.account.SavingsAccount;
import main.design.bank.transaction.History;
import main.design.bank.util.Constant;

public class BankService {

	private AccountService accountService;
	private TransactionService transactionService;
	private AccountHistoryService accountHistoryService;

	public BankService(AccountService accountService, TransactionService transactionService, AccountHistoryService accountHistoryService){
		this.accountService = accountService;
		this.transactionService = transactionService;
		this.accountHistoryService = accountHistoryService;
	}

	public BankAccount createAccount(Person person, int pinNumber, double amount, double cardLimit, double interestRate,  boolean isChecking) throws Exception {
		BankAccount bankAccount = accountService.createAccount(person, pinNumber, amount, cardLimit, interestRate, isChecking);
		accountHistoryService.updateAccountHistory(bankAccount, Constant.TransactionType.CREATE_ACCOUNT, amount, accountService.getBalance(bankAccount.getAccountNumber()));
		return bankAccount;
	}

	public boolean authenticateUser(Long accountNumber, int pinNumber) throws Exception{
		return accountService.authenticateUser(accountNumber, pinNumber);
	}

	public void transfer(Long from, Long to, double amount) throws Exception{
		BankAccount fromAccount = accountService.getBankAccount(from);
		BankAccount toAccount = accountService.getBankAccount(to);
		transactionService.transfer(fromAccount, toAccount, amount);
		accountHistoryService.updateTransferHistory(fromAccount, Constant.TransactionType.TRANSFER, amount, fromAccount.getBalance(),toAccount.getAccountNumber());
	}

	public void deposit(Long accountNumber, double amount) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		transactionService.deposit(bankAccount, amount);
		accountHistoryService.updateAccountHistory(bankAccount, Constant.TransactionType.DEPOSIT, amount, bankAccount.getBalance());
	}

	public void withdraw(Long accountNumber, double amount) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		transactionService.withdraw(bankAccount, amount);
		accountHistoryService.updateAccountHistory(bankAccount, Constant.TransactionType.WITHDRAWAL, amount, bankAccount.getBalance());
	}

	public void useCheckCard(Long accountNumber, double amount) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		if (bankAccount instanceof CheckingAccount) {
			transactionService.useCheckCard(((CheckingAccount)bankAccount), amount);
		} else {
			throw new Exception("This account does not support card usage.");
		}
		accountHistoryService.updateAccountHistory(bankAccount, Constant.TransactionType.CARD_USED, amount, bankAccount.getBalance());
	}

	public double applyInterest(Long accountNumber) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		double interest = 0;
		if (bankAccount instanceof SavingsAccount) {
			transactionService.applyInterest(((SavingsAccount)bankAccount));
		} else {
			throw new Exception("This account does not support card usage.");
		}
		accountHistoryService.updateAccountHistory(bankAccount, Constant.TransactionType.INTEREST_APPLIED, interest, bankAccount.getBalance());
		return interest;
	}

	public List<History> getAccountHistoryList(Long accountNumber, Long historyId) throws Exception{
		BankAccount bankAccount = accountService.getBankAccount(accountNumber);
		return accountHistoryService.getAccountHistoryList(bankAccount, historyId);
	}
}
