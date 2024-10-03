package design.bank;

public abstract class BankAccount {

	private String firstName;
	private String lastName;
	private int pinNumber;
	private double balance;

	public BankAccount(String firstName, String lastName, int pinNumber, double amount) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pinNumber = pinNumber;
		this.balance = amount;
	}

	public synchronized void deposit(double amount) {
		this.balance += amount;
	}

	public synchronized void withdraw(double amount) throws Exception{
		if(this.balance < amount){
			throw new Exception("Not enough balance in an account.");
		}
		this.balance -= amount;
	}

	public double getBalance(){
		return this.balance;
	}

	public int getPinNumber(){
		return this.pinNumber;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public String getLastName(){
		return this.lastName;
	}
}
