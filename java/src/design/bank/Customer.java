package design.bank;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 고객 관리 시스템 구현을 통해 얻을 수 있는 OOP 개념:
 * 객체 간의 관계: 고객이 여러 개의 계좌를 소유하는 관계를 구현하면서 객체 간 상호작용을 자연스럽게 익힐 수 있습니다.
 * 집합 관리: 여러 개의 계좌를 리스트 또는 맵으로 관리하는 로직을 구현하면, 컬렉션 활용 능력을 기를 수 있습니다.
 * 추가 기능의 기초: 거래 내역, 이체, 알림 시스템 등 다양한 기능들을 추가할 때 고객 클래스가 핵심 역할을 하므로 이후 확장이 용이해집니다.
 * 다음 단계:
 * Customer 클래스를 만들고, 해당 클래스에서 고객이 여러 개의 계좌를 생성하고 조회할 수 있는 기능을 추가하세요.
 * Customer 클래스에 리스트 또는 맵을 사용해 여러 BankAccount 객체를 관리하고, 고객이 계좌를 추가하고 조회하는 메서드를 구현하세요.
 */
public class Customer {

	ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
	AtomicLong id = new AtomicLong(0);

	public Long createCheckingAccount(String firstName, String lastName, int pinNumber, double amount){
		long accountId = this.id.incrementAndGet();
		this.accounts.put(accountId, new CheckingAccount(firstName, lastName, pinNumber, accountId, 10000));
		return accountId;
	}

	public Long createSavingAccount(String firstName, String lastName, int pinNumber, double amount, double interestRate){
		long accountId = this.id.incrementAndGet();
		this.accounts.put(accountId, new SavingsAccount(firstName, lastName, pinNumber, accountId, interestRate));
		return accountId;
	}

	public boolean checkPinNumber(Long accountId, int pinNumber) throws Exception{
		if(!accounts.containsKey(accountId)){
			throw new Exception("Account not Exist");
		}
		return this.accounts.get(accountId).getPinNumber() == pinNumber;

	}

	public BankAccount getBankAccount(Long accountId, int pinNumber) throws Exception{
		if(!accounts.containsKey(accountId)){
			throw new Exception("Account not Exist");
		}
		if(!checkPinNumber(accountId, pinNumber)){
			throw new Exception("pinNumber is wrong");
		}
		return this.accounts.get(accountId);
	}
}
