package com.test.moneyChange.model;

public class Report {
	private Currency baseCurrency;
	private Currency wantedCurrency;
	private float amountInBaseCurrency;
	private float standardRate;
	private float finalRate;
	private float profitInWantedCurrency;
	private float profitInSGD;
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
	public double getAmountInBaseCurrency() {
		return amountInBaseCurrency;
	}
	public void setAmountInBaseCurrency(float amountInBaseCurrency) {
		this.amountInBaseCurrency = amountInBaseCurrency;
	}
	public double getStandardRate() {
		return standardRate;
	}
	public void setStandardRate(float standardRate) {
		this.standardRate = standardRate;
	}
	public double getFinalRate() {
		return finalRate;
	}
	public void setFinalRate(float finalRate) {
		this.finalRate = finalRate;
	}
	public double getProfitInWantedCurrency() {
		return profitInWantedCurrency;
	}
	public void setProfitInWantedCurrency(float profitInWantedCurrency) {
		this.profitInWantedCurrency = profitInWantedCurrency;
	}
	public double getProfitInSGD() {
		return profitInSGD;
	}
	public void setProfitInSGD(float profitInSGD) {
		this.profitInSGD = profitInSGD;
	}
}