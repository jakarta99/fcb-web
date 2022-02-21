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
		doGet(request, response);
	}

}
