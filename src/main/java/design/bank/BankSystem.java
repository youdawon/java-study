package design.bank;

import design.bank.account.BankAccount;
import design.bank.account.Person;
import design.bank.service.AccountHistoryService;
import design.bank.service.AccountService;
import design.bank.service.BankService;
import design.bank.service.TransactionService;
import design.bank.transaction.History;

public class test {
	public static void main(String[] args) throws  Exception{
		try {
			BankService bankService = new BankService(new AccountService(), new TransactionService(),
				new AccountHistoryService());
			BankAccount bankAccount = bankService.createAccount(new Person("Dawon", "You", 123), 1234, 10000, 100000, 0,
				false);
			bankService.deposit(bankAccount.getAccountNumber(), 10);
			bankService.withdraw(bankAccount.getAccountNumber(), 1234);
			bankService.applyInterest(bankAccount.getAccountNumber());
			BankAccount bankAccount2 = bankService.createAccount(new Person("Sumin", "You", 123), 1234, 100, 100000, 0,
				false);
			bankService.transfer(bankAccount.getAccountNumber(), bankAccount2.getAccountNumber(), 4000);
			for (History history : bankService.getAccountHistoryList(bankAccount.getAccountNumber(), null)) {
				System.out.println(history.toString());
			}
			System.out.println("--------------------------");
			for (History history : bankService.getAccountHistoryList(bankAccount2.getAccountNumber(), null)) {
				System.out.println(history.toString());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

