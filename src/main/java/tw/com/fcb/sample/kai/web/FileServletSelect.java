package tw.com.fcb.sample.kai.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/select")
public class FileServletSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FileSecuritiesService fileSecuritiesService = null;
	List<FileSecurities> listSecurities = null;
	
	// default constructor
	public FileServletSelect() {}
	
	// doGet()
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=gbk");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("id") == null) {
			fileSecuritiesService = new FileSecuritiesService();
			try {
				listSecurities = fileSecuritiesService.findAll();
				request.setAttribute("listData", listSecurities);
				request.getRequestDispatcher("webkai/select.jsp").forward(request, response);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			fileSecuritiesService = new FileSecuritiesService();
			try {
				String id = request.getParameter("id");	
				listSecurities = fileSecuritiesService.findById(id);
				
				if(listSecurities.size() == 0) {
					out.println("<h3>The data of stockOrder = " + id + " not found!!</h3>");
				}
				else {
					request.setAttribute("listData", listSecurities);
					request.getRequestDispatcher("webkai/select.jsp").forward(request, response);
//					for(int i = 0 ; i < listSecurities.size() ; i++) {
//						request.setAttribute("stockOrder", listSecurities.get(i).getSecuritiesOrder());
//						request.setAttribute("stockCode", listSecurities.get(i).getStockCode());
//						request.setAttribute("stockName", listSecurities.get(i).getStockName());
//						request.setAttribute("stockTrans", listSecurities.get(i).getStockTransaction());
//						request.setAttribute("etfCode", listSecurities.get(i).getEtfCode());
//						request.setAttribute("etfName", listSecurities.get(i).getEtfName());
//						request.setAttribute("etfTrans", listSecurities.get(i).getEtfTransaction());
//						request.setAttribute("currCode", listSecurities.get(i).getCurrencyEnum());
//						request.getRequestDispatcher("webkai/select.jsp").forward(request, response);
//					}
				}
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
