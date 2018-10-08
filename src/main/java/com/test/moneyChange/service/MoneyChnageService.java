package com.test.moneyChange.service;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.test.moneyChange.constants.Constants;
import com.test.moneyChange.model.ClientType;
import com.test.moneyChange.model.Currency;
import com.test.moneyChange.model.MarkUp;
import com.test.moneyChange.model.Rates;
import com.test.moneyChange.model.Transaction;

public class MoneyChnageService {
	MoneyChnageInterface moneyChange;

	public MoneyChnageService(MoneyChnageInterface moneyChange) {
		super();
		this.moneyChange = moneyChange;
	}
	public void generateMoneyChangeReport() {
		File transactionFile = new File(Constants.TRANSACTION_FILE);
		File rateFile = new File(Constants.RATES_FILE);
		File corprateMarkupFile = new File(Constants.MARKUP_CORPORATE);
		File individualMarkupFile = new File(Constants.MARKUP_INDIVIDUAL);
		File rateLocation = new File(Constants.RATES_FILE);
		//MoneyChnageInterface moneyChange = new MoneyChangemoneyChange();
					StringBuilder sb = new StringBuilder();
			sb.append(
					"BaseCurrency,WantedCurrency,AmountInBaseCurrency,StandardRate,FinalRate,ProfitInWantedCurrency,ProfitInSGD"
							+ '\n');
			List<Transaction> transactionList = new ArrayList<Transaction>();
			Map<MarkUp, Integer> corporateMarkUpMap = new HashMap<MarkUp, Integer>();
			Map<MarkUp, Integer> individualMarkUpMap = new HashMap<MarkUp, Integer>();
			Map<LocalTime, List<Rates>> rateMap = new TreeMap<LocalTime, List<Rates>>();
			moneyChange.createTransactionList(transactionList, transactionFile);
			moneyChange.createMarkupMap(corporateMarkUpMap, corprateMarkupFile);
			moneyChange.createMarkupMap(individualMarkUpMap, individualMarkupFile);
			moneyChange.createRateMap(rateMap, rateLocation);
			for (Transaction trn : transactionList) {
				double markUp = 0;
				float rate;
				float USDRate = 0;
				float SGDToUSDRate = 0;
				double baseAmountInUSD;
				double profitInSGD = 0;
				rate = moneyChange.getRate(rateMap, trn);
				if (!trn.getBaseCurrency().equals(Currency.USD)) {
					USDRate = moneyChange.getRate(rateMap,
							new Transaction(trn.getBaseCurrency(), Currency.USD, trn.getTransactionTime()));
					baseAmountInUSD = trn.getAmountInBaseCurrency() * USDRate;
				} else {
					baseAmountInUSD = trn.getAmountInBaseCurrency();
				}
				if (trn.getClientType() == ClientType.valueOf("Individual")) {
					markUp = moneyChange.getMarkUp(individualMarkUpMap, baseAmountInUSD);
				} else if (trn.getClientType() == ClientType.valueOf("Corporate")) {
					markUp = moneyChange.getMarkUp(corporateMarkUpMap, baseAmountInUSD);
				}
				double finalRate = new BigDecimal(rate * Double.valueOf(1 - (markUp / 10000)))
						.setScale(4, RoundingMode.HALF_UP).doubleValue();
				double profit = new BigDecimal((rate - finalRate) * trn.getAmountInBaseCurrency())
						.setScale(2, RoundingMode.HALF_UP).doubleValue();
				if (!trn.getWantedCurrency().equals(Currency.SGD)) {
					double SGDRate = moneyChange.getRate(rateMap,
							new Transaction(trn.getWantedCurrency(), Currency.SGD, trn.getTransactionTime()));
					profitInSGD = new BigDecimal(profit * SGDRate).setScale(2, RoundingMode.HALF_UP).doubleValue();
				} else {
					profitInSGD = profit;
				}
				sb.append(trn.getBaseCurrency().toString() + ',' + trn.getWantedCurrency().toString() + ','
						+ trn.getAmountInBaseCurrency() + ',' + rate + ',' + profit + ',' + profitInSGD + '\n');
			}
			moneyChange.generateReport(sb.toString(), Constants.REPORT_FILE);
			System.out.println("report Generated");
		
	}
}
