package design.bank;

import java.time.LocalDateTime;

public class History {

	private Long historyId;
	private String type;
	private double amount;
	private LocalDateTime localDateTime;
	private double balance;

	public History(Long historyId, String type, double amount, double balance){
		this.historyId = historyId;
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		localDateTime = LocalDateTime.now();
	}
}
