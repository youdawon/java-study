package design.bank;

public interface TransactionInterface {

	public void updateTransactionHistory(Long accountId, String type, double amount, double balance);
	public History getTransactionHistory(Long accountId, Long historyId) throws Exception;
}
