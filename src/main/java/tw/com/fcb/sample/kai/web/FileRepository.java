package tw.com.fcb.sample.kai.web;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class FileRepository {
	List<FileSecurities> list = null;
	FileSecurities fileSecurities = null;
	HikariDataSource ds = null;
	
	public FileRepository() throws Exception{
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.addDataSourceProperty("minimumIdle", "10");
        config.addDataSourceProperty("maximumPoolSize", "30");
        this.ds = new HikariDataSource(config);
		
//		LocalDateTime starTime = LocalDateTime.now();
//		Properties props = new Properties();
//		props.load(new FileInputStream("jdbc.properties"));
//		
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl(props.getProperty("dbUrl"));
//		config.setUsername(props.getProperty("username"));
//		config.setPassword(props.getProperty("password"));
//		config.addDataSourceProperty("minimumIdle", "10");
//		config.addDataSourceProperty("maximumPoolSize", "30");
//		this.ds = new HikariDataSource(config);
//		
//		LocalDateTime endTime = LocalDateTime.now();
//		Long diff = ChronoUnit.MILLIS.between(starTime, endTime);
//		System.out.println("Total Sec: " + diff + " msec");
	}
	
	// getConnection()
	public Connection getConnection() throws Exception {
		return ds.getConnection();
	}
	
	// findAll()
	public List<FileSecurities> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
			
		String selectSql = "SELECT * FROM stock ORDER BY stockorder";
		pStatement = connection.prepareStatement(selectSql);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<FileSecurities>();
		while(resultSet.next()) {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(resultSet.getString("stockorder"));
			fileSecurities.setStockCode(resultSet.getString("stockcode"));
			fileSecurities.setStockName(resultSet.getString("stockname"));
			fileSecurities.setStockTransaction(resultSet.getString("stocktransaction"));
			fileSecurities.setEtfCode(resultSet.getString("etfcode"));
			fileSecurities.setEtfName(resultSet.getString("etfname"));
			fileSecurities.setEtfTransaction(resultSet.getString("etftransaction"));
			fileSecurities.setCurrencyEnum(FileCurrencyEnum.valueOf(resultSet.getString("curr_code")));

			list.add(fileSecurities);
		}
		
		return list ;
	}
	
	// findAllOrderData()
	public List<FileSecurities> findAllOrderData() throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
			
		String selectSql = "SELECT * FROM stock ORDER BY stockcode";
		pStatement = connection.prepareStatement(selectSql);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<FileSecurities>();
		while(resultSet.next()) {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(resultSet.getString("stockorder"));
			fileSecurities.setStockCode(resultSet.getString("stockcode"));
			fileSecurities.setStockName(resultSet.getString("stockname"));
			fileSecurities.setStockTransaction(resultSet.getString("stocktransaction"));
			fileSecurities.setEtfCode(resultSet.getString("etfcode"));
			fileSecurities.setEtfName(resultSet.getString("etfname"));
			fileSecurities.setEtfTransaction(resultSet.getString("etftransaction"));
			fileSecurities.setCurrencyEnum(FileCurrencyEnum.valueOf(resultSet.getString("curr_code")));
			
			list.add(fileSecurities);
		}
		
		return list ;
	}
	
	// findOrderDataByCols()
	public List<FileSecurities> findOrderDataByCols(String column) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
			
		String selectSql = "SELECT * FROM stock ORDER BY " + column + "";
		pStatement = connection.prepareStatement(selectSql);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<FileSecurities>();
		while(resultSet.next()) {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(resultSet.getString("stockorder"));
			fileSecurities.setStockCode(resultSet.getString("stockcode"));
			fileSecurities.setStockName(resultSet.getString("stockname"));
			fileSecurities.setStockTransaction(resultSet.getString("stocktransaction"));
			fileSecurities.setEtfCode(resultSet.getString("etfcode"));
			fileSecurities.setEtfName(resultSet.getString("etfname"));
			fileSecurities.setEtfTransaction(resultSet.getString("etftransaction"));
			fileSecurities.setCurrencyEnum(FileCurrencyEnum.valueOf(resultSet.getString("curr_code")));
			
			list.add(fileSecurities);
		}
		
		return list ;
	}
	
	// findById()
	public List<FileSecurities> findById(String stockOrder) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
			
		String selectSql = "SELECT * FROM stock WHERE stockOrder = ?";
		pStatement = connection.prepareStatement(selectSql);
		pStatement.setString(1, stockOrder);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<FileSecurities>();
		if(resultSet.next()) {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(resultSet.getString("stockorder"));
			fileSecurities.setStockCode(resultSet.getString("stockcode"));
			fileSecurities.setStockName(resultSet.getString("stockname"));
			fileSecurities.setStockTransaction(resultSet.getString("stocktransaction"));
			fileSecurities.setEtfCode(resultSet.getString("etfcode"));
			fileSecurities.setEtfName(resultSet.getString("etfname"));
			fileSecurities.setEtfTransaction(resultSet.getString("etftransaction"));
			fileSecurities.setCurrencyEnum(FileCurrencyEnum.valueOf(resultSet.getString("curr_code")));
			
			list.add(fileSecurities);
		}
		else {
			System.out.println("The data of stockOrder = " + stockOrder + " not found!! ");
		}
		
		return list ;
	}
	
	// insert
	public void insert(FileSecurities fileSecurities) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

		connection = getConnection();
		
		//方法一
		//CREATE type curr_enum as enum('USD', 'EUR', 'JPY', 'ZAR', 'TWD');
		//因為DB有設定ENUM型態，所以在傳值時需指定::curr_enum
		//String insertSql = "INSERT INTO stock VALUES(?, ?, ?, ?, ?, ?, ?, ?::curr_enum)";
		//pStatement.setString(8, fileSecurities.getCurrencyEnum().name());
		
		String insertSql = "INSERT INTO stock VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		pStatement = connection.prepareStatement(insertSql);
		pStatement.setString(1, fileSecurities.getSecuritiesOrder());
		pStatement.setString(2, fileSecurities.getStockCode());
		pStatement.setString(3, fileSecurities.getStockName());
		pStatement.setString(4, fileSecurities.getStockTransaction());
		pStatement.setString(5, fileSecurities.getEtfCode());
		pStatement.setString(6, fileSecurities.getEtfName());
		pStatement.setString(7, fileSecurities.getEtfTransaction());
		pStatement.setObject(8, fileSecurities.getCurrencyEnum(), java.sql.Types.OTHER);
		pStatement.executeUpdate();
		pStatement.clearParameters();
	}
	
	// update
	public void update(String stockCode, String stockOrder) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

		connection = getConnection();
			
		String updateSql = "UPDATE stock SET stockcode = ? WHERE stockorder = ?";
		pStatement = connection.prepareStatement(updateSql);
		pStatement.setString(1, stockCode);
		pStatement.setString(2, stockOrder);
		
		int affectedRow = pStatement.executeUpdate();
		System.out.println("共 " + affectedRow + " 筆資料更新");
	}
	
	// delete
	public void delete(String stockOrder) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

		connection = getConnection();
			
		String updateSql = "DELETE FROM stock WHERE stockorder = ?";
		pStatement = connection.prepareStatement(updateSql);
		pStatement.setString(1, stockOrder);
		
		int affectedRow = pStatement.executeUpdate();
		System.out.println("共 " + affectedRow + " 筆資料刪除");
	}
}
