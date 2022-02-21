package tw.com.fcb.sample.ijoshua29.web;

import java.sql.SQLException;
import java.util.List;

public class MovieService {
	
	private List<Movie> movies = null;	
	
	public List<Movie> getAll() throws SQLException {
	        MovieRepository movieRepository = new MovieRepository();
	        movies = movieRepository.findAll();
	        return movies;
	    }
		
	    public Movie getById(Long id) throws SQLException {
	        MovieRepository movieRepository = new MovieRepository();
	        Movie movie = movieRepository.getById(id);
	        return movie;
	    }
	
		public void runCrud() {
			
			MovieRepository movieRepository = new MovieRepository();
			
			Movie movie;
			Movie getMovie;
			
			try {
				// run findAll
				movies = movieRepository.findAll();
				
				System.out.println("共有" + movies.size() + " 筆資料");
				
				for(int i = 0 ; i < movies.size() ; i++) {
					System.out.println(movies.get(i).toString());
				}				
				
				// insert
				movie = new Movie();
				movie.setCode("A001");
				movie.setName("The mission");
				movie.setPrice(340);
				movie.setRoom(MovieRoomEnum.R1);
				movie = movieRepository.insert(movie);
				System.out.println("insert return id=" + movie.getId());
				
				movie = new Movie();
				movie.setCode("A002");
				movie.setName("Batman");
				movie.setPrice(320);
				movie.setRoom(MovieRoomEnum.R3);
				movie = movieRepository.insert(movie);
				System.out.println("insert return id=" + movie.getId());
				
				// getById(movie.getId());
				getMovie = movieRepository.getById(movie.getId());
				System.out.println("get by id result:" + getMovie.toString());
				
				// update 
				//movie.setCode("A002");
				//movie.setName("Mission imposible");
				//movie.setPrice(380);
				//movie.setRoom(MovieRoomEnum.R2);
				
				//movieRepository.update(movie);
				//getMovie = movieRepository.getById(movie.getId());
				//System.out.println("update get by id result:" + getMovie.toString());
				
				// delete by id
				//movieRepository.delete(movie.getId());
				//movies = movieRepository.findAll();				
				//System.out.println("刪除後共有 " + movies.size() + " 筆資料");
			
			}
			catch (SQLException e) 
			{
				System.out.println("查詢資料庫有誤");
				e.printStackTrace();
			} 
			catch (Exception e) 
			{
				System.out.println("其他錯誤");
				e.printStackTrace();
			}
			
			
		}

}
