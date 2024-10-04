package design.bank;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Transaction implements TransactionInterface{

	ConcurrentHashMap<Long, Map<Long, History>> transactionHistory;
	AtomicLong id;

	public Transaction(){
		this.transactionHistory = new ConcurrentHashMap<>();
		this.id = new AtomicLong(0);
	}

	@Override
	public void updateTransactionHistory(Long accountId, String type, double amount, double balance) {
		Long historyId = id.incrementAndGet();
		transactionHistory.computeIfAbsent(accountId, k -> new LinkedHashMap<>());
		transactionHistory.get(accountId).put(historyId, new History(historyId, type, amount, balance));

	}

	@Override
	public History getTransactionHistory(Long accountId, Long historyId) throws Exception {
		if(!transactionHistory.containsKey(accountId)){
			throw  new Exception("Account not exist");
		}
		if(!transactionHistory.get(accountId).containsKey(historyId)){
			throw  new Exception("History not exist");
		}

		return transactionHistory.get(accountId).get(historyId);
	}
}

