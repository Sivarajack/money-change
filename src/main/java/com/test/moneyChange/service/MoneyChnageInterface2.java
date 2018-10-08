package com.test.moneyChange.service;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.test.moneyChange.model.Currency;
import com.test.moneyChange.model.MarkUp;
import com.test.moneyChange.model.Rates;
import com.test.moneyChange.model.Report;
import com.test.moneyChange.model.Transaction;

public interface MoneyChnageInterface2 {
	public List<Transaction> createTransactionList(List<Transaction> objectList, File fileLocation);
	public Map<MarkUp, Integer> createMarkupMap(Map<MarkUp, Integer> markUpMap, File fileLocation);
	public Map<LocalTime, List<Rates>> createRateMap(Map<LocalTime, List<Rates>> rateMap, File fileLocation);
	public int getMarkUp(Map<MarkUp, Integer> markUpMap, double transactionAmount);
	public float getRate(Map<LocalTime, List<Rates>> rateMap, Transaction transaction);
	public void generateReport(String reportString, String reportLocation);
	}
