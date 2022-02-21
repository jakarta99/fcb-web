package tw.com.fcb.sample.weifan.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DataBaseService {
	private String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
	private String username = "postgres";
	private String password = "postgres";

	public Connection ConnectDB() throws SQLException {
		try {
			Class.forName("java.sql.DriverManager");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection(dbUrl, username, password);
		return conn;
	}
	
	public List<Language> findAll() throws Exception {
		Connection connection = ConnectDB();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		List<Language> list = null;
		
		String SqlCmd = "SELECT * FROM language";
		pStatement = connection.prepareStatement(SqlCmd);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<Language>();
		while(resultSet.next()) {
			Language Lan = new Language();
			Lan.setSeq(Integer.valueOf(resultSet.getString("seq")));
			Lan.setCreateDate(resultSet.getString("createdate"));
			Lan.setYear(resultSet.getString("year"));
			Lan.setChineseName(resultSet.getString("chinesename"));
			Lan.setLevel(LanguageLevelEnum.valueOf(resultSet.getString("level")));
			Lan.setSignNum(resultSet.getString("signnum"));
			Lan.setJoinNum(resultSet.getString("joinnum"));
			Lan.setPassNum(resultSet.getString("passnum"));
			list.add(Lan);
		}
		
		return list ;
	}
	public Language findByID(int ID) throws Exception {
		Language Lan = new Language();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		Connection connection = ConnectDB();
		
		String SqlCmd = "SELECT * FROM language where seq = ? ";
		pStatement = connection.prepareStatement(SqlCmd);
		pStatement.setInt(1, ID);
		resultSet = pStatement.executeQuery();
		
		if(resultSet.next()) {
		Lan.setSeq(Integer.valueOf(resultSet.getString("seq")));
		Lan.setCreateDate(resultSet.getString("createdate"));
		Lan.setYear(resultSet.getString("year"));
		Lan.setChineseName(resultSet.getString("chinesename"));
		Lan.setLevel(LanguageLevelEnum.valueOf(resultSet.getString("level")));
		Lan.setSignNum(resultSet.getString("signnum"));
		Lan.setJoinNum(resultSet.getString("joinnum"));
		Lan.setPassNum(resultSet.getString("passnum"));
		}
		return Lan;
	}
	public void Insert(Language Lan) throws Exception{
		PreparedStatement pStatement = null;
		Connection connection = ConnectDB();

		String SqlCmd = "INSERT INTO language VALUES(?,?,?,?,?,?,?,?)";
		pStatement = connection.prepareStatement(SqlCmd);
		pStatement.setInt(1,Lan.getSeq());
		pStatement.setString(2,Lan.getCreateDate());
		pStatement.setString(3,Lan.getYear());
		pStatement.setString(4,Lan.getChineseName());
		pStatement.setString(5,Lan.getLevel().toString());
		pStatement.setString(6,Lan.getSignNum());
		pStatement.setString(7,Lan.getJoinNum());
		pStatement.setString(8,Lan.getPassNum());
		pStatement.executeUpdate();
		pStatement.clearParameters();
	}
	public void Update(Language Lan) throws Exception{
		PreparedStatement pStatement = null;
		Connection connection = ConnectDB();

		String SqlCmd = "UPDATE language SET createdate = ?, year = ?, chinesename =?, level = ?, signnum = ?, joinnum = ?, passnum = ? WHERE seq = ?";
		pStatement = connection.prepareStatement(SqlCmd);
		pStatement.setInt(8,Lan.getSeq());
		pStatement.setString(1,Lan.getCreateDate());
		pStatement.setString(2,Lan.getYear());
		pStatement.setString(3,Lan.getChineseName());
		pStatement.setString(4,Lan.getLevel().toString());
		pStatement.setString(5,Lan.getSignNum());
		pStatement.setString(6,Lan.getJoinNum());
		pStatement.setString(7,Lan.getPassNum());
		pStatement.executeUpdate();
		pStatement.clearParameters();
	}
	public void Delete(int ID) throws Exception{
		PreparedStatement pStatement = null;
		Connection connection = ConnectDB();

		String SqlCmd = "DELETE FROM language WHERE seq = ?";
		pStatement = connection.prepareStatement(SqlCmd);
		pStatement.setInt(1,ID);

		pStatement.executeUpdate();
		pStatement.clearParameters();
	}
	public void CloseDB(Statement stmt) throws SQLException{
		stmt.close();
	}
}
