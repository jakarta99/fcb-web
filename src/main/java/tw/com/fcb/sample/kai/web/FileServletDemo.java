package tw.com.fcb.sample.kai.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/index")
public class FileServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// default constructor
	public FileServletDemo() {}
	
	// doGet()
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=gbk");
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("webkai/index.jsp").forward(request, response);
	}
	
	// doPost()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
