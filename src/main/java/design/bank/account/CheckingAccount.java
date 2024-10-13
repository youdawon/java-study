package main.design.bank.account;


public class CheckingAccount extends BankAccount {

	private double cardLimit;

	public CheckingAccount(Person person, Long accountNumber, int pinNumber, double amount, double cardLimit){
		super(person, accountNumber, pinNumber, amount);
		this.cardLimit = cardLimit;
	}

	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
}
