package tw.com.fcb.sample.yuwei.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DividendRepository {

	HikariDataSource ds;
	
	public DividendRepository(){
//		LocalDateTime startTime = LocalDateTime.now();
//		
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
//		config.setUsername("postgres");
//		config.setPassword("postgres");
//		config.addDataSourceProperty("minimumIdle", "10");
//		config.addDataSourceProperty("maximumPoolSize", "30");
//		
//		LocalDateTime stopTime = LocalDateTime.now();
//		Long diff = ChronoUnit.MILLIS.between(startTime, stopTime);
//		System.out.println("total "+diff+" msec");
//		this.ds = new HikariDataSource(config);
	}
	
	
	public Connection getConnection() throws SQLException {
		
//		return ds.getConnection();
//		Class.forName("org.postgresql.Driver");
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public List<Dividend> findAll() throws SQLException{
		LocalDateTime startTime = LocalDateTime.now();
		Connection conn = getConnection();
		LocalDateTime stopTime = LocalDateTime.now();
		Long diff = ChronoUnit.MILLIS.between(startTime, stopTime);
		System.out.println("findAll total "+diff+" msec");
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from dividend";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		List<Dividend> rsList = new ArrayList<Dividend>();
		Dividend dividend;
		while(rs.next()) {
			dividend = new Dividend();
			dividend.setAllocationOfAnnual(rs.getInt("allocation_of_annual"));
			dividend.setCashDividend(rs.getDouble("cash_dividend"));
			dividend.setStockDividend(rs.getDouble("stock_dividend"));
			dividend.setTotal(rs.getDouble("total"));
			dividend.setTotalCashDividendUnit(rs.getDouble("total_cash_dividend_Unit"));
			dividend.setShareholdingRatio(rs.getDouble("shareholding_ratio"));
			dividend.setIssuingCompany(rs.getString("issuing_company"));
			dividend.setIndustry(IndustryEnum.valueOf(rs.getString("industry")));
			rsList.add(dividend);
		}
		rs.close();
		stmt.close();
		return rsList;
	}
	
	public List<Dividend> getIndustryByIssuingCompany(String issuingCompany,Connection conn) throws SQLException {
		
		Statement stmt = conn.createStatement();
		String sqlCmd = "select distinct industry from dividend where issuing_company = '"+issuingCompany+"'";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		List<Dividend> rsList = new ArrayList<Dividend>();
		Dividend dividend;
		while(rs.next()) {
			dividend = new Dividend();
			dividend.setIndustry(IndustryEnum.valueOf(rs.getString("industry")));
			rsList.add(dividend);
		}
		
		return rsList;
	}
	
	
	public List<Dividend> getByIssuingCompany(String issuingCompany,Connection conn) throws SQLException{

		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from dividend where issuing_company = '"+issuingCompany+"'";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		List<Dividend> rsList = new ArrayList<Dividend>();
//		Dividend dividend  = new Dividend();
		Dividend dividend;
		while(rs.next()) {
//			dividend = new Dividend();
			dividend = Dividend.builder().build();
			dividend.setId(rs.getLong("id"));
			dividend.setAllocationOfAnnual(rs.getInt("allocation_of_annual"));
			dividend.setCashDividend(rs.getDouble("cash_dividend"));
			dividend.setStockDividend(rs.getDouble("stock_dividend"));
			dividend.setTotal(rs.getDouble("total"));
			dividend.setTotalCashDividendUnit(rs.getDouble("total_cash_dividend_Unit"));
			dividend.setShareholdingRatio(rs.getDouble("shareholding_ratio"));
			dividend.setIssuingCompany(rs.getString("issuing_company"));
			dividend.setIndustry(IndustryEnum.valueOf(rs.getString("industry")));
			System.out.println("Query " + dividend);
			rsList.add(dividend);
		}
		rs.close();
		stmt.close();
		return rsList;
	}
	
	
	public Dividend getById(Long id) throws SQLException {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from dividend where id = '"+id+"'";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		Dividend dividend = new Dividend();
		while(rs.next()) {
			dividend.setId(id);
			dividend.setAllocationOfAnnual(rs.getInt("allocation_of_annual"));
			dividend.setCashDividend(rs.getDouble("cash_dividend"));
			dividend.setStockDividend(rs.getDouble("stock_dividend"));
			dividend.setTotal(rs.getDouble("total"));
			dividend.setTotalCashDividendUnit(rs.getDouble("total_cash_dividend_Unit"));
			dividend.setShareholdingRatio(rs.getDouble("shareholding_ratio"));
			dividend.setIssuingCompany(rs.getString("issuing_company"));
			dividend.setIndustry(IndustryEnum.valueOf(rs.getString("industry")));
		}
		stmt.close();
		conn.close();
		return dividend;
		
	}
	
	
	public int insert(Dividend dividend,Connection conn) throws SQLException{
//		Statement stmt = conn.createStatement();
//		String sqlCmd = "INSERT INTO dividend values ("
//				+"'"+dividend.getAllocationOfAnnual()+"',"
//				+"'"+dividend.getCashDividend()+"',"
//				+"'"+dividend.getStockDividend()+"',"
//				+"'"+dividend.getTotal()+"',"
//				+"'"+dividend.getTotalCashDividendUnit()+"',"
//				+"'"+dividend.getShareholdingRatio()+"',"
//				+"'"+dividend.getIssuingCompany()+"')";
//		stmt.executeUpdate(sqlCmd);
		
		String sqlCmd = "INSERT INTO dividend (allocation_of_annual,cash_dividend,stock_dividend,total,total_cash_dividend_Unit,shareholding_ratio,issuing_company,industry)"
				+ "values (?, ?, ?, ?, ?, ?, ?,?) returning id ";
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
		pstmt.setInt(1, dividend.getAllocationOfAnnual());
		pstmt.setDouble(2, dividend.getCashDividend());
		pstmt.setDouble(3, dividend.getStockDividend());
		pstmt.setDouble(4, dividend.getTotal());
		pstmt.setDouble(5, dividend.getTotalCashDividendUnit());
		pstmt.setDouble(6, dividend.getShareholdingRatio());
		pstmt.setString(7, dividend.getIssuingCompany());
		pstmt.setObject(8, dividend.getIndustry(),java.sql.Types.OTHER);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			int id = rs.getInt("id");
			dividend.setId(Long.valueOf(id));
		} else {
			// do something if can't 
			 System.out.println("insert not success");
		}
		pstmt.clearParameters();
		pstmt.close();
		return -1;
	}
	
	public void insertData(Dividend dividend) throws SQLException {
		Connection conn = getConnection();
		String sqlCmd = "INSERT INTO dividend (allocation_of_annual,cash_dividend,stock_dividend,total,total_cash_dividend_Unit,shareholding_ratio,issuing_company,industry)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd,Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, dividend.getAllocationOfAnnual());
		pstmt.setDouble(2, dividend.getCashDividend());
		pstmt.setDouble(3, dividend.getStockDividend());
		pstmt.setDouble(4, dividend.getTotal());
		pstmt.setDouble(5, dividend.getTotalCashDividendUnit());
		pstmt.setDouble(6, dividend.getShareholdingRatio());
		pstmt.setString(7, dividend.getIssuingCompany());
		pstmt.setObject(8, dividend.getIndustry(),java.sql.Types.OTHER);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if(rs.next()) {
			int id = rs.getInt("id");
			dividend.setId(Long.valueOf(id));
		} else {
			// do something if can't 
			 System.out.println("insert not success");
		}
		pstmt.close();
		conn.close();
		
	}
	
	
	public void delete() throws SQLException{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sqlCmd = "Delete from dividend";
		stmt.executeUpdate(sqlCmd);
		stmt.close();
		conn.close();
	}
	
	public void deleteById(Long id) throws SQLException{
		Connection conn = getConnection();
		String sqlCmd = "Delete from dividend where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqlCmd);
		stmt.setLong(1, id);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}

	public void update(Dividend dividend) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		String sqlCmd = "update dividend set cash_dividend=? where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqlCmd);
		stmt.setInt(1, 8);
		stmt.setLong(2, dividend.getId());
		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	
}
