package tw.com.fcb.sample.yuwei.web;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DividendServlet
 */
@WebServlet("/Dividend")
public class DividendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DividendService dividendService = new DividendService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DividendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		out.println("Hello id="+request.getParameter("id")+"<br>");
		String reqIdParameter = request.getParameter("id");
		
		if(reqIdParameter == null) {
			List<Dividend> dividendList = new ArrayList<Dividend>();
			try {
				dividendList = dividendService.findAll();
				for(Dividend dividend : dividendList) {
					String output = "id : "+dividend.getId()
					+" allocationOfAnnual : "+dividend.getAllocationOfAnnual()
							+" cashDividend : "+dividend.getCashDividend()
							+" stockDividend : "+dividend.getStockDividend()
							+" total : "+dividend.getTotal()
							+" totalCashDividendUnit : "+dividend.getTotalCashDividendUnit()
							+" shareholdingRatio : "+dividend.getShareholdingRatio()
							+" issuingCompany : "+dividend.getIssuingCompany()
							+" Industry : "+ dividend.getIndustry()+"<br>";
					
					out.println(output);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
				try {
					Dividend dividend = dividendService.findById(Long.valueOf(reqIdParameter));
					String output = " id : "+dividend.getId()
					+" allocationOfAnnual : "+dividend.getAllocationOfAnnual()
							+" cashDividend : "+dividend.getCashDividend()
							+" stockDividend : "+dividend.getStockDividend()
							+" total : "+dividend.getTotal()
							+" totalCashDividendUnit : "+dividend.getTotalCashDividendUnit()
							+" shareholdingRatio : "+dividend.getShareholdingRatio()
							+" issuingCompany : "+dividend.getIssuingCompany()
							+" Industry : "+ dividend.getIndustry();
					
					out.println(output);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
