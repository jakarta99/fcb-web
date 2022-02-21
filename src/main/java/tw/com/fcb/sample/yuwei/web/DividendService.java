package tw.com.fcb.sample.yuwei.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DividendService {

	
	public void runCrud() throws SQLException {
		
		DividendRepository dividendRepository = new DividendRepository();
		List<Dividend> dividendList = new ArrayList<Dividend>();
		Dividend dividendData = setDividend();
		System.out.println("dividendData1:"+dividendData);
		// 1.run findAll
		dividendList = dividendRepository.findAll();
		//2.List<Dividend> dividend.size = 0
		System.out.println("Insert資料前，資料庫內之資料總共 = " + dividendList.size()+ "筆");
		//3.insert
		dividendRepository.insertData(dividendData);
		dividendList = dividendRepository.findAll();
		System.out.println("Insert資料後，資料庫內之資料總共 = " + dividendList.size()+ "筆");
		
		Dividend dividendInsertData; 
		//4.getById(the Dividend.getId());
		System.out.println("dividendData2:"+dividendData);
		System.out.println("dividendData.getId()" + dividendData.getId());
		dividendInsertData = dividendRepository.getById(dividendData.getId());
		System.out.println(dividendInsertData);
		//5.update cash_dividend
		dividendRepository.update(dividendInsertData);
		dividendInsertData = dividendRepository.getById(dividendData.getId());
		System.out.println(dividendInsertData);
		//6.delete dividend by allocation of annual
		dividendRepository.deleteById(dividendData.getId());
		
		
		
	}
	
	public List<Dividend> findAll() throws SQLException {
		DividendRepository dividendRepository = new DividendRepository();
		List<Dividend> dividendList = dividendRepository.findAll();
		
		return dividendList;
	}
	
	
    public Dividend setDividend(){
//    	Dividend dividend = new Dividend();
//		dividend.setAllocationOfAnnual(111);
//		dividend.setCashDividend(2);
//		dividend.setStockDividend(2);
//		dividend.setTotal(4);
//		dividend.setTotalCashDividendUnit(40.24);
//		dividend.setShareholdingRatio(13.11);
//		dividend.setIssuingCompany("富邦金控");
//		dividend.setIndustry(IndustryEnum.financial);
    	Dividend dividend = Dividend.builder()
    			.allocationOfAnnual(111)
    			.cashDividend(2)
    			.stockDividend(2)
    			.total(4)
    			.totalCashDividendUnit(40.24)
    			.shareholdingRatio(13.11)
    			.issuingCompany("富邦金控")
    			.industry(IndustryEnum.financial)
    			.build();
    	
        return dividend;
    }
	
	public List<Dividend> loadFromFile() throws IOException, SQLException {

		// 讀檔
		String openApiFile = "C:\\fcb-workspace\\fcb-io\\fubon-opendate.csv";
		BufferedReader br = new BufferedReader(new FileReader(openApiFile));
		String lineData;
		int idx = 0;
		List<Dividend> result = new ArrayList<Dividend>();
		DividendRepository dividendRepository = new DividendRepository();
		Connection conn = dividendRepository.getConnection();
		// 於 try 關鍵字後的小括號中進行 resource 宣告及初始化
		// 在 try 後方小括號初始化的資源會在離開 try 區塊時自動呼叫 close()
		try (conn;br) {
			// 先清空資料
			dividendRepository.delete();
			// 迴圈讀一行資料
			while ((lineData = br.readLine()) != null) {
				idx = idx + 1;
				if (idx == 1)
					continue;
				// split 切割
				String[] data = lineData.split(",");
				// 設值
				Dividend dividend = new Dividend();
				dividend.setAllocationOfAnnual(Integer.parseInt(data[0]));
				dividend.setCashDividend(Double.parseDouble(data[1]));
				dividend.setStockDividend(Double.parseDouble(data[2]));
				dividend.setTotal(Double.parseDouble(data[3]));
				dividend.setTotalCashDividendUnit(Double.parseDouble(data[4]));
				dividend.setShareholdingRatio(Double.parseDouble(data[5]));
				dividend.setIssuingCompany(data[6]);
				dividend.setIndustry(IndustryEnum.valueOf(data[7]));
				System.out.println(dividend);
				// 放到 List 之中
				result.add(dividend);

				//insert資料進DB
				dividendRepository.insert(dividend, conn);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return result;
	}

	public void getIndustryByIssuingCompany(String issuingCompanies[]) throws SQLException {
		DividendRepository dividendRepository = new DividendRepository();
		Connection conn = dividendRepository.getConnection();
		List<Dividend> result = new ArrayList<Dividend>();
		for(String issuingCompany:issuingCompanies) {
			result = dividendRepository.getIndustryByIssuingCompany(issuingCompany,conn);
				switch(result.get(0).getIndustry()){
					case financial:
						System.out.println(IndustryEnum.financial.getCode()+IndustryEnum.financial.getDescription());
						break;
					case semiconductor:
						System.out.println(IndustryEnum.semiconductor.getCode()+IndustryEnum.semiconductor.getDescription());
						break;
					case steel:
						System.out.println(IndustryEnum.steel.getCode()+IndustryEnum.semiconductor.getDescription());
						break;
					case biomedical:
						System.out.println(IndustryEnum.biomedical.getCode()+IndustryEnum.biomedical.getDescription());
						break;
			        default:
			            break;
			}
		}
		
		
	}
	public void fileWrite() throws IOException, SQLException {

		
		// 寫檔
		String writeFile = "C:\\fcb-workspace\\fcb-io\\write-file.txt";
		BufferedWriter bufwriter = new BufferedWriter(new FileWriter(writeFile,false));
		DividendRepository dividendRepository = new DividendRepository();
		Connection conn = dividendRepository.getConnection();
		String issuingCompany ="富邦金控";
		try (bufwriter; conn) {
			List<Dividend> result = new ArrayList<Dividend>();
			result = dividendRepository.getByIssuingCompany(issuingCompany, conn);
			bufwriter.write("AllocationOfAnnual  "+"CashDividend  "+"StockDividend  "+"Total  "+"TotalCashDividendUnit  "+"ShareholdingRatio  "+"IssuingCompany");
			bufwriter.newLine();
			for(Dividend dividend :result) {
//			System.out.println("Write " + dividend);
			bufwriter.write(dividend.getAllocationOfAnnual()+"   ");
			bufwriter.write(String.valueOf(dividend.getCashDividend())+"   ");
			bufwriter.write(String.valueOf(dividend.getStockDividend())+"   ");
			bufwriter.write(String.valueOf(dividend.getTotal()+"   "));
			bufwriter.write(String.valueOf(dividend.getTotalCashDividendUnit())+"   ");
			bufwriter.write(String.valueOf(dividend.getShareholdingRatio())+"   ");
			bufwriter.write(dividend.getIssuingCompany());
			bufwriter.newLine();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		
	}

	public Dividend findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		DividendRepository dividendRepository = new DividendRepository();
		Dividend dividend= dividendRepository.getById(id);
		return dividend;
	}
	
	
}
