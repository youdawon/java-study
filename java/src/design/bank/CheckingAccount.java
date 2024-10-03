package design.bank;

public class CheckingAccount extends BankAccount{

	private double cardLimit;

	public CheckingAccount(String firstName, String lastName, int pinNumber, double amount, double cardLimit){
		super(firstName, lastName, pinNumber, amount);
		this.cardLimit = cardLimit;
	}

	public synchronized double useCheckCard(double amount) throws Exception{
		if(amount > this.cardLimit) {
			throw new Exception("the amount is more than card limit.");
		}
		if(getBalance() < amount){
			throw new Exception("Not enough balance in an account.");
		}
		withdraw(amount);
		return getBalance();
	}
}
