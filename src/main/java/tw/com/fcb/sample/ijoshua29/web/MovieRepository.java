package tw.com.fcb.sample.ijoshua29.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
	
	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);
			
	}
	
	public List<Movie> findAll() throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from movie";
		ResultSet rs = stmt.executeQuery(sqlCmd);		
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie;
		
		while(rs.next()) {
			movie = new Movie();
			movie.setId(rs.getLong("id"));
			movie.setCode(rs.getString("code"));
			movie.setName(rs.getString("name"));
			movie.setPrice(rs.getInt("price"));
			movie.setRoom(MovieRoomEnum.valueOf(rs.getString("room")));		
			
			movies.add(movie);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		return movies;
	}
	
	
	public Movie getById(Long id) throws SQLException {
		
		Connection conn = getConnection();
		String sqlCmd = "select * from movie where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
		pstmt.setInt(1, id.intValue());
		ResultSet rs = pstmt.executeQuery();
		
		Movie movie = new Movie();
		if(rs.next()) {
			movie.setId(rs.getLong("id"));
			movie.setCode(rs.getString("code"));
			movie.setName(rs.getString("name"));
			movie.setPrice(rs.getInt("price"));
			movie.setRoom(MovieRoomEnum.valueOf(rs.getString("room")));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		return movie;
	}
	
	
	public Movie insert(Movie movie) throws SQLException {
		
		Connection conn = getConnection();
		String sqlCmd = "INSERT INTO movie(code, name, price, room) values (?, ? , ?, ?) returning id";
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
		pstmt.setString(1, movie.getCode());
		pstmt.setString(2, movie.getName());
		pstmt.setInt(3, movie.getPrice());
		pstmt.setString(4, movie.getRoom().toString());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int id = rs.getInt("id");
			movie.setId(Long.valueOf(id));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		return movie;
	}
	
/*	
	public void update(Movie movie) throws SQLException {
		
		Connection conn = getConnection();
		String sqlCmd = "Update movie set code = ?, name = ?, price = ?, room = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
		pstmt.setString(1, movie.getCode());
		pstmt.setString(2, movie.getName());
		pstmt.setInt(3, movie.getPrice());
		pstmt.setString(4, movie.getRoom().toString());
		pstmt.setInt(5, movie.getId().intValue());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	public void delete(Long id) throws SQLException {
		
		Connection conn = getConnection();
		String sqlCmd = "delete from movie where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
		pstmt.setInt(1, id.intValue());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	*/
}
