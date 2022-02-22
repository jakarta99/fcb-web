package tw.com.fcb.sample.h25949.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

public class FinancialIndicatorsRepository {

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";

		return DriverManager.getConnection(dbUrl, username, password);
	}

	public int insert(List<FinancialIndicators> result) throws SQLException {

		Connection conn = getConnection();
		int count = 0;

		for (FinancialIndicators FinancialIndicator : result) {
			String SQL = "insert into fruit(the_year,exchange_rate,the_foreign,stock_index,stock_amount) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.setInt(1, FinancialIndicator.getYear());
			ps.setBigDecimal(2, FinancialIndicator.getExchangeRate());
			ps.setBigDecimal(3, FinancialIndicator.getForeign());
			ps.setBigDecimal(4, FinancialIndicator.getStockIndex());
			ps.setBigDecimal(5, FinancialIndicator.getStockAmount());

			count = count + ps.executeUpdate();
			;
		}
		conn.close();
		return count;
	}

	public List<FinancialIndicators> findAll() throws SQLException {
		Connection conn = getConnection();
		String sqlCmd = "select * from fruit";
		PreparedStatement ps = conn.prepareStatement(sqlCmd);
		ResultSet rs = ps.executeQuery();

		List<FinancialIndicators> FinancialIndicators = new ArrayList<FinancialIndicators>();
		FinancialIndicators FinancialIndicator;
		while (rs.next()) {
			FinancialIndicator = new FinancialIndicators();
			FinancialIndicator.setYear(rs.getInt("the_year"));
			FinancialIndicator.setExchangeRate(rs.getBigDecimal("exchange_rate"));
			FinancialIndicator.setForeign(rs.getBigDecimal("the_foreign"));
			FinancialIndicator.setStockIndex(rs.getBigDecimal("stock_index"));
			FinancialIndicator.setStockAmount(rs.getBigDecimal("stock_amount"));

			FinancialIndicators.add(FinancialIndicator);
		}
		conn.close();
		return FinancialIndicators;
	}
	
	@Builder
	public FinancialIndicators getById(int optionById) throws SQLException {
		Connection conn = getConnection();
		String sqlCmd = "select * from fruit where the_year = ?";
		PreparedStatement ps = conn.prepareStatement(sqlCmd);

		ps.setInt(1, optionById);
		ResultSet rs = ps.executeQuery();

		FinancialIndicators FinancialIndicator = new FinancialIndicators();
		//FinancialIndicators FinancialIndicator = FinancialIndicators.builder().build();
		if (rs.next()) {
			FinancialIndicator.setYear(rs.getInt("the_year"));
			FinancialIndicator.setExchangeRate(rs.getBigDecimal("exchange_rate"));
			FinancialIndicator.setForeign(rs.getBigDecimal("the_foreign"));
			FinancialIndicator.setStockIndex(rs.getBigDecimal("stock_index"));
			FinancialIndicator.setStockAmount(rs.getBigDecimal("stock_amount"));
		}
		conn.close();
		return FinancialIndicator;
	}

	public int update(FinancialIndicators financialIndicator) throws SQLException {
		String SQL = "UPDATE fruit SET exchange_rate=? , the_foreign=?, stock_index=?,stock_amount =? WHERE the_year = ?";
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(SQL);

		ps.setBigDecimal(1, financialIndicator.getExchangeRate());
		ps.setBigDecimal(2, financialIndicator.getForeign());
		ps.setBigDecimal(3, financialIndicator.getStockIndex());
		ps.setBigDecimal(4, financialIndicator.getStockAmount());
		ps.setInt(5, financialIndicator.getYear());

		conn.close();
		return ps.executeUpdate();
	}

	public int delete(int optionById) throws SQLException {
		String SQL = "DELETE FROM fruit WHERE the_year = ?";
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(SQL);

		ps.setInt(1, optionById);

		conn.close();
		return ps.executeUpdate();
	}

}
