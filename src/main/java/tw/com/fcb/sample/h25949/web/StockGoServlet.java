package tw.com.fcb.sample.h25949.web;

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
@WebServlet("/StockGo")
public class StockGoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FinancialIndicatorsService financialIndicatorsService = new FinancialIndicatorsService();

	/**
	 * Default constructor.
	 */
	public StockGoServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		List<FinancialIndicators> allFinancialIndicators = new ArrayList<>();

		try {
			allFinancialIndicators = financialIndicatorsService.findAll();
			for (FinancialIndicators allFinancialIndicator : allFinancialIndicators) {
				out.println(allFinancialIndicator + "<br>");
			}
		} catch (SQLException e) {
			// TODO 自動產生的 catch 區塊
			out.print("Error");
			e.printStackTrace();
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}