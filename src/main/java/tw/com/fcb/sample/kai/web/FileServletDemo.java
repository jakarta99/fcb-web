package tw.com.fcb.sample.kai.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/stock")
public class FileServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FileSecuritiesService fileSecuritiesService = null;
	List<FileSecurities> listSecurities = null;
	
	// default constructor
	public FileServletDemo() {}
	
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
				
//				out.println("<!DOCTYPE html>");
//				out.println("<html>");
//				out.println("<head>");
//				out.println("<title>Hello! Servlet!</title>");
//				out.println("</head>");
//				out.println("<body>");
//				out.println("<table border=1 cellpadding=5>");
//				out.println("<tr>");
//				out.println("<th> StockOrder </th>");
//				out.println("<th> StockCode </th>");
//				out.println("<th> StockName </th>");
//				out.println("<th> StockTransaction </th>");
//				out.println("<th> EtfCode </th>");
//				out.println("<th> EtfName </th>");
//				out.println("<th> EtfTransaction </th>");
//				out.println("<th> Curr-Code </th>");
//				out.println("</tr>");
//				for(int i = 0 ; i < listSecurities.size() ; i++) {
//					out.println("<tr>");
//					out.println("<td>" + listSecurities.get(i).getSecuritiesOrder() + "</td>");
//					out.println("<td>" + listSecurities.get(i).getStockCode() + "</td>");
//					out.println("<td>" + listSecurities.get(i).getStockName() + "</td>");
//					out.println("<td>" + listSecurities.get(i).getStockTransaction() + "</td>");
//					out.println("<td>" + listSecurities.get(i).getEtfCode() + "</td>");
//					out.println("<td>" + listSecurities.get(i).getEtfName() + "</td>");
//					out.println("<td>" + listSecurities.get(i).getEtfTransaction() + "</td>");
//					out.println("<td>" + listSecurities.get(i).getCurrencyEnum() + "</td>");
//					out.println("</tr>");
//					
//				}
//				out.println("</table>");
//				out.println("</body>");
//				out.println("</html>");
//				out.println("共有 " + listSecurities.size() + " 筆資料!!!");
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
