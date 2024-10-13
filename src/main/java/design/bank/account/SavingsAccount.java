package main.design.bank.account;

public class SavingsAccount extends BankAccount {

	private double interestRate;

	public SavingsAccount(Person person, Long accountId, int pinNumber, double amount, double interestRate) {
		super(person, accountId, pinNumber, amount);
		this.interestRate = interestRate;
	}

	public void setInterestRate(double interestRate){
		this.interestRate = interestRate;
	}

	public double getInterestRate(){
		return this.interestRate;
	}

}
