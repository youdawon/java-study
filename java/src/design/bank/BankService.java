package design.bank;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BankService {

	ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
	AtomicLong id = new AtomicLong(0);

	public BankAccount createAccount(String firstName, String lastName, int pinNumber, double amount, double cardLimit, double interestRate,  boolean isChecking){
		long accountId = this.id.incrementAndGet();
		Transaction transaction = new Transaction();
		BankAccount bankAccount;
		if(isChecking){
			bankAccount = createCheckingAccount(accountId , firstName, lastName, pinNumber, cardLimit, amount, transaction);
		} else {
			bankAccount =  createSavingsAccount(accountId, firstName, lastName, pinNumber, amount, interestRate, transaction);
		}
		return addAccount(accountId, bankAccount);
	}

	private BankAccount createCheckingAccount(Long accountId, String firstName, String lastName, int pinNumber, double cardLimit, double amount, Transaction transaction){
		return new CheckingAccount(accountId, firstName, lastName, pinNumber, amount, cardLimit, transaction);
	}

	private BankAccount createSavingsAccount(Long accountId, String firstName, String lastName, int pinNumber, double amount, double interestRate, Transaction transaction){
		return new SavingsAccount(accountId, firstName, lastName, pinNumber, amount, interestRate, transaction);
	}

	public boolean checkPinNumber(Long accountId, int pinNumber) throws Exception{
		if(!isAccountExist(accountId)){
			throw new Exception("Account not Exist");
		}
		return this.accounts.get(accountId).getPinNumber() == pinNumber;

	}

	public BankAccount getBankAccount(Long accountId, int pinNumber) throws Exception{
		if(!isAccountExist(accountId)){
			throw new Exception("Account not Exist");
		}
		if(!checkPinNumber(accountId, pinNumber)){
			throw new Exception("pinNumber is wrong");
		}
		return this.accounts.get(accountId);
	}

	private boolean isAccountExist(Long accountId){
		return accounts.containsKey(accountId);
	}

	private BankAccount addAccount(Long accountId, BankAccount account) {
		this.accounts.put(accountId, account);
		return this.accounts.get(accountId);
	}
}
