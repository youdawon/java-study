package design.bank;

public interface BankAccountInterface {

	public void deposit(double amount);

	public void withdraw(double amount) throws Exception;

	public double getBalance();

	public int getPinNumber();

	public String getFirstName();

	public String getLastName();
}
