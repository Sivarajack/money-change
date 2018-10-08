package com.test.moneyChange.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.test.moneyChange.model.ClientType;
import com.test.moneyChange.model.Currency;
import com.test.moneyChange.model.MarkUp;
import com.test.moneyChange.model.Rates;
import com.test.moneyChange.model.Transaction;

public class MoneyChangeImpl implements MoneyChnageInterface {

	public List<Transaction> createTransactionList(List<Transaction> objectList, File fileLocation) {
		try {
			BufferedReader brBufferedReader = new BufferedReader(new FileReader((fileLocation)));
			String line;
			try {
				String headerLine = brBufferedReader.readLine();
				while ((line = brBufferedReader.readLine()) != null) {

					String[] txnArray = line.split(",");
					Transaction transactionObject = new Transaction();
					transactionObject.setBaseCurrency(Currency.valueOf(txnArray[0]));
					transactionObject.setWantedCurrency(Currency.valueOf(txnArray[1]));
					transactionObject.setAmountInBaseCurrency(Integer.parseInt(txnArray[2]));
					transactionObject.setClientType(ClientType.valueOf(txnArray[3]));
					transactionObject.setTransactionTime(LocalTime.parse(txnArray[4]));
					objectList.add(transactionObject);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objectList;
	}

	public void generateReport(String reportString, String reportLocation) {
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new FileWriter(reportLocation));
			printWriter.write(reportString);
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getMarkUp(Map<MarkUp, Integer> markUpMap, double transactionAmount) {
		int markUp = 0;
		Iterator itr = markUpMap.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry m = (Entry) itr.next();
			if (m.getKey().equals(transactionAmount)) {
				markUp = (Integer) m.getValue();
				break;
			}
			;
		}
		return markUp;
	}

	public Map<MarkUp, Integer> createMarkupMap(Map<MarkUp, Integer> markupMap, File fileLocation) {
		try {
			BufferedReader brBufferedReader = new BufferedReader(new FileReader((fileLocation)));
			String line;
			try {
				String headerLine = brBufferedReader.readLine();
				while ((line = brBufferedReader.readLine()) != null) {
					String[] mrkArray = line.split(",");
					MarkUp markUpObject = new MarkUp();
					markUpObject.setStartRange(Integer.parseInt(mrkArray[0]));
					markUpObject.setEndRange(
							mrkArray[1].equals("infinity") ? Integer.MAX_VALUE : Integer.parseInt(mrkArray[1]));
					markupMap.put(markUpObject, Integer.parseInt(mrkArray[2]));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return markupMap;
	}

	public Map<LocalTime, List<Rates>> createRateMap(Map<LocalTime, List<Rates>> rateMap, File fileLocation) {
		try {
			BufferedReader brBufferedReader = new BufferedReader(new FileReader((fileLocation)));
			String line;
			try {
				while ((line = brBufferedReader.readLine()) != null) {
					String[] rateArray = line.split(",");
					Rates rateObj = new Rates();
					rateObj.setBaseCurrency(Currency.valueOf(rateArray[0]));
					rateObj.setWantedCurrency(Currency.valueOf(rateArray[1]));
					rateObj.setRate(Float.parseFloat(rateArray[2]));
					rateObj.setEffectiveTime(LocalTime.parse(rateArray[3]));
					if (rateMap.containsKey(LocalTime.parse(rateArray[3]))) {
						rateMap.get(LocalTime.parse(rateArray[3])).add(rateObj);
					} else {
						rateMap.put(LocalTime.parse(rateArray[3]), new ArrayList<Rates>(Arrays.asList(rateObj)));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public float getRate(Map<LocalTime, List<Rates>> rateMap, Transaction transaction) {
		float rate = 0;
		Iterator itr = rateMap.keySet().iterator();
		while (itr.hasNext()) {
			LocalTime time = (LocalTime) itr.next();
			if (transaction.getTransactionTime().isBefore(time)) {
				List<Rates> ratesForTime = rateMap.get(time);
				for (Rates rateEntry : ratesForTime) {
					if (rateEntry.equals(transaction)) {
						rate = rateEntry.getRate();
						break;
					}
				}
			}
			if(rate!=0) {
				break;
			}
		}
		return rate;
	}

}
