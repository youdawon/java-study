package design.bank;

/**
 * 이자율 필드를 추가하세요: private double interestRate;
 *
 * 생성자에 이자율을 추가하세요: 생성자를 수정해서 이자율을 받아 계좌를 생성할 때 설정하도록 하세요.
 *
 * 이자를 적용하는 메서드를 만들어 보세요:
 * applyInterest()라는 메서드를 작성해서 현재 잔액에 이자율을 적용한 후, 그 이자를 계좌에 입금하도록 하세요.
 *
 * 이자율을 설정하고 조회하는 메서드를 추가하세요:
 * setInterestRate()와 getInterestRate() 메서드를 작성해서 이자율을 변경하거나 확인할 수 있게 하세요.
 */
public class SavingsAccount extends BankAccount{

	private double interestRate;

	public SavingsAccount(String firstName, String lastName, int pinNumber, double amount, double interestRate) {
		super(firstName, lastName, pinNumber, amount);
		this.interestRate = interestRate;
	}

	public void setInterestRate(double interestRate){
		this.interestRate = interestRate;
	}

	public double getInterestRate(){
		return this.interestRate;
	}

	public void applyInterest(){
		double interest = getBalance() * this.interestRate;
		deposit(interest);
	}
}
