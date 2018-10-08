package com.test.moneyChange.model;

import java.time.LocalTime;

public class Rates {
	private Currency baseCurrency;
	private Currency wantedCurrency;
	private float rate;
	private LocalTime effectiveTime;
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
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public LocalTime getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(LocalTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	@Override
	public boolean equals(Object obj) {
		Transaction transaction = (Transaction) obj;
		return this.baseCurrency.equals(transaction.getBaseCurrency()) && this.wantedCurrency.equals(transaction.getWantedCurrency());
	}
}
