package tw.com.fcb.sample.sinjen.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeaturedStoreRepository {
	
	private Connection getConnection() {
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		
        try{
        	Class.forName("org.postgresql.Driver");
            try{
                Connection conn = DriverManager.getConnection(dbUrl, username, password);
                if (conn != null){
                    return conn;   
                }
            }catch(SQLException sqlEx){
            	sqlEx.printStackTrace();
            }
        }catch(ClassNotFoundException cnfEx){
        	cnfEx.printStackTrace();
        }
        return null;
	}
	
	// 讀取資料庫featured_store
	public List<StoreRow> getDBQueryResult() {
		List<StoreRow> arrayList = new ArrayList<StoreRow>();
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sqlCmd = "select * from featured_store";
			rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				StoreRow row = new StoreRow();
				row.setId(Long.valueOf(rs.getInt("id")));
				//row.setZone(rs.getString("zone"));
				row.setZone(String.valueOf(FeaturedZoneEnum.valueOf(rs.getString("zone"))));
				row.setStore(rs.getString("store"));
				row.setStoreTel(rs.getString("store_tel"));
				row.setStoreAdd(rs.getString("store_add"));
				row.setStoreProduct(rs.getString("store_product"));
				row.setTwd97X(rs.getString("twd97X"));
				row.setTwd97Y(rs.getString("twd97Y"));
				row.setWgs84aX(rs.getString("wgs84aX"));
				row.setWgs84aY(rs.getString("wgs84aY"));
				//System.out.println(row);
				arrayList.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeResultSet(rs);
		closeStatment(stmt);
		closeConnection(conn);
		System.out.println("讀取DB:featured_store完成，共" + arrayList.size() + "筆資料");
		return arrayList;
	}
	
	// 讀取資料庫featured_store筆數
	public int count() {
		int count = 0;
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sqlCmd = "select count(*) from featured_store";
			rs = stmt.executeQuery(sqlCmd);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeResultSet(rs);
		closeStatment(stmt);
		closeConnection(conn);
		//System.out.println("讀取DB:featured_store完成，count共" + count + "筆資料");
		return count;
	}
	
	// 讀取資料庫featured_store單筆資料
	public StoreRow getById(int id)  {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StoreRow row = new StoreRow();
		try {
			String sqlCmd = "select * from featured_store where id =?";
			stmt = conn.prepareStatement(sqlCmd);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row.setId(Long.valueOf(rs.getInt("id")));
				//row.setZone(rs.getString("zone"));
				row.setZone(String.valueOf(FeaturedZoneEnum.valueOf(rs.getString("zone"))));
				row.setStore(rs.getString("store"));
				row.setStoreTel(rs.getString("store_tel"));
				row.setStoreAdd(rs.getString("store_add"));
				row.setStoreProduct(rs.getString("store_product"));
				row.setTwd97X(rs.getString("twd97X"));
				row.setTwd97Y(rs.getString("twd97Y"));
				row.setWgs84aX(rs.getString("wgs84aX"));
				row.setWgs84aY(rs.getString("wgs84aY"));
				System.out.println("ID:" + id + "資料為" + row.toString());
			} else {
				System.out.println("查無ID:" + id + "資料");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeResultSet(rs);
		closeStatment(stmt);
		closeConnection(conn);
		return row;
	}
	
	// 寫入資料庫featured_store(方法不安全，禁用)
	public void insertDB_featured_store(List<StoreRow> listResult) {
		Connection conn = getConnection();
		Statement stmt = null;
		StoreRow row = null;
		int size = listResult.size();

		try {
			stmt = conn.createStatement();
			for (int i = 0; i < size; i++) {
				row = (StoreRow) listResult.get(i);
				String sqlCmd = "INSERT INTO featured_store values ("
						+ "'" + row.getZone() + "', " 
						+ "'" + row.getStore() + "', " 
						+ "'" + row.getStoreTel() + "', " 
						+ "'" + row.getStoreAdd() + "', " 
						+ "'" + row.getStoreProduct() + "', "
						+ "'" + row.getTwd97X() + "', " 
						+ "'" + row.getTwd97Y() + "', " 
						+ "'" + row.getWgs84aX() + "', "
						+ "'" + row.getWgs84aY() + "'"
						+ ")";
				stmt.executeUpdate(sqlCmd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatment(stmt);
		closeConnection(conn);
		System.out.println("寫入DB:featured_store完成，共" + size + "筆資料");
	}
	
	// 寫入資料庫featured_store
	public void insertDB(List<StoreRow> listResult) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		StoreRow row = null;
		int size = listResult.size();
		String sqlCmd = "INSERT INTO featured_store(zone,store,store_tel,store_add,store_product,twd97X,twd97Y,wgs84aX,wgs84aY)"
				+ "values(?,?,?,?,?,?,?,?,?)";

		try {
			stmt = conn.prepareStatement(sqlCmd);
			for (int i = 0; i < size; i++) {
				row = (StoreRow) listResult.get(i);
				stmt.setString(1, row.getZone());
				stmt.setString(2, row.getStore());
				stmt.setString(3, row.getStoreTel());
				stmt.setString(4, row.getStoreAdd());
				stmt.setString(5, row.getStoreProduct());
				stmt.setString(6, row.getTwd97X());
				stmt.setString(7, row.getTwd97Y());
				stmt.setString(8, row.getWgs84aX());
				stmt.setString(9, row.getWgs84aY());
				stmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatment(stmt);
		closeConnection(conn);
		System.out.println("寫入DB:featured_store完成，共" + size + "筆資料");
	}
	
	//更新資料庫featured_store單筆資料
	public void update(StoreRow row) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		try {
			String sqlCmd = "UPDATE featured_store SET zone=? ,store =? ,store_tel = ? ,store_add = ? ,store_product = ?"
					+ ",twd97X = ? ,twd97Y = ? ,wgs84aX = ? ,wgs84aY = ? WHERE id = ?";
			stmt = conn.prepareStatement(sqlCmd);
			stmt.setString(1, row.getZone());
			stmt.setString(2, row.getStore());
			stmt.setString(3, row.getStoreTel());
			stmt.setString(4, row.getStoreAdd());
			stmt.setString(5, row.getStoreProduct());
			stmt.setString(6, row.getTwd97X());
			stmt.setString(7, row.getTwd97Y());
			stmt.setString(8, row.getWgs84aX());
			stmt.setString(9, row.getWgs84aY());
			stmt.setInt(10, (row.getId().intValue()));
			int result = stmt.executeUpdate();
			
			if (result == 0) {
				System.out.println("無資料更新");
			} else {
				System.out.println("ID:" + row.getId() + "資料已更新：" + row.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatment(stmt);
		closeConnection(conn);
	}
	
	//刪除資料庫featured_store單筆資料
	public void delete(int id) {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		try {
			String sqlCmd = "DELETE FROM featured_store WHERE id = ?";
			stmt = conn.prepareStatement(sqlCmd);
			stmt.setInt(1, id);
			int result = stmt.executeUpdate();
			
			if (result == 0) {
				System.out.println("無資料刪除");
			} else {
				System.out.println("ID:" + id + "資料已刪除");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatment(stmt);
		closeConnection(conn);
	}

	// Close ResultSet
	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// Close Statement
	public static void closeStatment(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// Close Connection
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    }
}
