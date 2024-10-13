package main.design.bank.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import main.design.bank.account.BankAccount;
import main.design.bank.transaction.History;
import main.design.bank.util.Constant;

public class AccountHistoryService {


	public synchronized void updateAccountHistory(BankAccount bankAccount, Constant.TransactionType type, double amount, double balance) {
		Long historyId = bankAccount.getHistoryId().incrementAndGet();
		bankAccount.getAccountHistory().put(historyId, new History(historyId, type, amount, balance));
	}

	public synchronized void updateTransferHistory(BankAccount bankAccount, Constant.TransactionType type, double amount, double balance, Long creditedAccountNumber) {
		Long historyId = bankAccount.getHistoryId().incrementAndGet();
		bankAccount.getAccountHistory().put(historyId, new History(historyId, type, amount, balance, creditedAccountNumber));
 	}

	public ArrayList<History> getAccountHistoryList(BankAccount bankAccount, Long historyId) throws Exception {
		if (historyId == null) {
			return new ArrayList<History>(bankAccount.getAccountHistory().values());
		}
		History history = bankAccount.getAccountHistory().get(historyId);
		if(history == null){
			throw  new Exception("history Id : " + historyId + " not found.");
		}
		return new ArrayList<History>(Collections.singleton(history));
	}
}
