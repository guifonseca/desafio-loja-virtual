package br.com.lojavirtual.util;

public class Transaction {

	private static Transaction instance;
	private Long idTransaction = null;
	
	public static Transaction getInstance() {
		if (instance == null)
			instance = new Transaction();

		return instance;
	}
	
	public Long getIdTransaction() {
		synchronized (Transaction.class) {
			this.idTransaction = System.currentTimeMillis();
		}
		
		return this.idTransaction;
	}
}
