package com.test.moneyChange;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.test.moneyChange.constants.Constants;
import com.test.moneyChange.exception.BusinessException;
import com.test.moneyChange.exception.SystemException;
import com.test.moneyChange.model.ClientType;
import com.test.moneyChange.model.Currency;
import com.test.moneyChange.model.MarkUp;
import com.test.moneyChange.model.Rates;
import com.test.moneyChange.model.Transaction;
import com.test.moneyChange.service.MoneyChangeImpl;
import com.test.moneyChange.service.MoneyChnageInterface;
import com.test.moneyChange.service.MoneyChnageService;


public class App {
	public static void main(String[] args) {
		MoneyChnageService service = new MoneyChnageService(new MoneyChangeImpl());
		try {
			service.generateMoneyChangeReport();
		} catch (SystemException e) {
			System.out.println(e.getMessage());
			System.out.println("Report Generation Failed");
		} 
	}

}
