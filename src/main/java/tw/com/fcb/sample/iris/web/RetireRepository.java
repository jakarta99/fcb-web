package tw.com.fcb.sample.iris.web;

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

public class RetireRepository {
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);		
	}
	
	public List<RetireAge> findAll() throws  SQLException{
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sqlCmd = "select * from retireID";
        ResultSet rs = stmt.executeQuery(sqlCmd);
        List<RetireAge> arr = new ArrayList<RetireAge>();
        RetireAge retireAge;
        while (rs.next()){
            retireAge = new RetireAge();
            retireAge.setId(rs.getLong("id"));
            retireAge.setType(rs.getString("type"));
            retireAge.setVoluntary_cnt(rs.getInt("voluntary_cnt"));
            retireAge.setAge_cnt(rs.getInt("age_cnt"));
            retireAge.setOrder_cnt(rs.getInt("order_cnt"));
            retireAge.setRetireEnum(RetireEnum.valueOf(rs.getString("retireEnum")));
            arr.add(retireAge);
        }
        return arr;
    }
	 public RetireAge getByType(String type) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();

	        String sqlCmd = "select * from retireID where type = '"+type+"'";
	        ResultSet rs = stmt.executeQuery(sqlCmd);

	        RetireAge retireAge = new RetireAge();
	        while(rs.next()) {
	        	retireAge.setId(rs.getLong("id"));
	            retireAge.setType(rs.getString("type"));
	            retireAge.setVoluntary_cnt(rs.getInt("voluntary_cnt"));
	            retireAge.setAge_cnt(rs.getInt("age_cnt"));
	            retireAge.setOrder_cnt(rs.getInt("order_cnt"));
	            retireAge.setRetireEnum(RetireEnum.valueOf(rs.getString("retireEnum")));
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	        return retireAge;
	    }
	 public RetireAge getById(Long id) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();

	        String sqlCmd = "select * from retireID where id = '"+id+"'";
	        ResultSet rs = stmt.executeQuery(sqlCmd);

	        RetireAge retireAge = new RetireAge();
	        while(rs.next()) {
	        	retireAge.setId(rs.getLong("id"));
	            retireAge.setType(rs.getString("type"));
	            retireAge.setVoluntary_cnt(rs.getInt("voluntary_cnt"));
	            retireAge.setAge_cnt(rs.getInt("age_cnt"));
	            retireAge.setOrder_cnt(rs.getInt("order_cnt"));
	            retireAge.setRetireEnum(RetireEnum.valueOf(rs.getString("retireEnum")));
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	        return retireAge;
	    }
	 public void inserDB(RetireAge obj) throws SQLException{

	        Connection conn = getConnection();	        
//	        String sqlCmd = "insert into retireID values('" + obj.getType() + "',"
//	        										  + obj.getVoluntary_cnt() +","
//	        										  + obj.getAge_cnt() +","
//	        										  + obj.getOrder_cnt() + ")";
	        String sqlCmd = "INSERT INTO retireID(Type,Voluntary_cnt,age_cnt,Order_cnt,retireEnum)" +
                            "VALUES(?,?,?,?,?) returning id,Type";
	        PreparedStatement  stmt = conn.prepareStatement(sqlCmd);
	        stmt.setString(1,obj.getType());
	        stmt.setInt(2,obj.getVoluntary_cnt() );
	        stmt.setInt(3,obj.getAge_cnt());
	        stmt.setInt(4,obj.getOrder_cnt());
	        stmt.setString(5,String.valueOf(obj.getRetireEnum()));
//	        ~~????~~executeUpdate --> 在 PreparedStatement 上不能使用獲取查詢字串的查詢方法。
//	        id顯示null~
	        ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("Type");
//				System.out.println(type);
				obj.setId(Long.valueOf(id));
			} else {
				// do something if can't 
				 System.out.println("insert not success");
			}
			
			System.out.println("insert "+obj);
//			stmt.clearParameters();
//			stmt.close();
//	        stmt.executeUpdate();
//	        stmt.executeQuery();
	        
	        stmt.close();
	        conn.close();
	    }
	 
	 public void updateDB(Long id, int voluntary_cnt) throws SQLException {

	        Connection conn = getConnection();

	        String sqlCmd = "update retireID set voluntary_cnt = '" + voluntary_cnt +"' where id = " + id;
	        PreparedStatement stmt = conn.prepareStatement(sqlCmd);

	        stmt.executeUpdate();
	        System.out.println("已將id = " + id + "的自願退休筆數更新為" +voluntary_cnt);
	        stmt.close();
	        conn.close();
	    }
	 public void deleteDB(String type) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();
	        String sqlCmd = "delete from retireID where type = '" + type + "'";
	        stmt.executeUpdate(sqlCmd);
	        stmt.close();
	        conn.close();
	    }

}
