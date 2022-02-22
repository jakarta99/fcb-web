package tw.com.fcb.sample.h25949.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FinancialIndicatorsApp {
	public static void main(String[] args) {
		System.out.println("1.匯入每年國內主要金融指標檔案，2.顯示每年國內主要金融指標，3.選擇年份顯示國內主要金融指標，4.選擇年份更新國內主要金融指標，5.選擇年份刪除資料");
		System.out.print("輸入選項：");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();

		try {
			switch (option) {
			case 1:
				// insert檔案資料-每年國內主要金融指標
				List<FinancialIndicators> result = new FinancialIndicatorsService().FinancialIndicatorsFile();
				System.out.println("檔案內容有 " + result.size() + "筆資料");
				new FinancialIndicatorsService().insert(result);
				break;
				
			case 2:
				// Select所有-每年國內主要金融指標
				new FinancialIndicatorsService().findAll();
				break;
			
			case 3:
				// 依年份Select-每年國內主要金融指標
				new FinancialIndicatorsService().getById();		
				break;
				
			case 4:
				// 依年份Update-每年國內主要金融指標
				new FinancialIndicatorsService().update();
				break;
				
			case 5:
				// 依年份Delete-每年國內主要金融指標
				new FinancialIndicatorsService().delete();
				break;
				
			default:
				 System.out.println("輸入選項有誤");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("找不到檔案");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("檔案已毀損");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("資料庫連線有誤");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		scanner.close();
	}
}
