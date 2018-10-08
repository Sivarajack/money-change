package com.test.moneyChange.model;

import java.time.LocalTime;

public class Transaction {
	
	private Currency baseCurrency;
	private Currency wantedCurrency;
	private int amountInBaseCurrency;
	private ClientType clientType;
	public Transaction() {
		super();
	}
	public Transaction(Currency baseCurrency, Currency wantedCurrency, LocalTime transactionTime) {
		super();
		this.baseCurrency = baseCurrency;
		this.wantedCurrency = wantedCurrency;
		this.transactionTime = transactionTime;
	}
	private LocalTime transactionTime;
	public Currency getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public Currency getWantedCurrency() {
		return wantedCurrency;
	}
	public void setWantedCurrency(Currency wantedCurrency) {
		this.wantedCurrency = wantedCurrency;
	}
	public int getAmountInBaseCurrency() {
		return amountInBaseCurrency;
	}
	public void setAmountInBaseCurrency(int amountInBaseCurrency) {
		this.amountInBaseCurrency = amountInBaseCurrency;
	}
	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	public LocalTime getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	
}
