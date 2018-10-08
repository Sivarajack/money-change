package com.test.moneyChange.model;

public class MarkUp {
	private int startRange;
	private int endRange;
	private int markup;
	public int getMarkup() {
		return markup;
	}
	public void setMarkup(int markup) {
		this.markup = markup;
	}
	public int getStartRange() {
		return startRange;
	}
	public void setStartRange(int startRange) {
		this.startRange = startRange;
	}
	public int getEndRange() {
		return endRange;
	}
	public void setEndRange(int endRange) {
		this.endRange = endRange;
	}
	@Override
	public int hashCode(){
		return this.startRange;
	}
	
	@Override
	public boolean equals(Object obj) {
		double amount = (Double) obj;
		return this.startRange <= amount && amount < this.endRange;
	}
}
