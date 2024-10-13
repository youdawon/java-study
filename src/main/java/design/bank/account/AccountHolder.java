package main.design.bank.account;

public class AccountHolder {

	private final int id;

	public AccountHolder(int idNumber) {
		this.id = idNumber;
	}

	public int getId(){
		return this.id;
	}
}
