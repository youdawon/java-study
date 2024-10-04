package design.bank;

public class CheckingAccount extends BankAccount{

	private double cardLimit;
	private Transaction transaction;

	public CheckingAccount(Long accountId, String firstName, String lastName, int pinNumber, double amount, double cardLimit, Transaction transaction){
		super(accountId, firstName, lastName, pinNumber, amount);
		this.cardLimit = cardLimit;
		this.transaction = transaction;
	}

	public double useCheckCard(double amount) throws Exception{
		synchronized(this) {
			if (amount > this.cardLimit) {
				throw new Exception("the amount is more than card limit.");
			}
			if (getBalance() < amount) {
				throw new Exception("Not enough balance in an account.");
			}
			withdraw(amount);
		}
		transaction.updateTransactionHistory(getAccountId(), "withdraw", amount, getBalance());

		return getBalance();
	}

	@Override
	public synchronized void deposit(double amount) {
		synchronized (this) {
			super.deposit(amount);
		}
		transaction.updateTransactionHistory(getAccountId(), "deposit", amount, getBalance());
	}
}
