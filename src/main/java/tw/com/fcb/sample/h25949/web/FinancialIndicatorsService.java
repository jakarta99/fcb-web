package tw.com.fcb.sample.h25949.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinancialIndicatorsService {

	public List<FinancialIndicators> FinancialIndicatorsFile() throws IOException {
		// 讀取金融指標檔案
		String openFile = "d:\\A17000000J.csv";
		FileReader fr = new FileReader(openFile);
		BufferedReader bfr = new BufferedReader(fr);

		String str;
		int idx = 0;
		List<FinancialIndicators> result = new ArrayList<FinancialIndicators>();

		while ((str = bfr.readLine()) != null) { // 每次讀取一行，直到檔案結束
			idx = idx + 1;
			if (idx == 1)
				continue;

			System.out.println(str);
			String[] strData = str.split(",");

			// 轉成資料物件
			FinancialIndicators financialIndicators = new FinancialIndicators();
			financialIndicators.setYear(Integer.valueOf(strData[0]));
			financialIndicators.setExchangeRate(new BigDecimal(strData[1]));
			financialIndicators.setForeign(new BigDecimal(strData[2]));
			financialIndicators.setStockIndex(new BigDecimal(strData[3]));
			financialIndicators.setStockAmount(new BigDecimal(strData[4]));

			// 放至集合物件
			System.out.println(financialIndicators);
			result.add(financialIndicators);
		}

		fr.close();
		bfr.close();
		return result;
	}

	public void insert(List<FinancialIndicators> result) throws SQLException {
		int count = new FinancialIndicatorsRepository().insert(result);
		System.out.println("目前已寫入 " + count + "筆資料");
	}

	public List<FinancialIndicators> findAll() throws SQLException {
		List<FinancialIndicators> allFinancialIndicators = new FinancialIndicatorsRepository().findAll();
		
		return allFinancialIndicators;
	}

	public void getById() throws SQLException {
		System.out.print("輸入年份：");
		Scanner scannerById = new Scanner(System.in);
		int optionById = scannerById.nextInt();
		FinancialIndicators FinancialIndicator = new FinancialIndicatorsRepository().getById(optionById);

		if (FinancialIndicator.getYear() == 0) {
			System.out.println("檔案無符合資料");
		} else {
			System.out.println(FinancialIndicator);
		}
		scannerById.close();
	}

	public void update() throws SQLException {
		FinancialIndicators financialIndicator = new FinancialIndicators();

		System.out.print("輸入年份：");
		Scanner scannerById = new Scanner(System.in);
		financialIndicator.setYear(scannerById.nextInt());

		System.out.print("輸入匯率：");
		Scanner scannerRate = new Scanner(System.in);
		financialIndicator.setExchangeRate(new BigDecimal(scannerRate.nextBigInteger()));

		System.out.print("輸入外匯存底：");
		Scanner scannerForeign = new Scanner(System.in);
		financialIndicator.setForeign(new BigDecimal(scannerForeign.nextBigInteger()));

		System.out.print("輸入證券發行量加權股價指數：");
		Scanner scannerStockIndex = new Scanner(System.in);
		financialIndicator.setStockIndex(new BigDecimal(scannerStockIndex.nextBigInteger()));

		System.out.print("輸入證券成交值：");
		Scanner scannerStockAmount = new Scanner(System.in);
		financialIndicator.setStockAmount(new BigDecimal(scannerStockAmount.nextBigInteger()));

		int count = new FinancialIndicatorsRepository().update(financialIndicator);

		if (count == 0) {
			System.out.println("檔案無符合資料");
		} else {
			System.out.println("更新" + count + "筆完成");
		}

		scannerById.close();
		scannerRate.close();
		scannerForeign.close();
		scannerStockIndex.close();
		scannerStockAmount.close();
	}

	public void delete() throws SQLException {
		System.out.print("輸入年份：");
		Scanner scannerById = new Scanner(System.in);
		int optionById = scannerById.nextInt();
		int count = new FinancialIndicatorsRepository().delete(optionById);

		if (count == 0) {
			System.out.println("檔案無符合資料");
		} else {
			System.out.println("刪除" + count + "筆成功");
		}
		scannerById.close();
	}
	
	public enum option {
	    a,b, c, d,e
	}
}
