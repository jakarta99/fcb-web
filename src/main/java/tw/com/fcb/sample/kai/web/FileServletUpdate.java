package tw.com.fcb.sample.kai.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/update")
public class FileServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FileSecuritiesService fileSecuritiesService = null;
	List<FileSecurities> listSecurities = null;
	FileSecurities fileSecurities = null;
	
	// default constructor
	public FileServletUpdate() {}
	
	// doGet()
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=gbk");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
//		String code = request.getParameter("code");
		String id = request.getParameter("id");	
		if(request.getParameter("id") == null) {
			out.println("<h3>The data of stockOrder = " + id + " not found!!</h3>");
		} 
		else {
			fileSecuritiesService = new FileSecuritiesService();
			try {
				fileSecuritiesService.update("8888", id);
				listSecurities = fileSecuritiesService.findAll();
//				fileSecuritiesService.findOrderDataByCols("stockorder");
				request.setAttribute("listData", listSecurities);
				request.getRequestDispatcher("webkai/select.jsp").forward(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// doPost()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
