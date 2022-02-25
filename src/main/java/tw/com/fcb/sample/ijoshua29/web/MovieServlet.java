package tw.com.fcb.sample.ijoshua29.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.fcb.Movie;
import tw.com.fcb.MovieRoomEnum;
import tw.com.fcb.MovieService;



/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/movie")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MovieServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		MovieService movieService = new MovieService();
		
		if (request.getParameter("id") == null) {
			List<Movie> movies = new ArrayList<>();

			try {
				movies = movieService.getAll();
				
				for (int i = 0 ; i < movies.size() ; i++) {
					String outStr = movies.get(i).toString() + "<br>";
				
					response.setContentType("text/html; charset=utf-8");
					out.println(outStr);
				}
			} catch (SQLException e) {
				System.out.println("查詢資料庫有誤");
				e.printStackTrace();
			}
			
		}else{
			
			Movie movie;
			
			try {
				movie = movieService.getById(Long.valueOf(request.getParameter("id")));
				String outStr = movie.toString();

				response.setContentType("text/html; charset=utf-8");
				out.println(outStr);

			} catch (SQLException e) {
				System.out.println("查詢資料庫有誤");
				e.printStackTrace();
			}

		}
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		MovieService movieService = new MovieService();
		Movie postMovie;
		Movie getMovie;
		String name;
		String code;
		int price;
		String room;
		long id;
		
		if (request.getParameter("action").equals("insert")) 
		{
			try {
				code = request.getParameter("code");
				name = request.getParameter("name");
				room = request.getParameter("room");
				price = Integer.parseInt(request.getParameter("price"));
				
				postMovie = new Movie();
				postMovie.setCode(code);
				postMovie.setName(name);
				postMovie.setPrice(price);
				postMovie.setRoom(MovieRoomEnum.valueOf(room));
				
				getMovie = movieService.insert(postMovie);
				response.setContentType("text/html; charset=utf-8");
				out.println(getMovie.toString());
				
			} catch (SQLException e) {
				System.out.println("查詢資料庫有誤");
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("輸入金額有誤");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("資料有誤");
				e.printStackTrace();
			}
		}
		else if (request.getParameter("action").equals("update")) 
		{
			try {
				code = request.getParameter("code");
				name = request.getParameter("name");
				room = request.getParameter("room");
				price = Integer.parseInt(request.getParameter("price"));
				id = Long.parseLong(request.getParameter("id"));
				
				postMovie = new Movie();
				postMovie.setCode(code);
				postMovie.setName(name);
				postMovie.setPrice(price);
				postMovie.setRoom(MovieRoomEnum.valueOf(room));
				postMovie.setId(id);
				
				getMovie = movieService.update(postMovie);
				response.setContentType("text/html; charset=utf-8");
				out.println(getMovie.toString());
				
			} catch (SQLException e) {
				System.out.println("查詢資料庫有誤");
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("輸入金額有誤");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("資料有誤");
				e.printStackTrace();
			}
		}
		
	}

}
