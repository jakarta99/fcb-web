package tw.com.fcb.sample.kai.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/insert")
public class FileServletInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FileSecuritiesService fileSecuritiesService = null;
	List<FileSecurities> listSecurities = null;
	FileSecurities fileSecurities = null;
	
	// default constructor
	public FileServletInsert() {}
	
	// doGet()
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	// doPost()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);	
		try {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(request.getParameter("stockOrder"));
			fileSecurities.setStockCode(request.getParameter("stockCode"));
			fileSecurities.setStockName(request.getParameter("stockName"));
			fileSecurities.setStockTransaction(request.getParameter("stockTrans"));
			fileSecurities.setEtfCode(request.getParameter("etfCode"));
			fileSecurities.setEtfName(request.getParameter("etfName"));
			fileSecurities.setEtfTransaction(request.getParameter("etfTrans"));
			fileSecurities.setCurrencyEnum(FileCurrencyEnum.valueOf(request.getParameter("currCode")));
			
			fileSecuritiesService = new FileSecuritiesService();
			fileSecuritiesService.insert(fileSecurities);
			listSecurities = fileSecuritiesService.findAll();
			fileSecuritiesService.findOrderDataByCols("stockorder");
			request.setAttribute("listData", listSecurities);
			request.getRequestDispatcher("webkai/select.jsp").forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
