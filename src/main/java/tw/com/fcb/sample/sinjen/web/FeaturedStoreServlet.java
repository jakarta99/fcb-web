package tw.com.fcb.sample.sinjen.web;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/store")
public class FeaturedStoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String TAB = "&emsp;";
	private static final String HEADING_1 = "商圈名稱:";
	private static final String HEADING_2 = "特色商家:";
	private static final String HEADING_3 = "店家電話:";
	private static final String HEADING_4 = "店家地址:";
	private static final String HEADING_5 = "店家商品:";
	//private static final String HEADING_6 = "twd97緯度";
	//private static final String HEADING_7 = "twd97經度";
	//private static final String HEADING_8 = "wgs84a緯度";
	//private static final String HEADING_9 = "wgs84a經度";

	public FeaturedStoreServlet() {
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String inputId = request.getParameter("id");
		//out.println(request.getMethod() + "<br>");
		//out.println("Store id="+inputId + "<br>");

		FeaturedStoreRepository fsr = new FeaturedStoreRepository();
		List<StoreRow> rowlist = new ArrayList<StoreRow>();
		StoreRow row = new StoreRow();
		if (inputId != null && inputId != "") {
			row = fsr.getById(Integer.valueOf(inputId));
			response.setContentType("text/html; charset=utf-8");
			out.println(row.toString());
		}else{
			rowlist = fsr.getDBQueryResult();
			for (StoreRow r : rowlist) {
				String output = HEADING_1 + r.getZone() + TAB + HEADING_2 + r.getStore() + TAB + HEADING_3 + r.getStoreTel() + TAB 
						+ HEADING_4 + r.getStoreAdd() + TAB + HEADING_5 + r.getStoreProduct() ;
				response.setContentType("text/html; charset=utf-8");
				out.println(output + "<br>");
			}
			//out.println(rowlist.toString());
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String inputZone = request.getParameter("zone");
		String inputStore = request.getParameter("store");
		String inputTel = request.getParameter("tel");
		String inputAdd = request.getParameter("add");
		String inputProduct = request.getParameter("product");
		String twd97X = request.getParameter("twd97X");
		String twd97Y = request.getParameter("twd97Y");
		String wgs84aX = request.getParameter("wgs84aX");
		String wgs84aY = request.getParameter("wgs84aY");
		//out.println(request.getMethod() + "<br>");
		//out.println("Store id="+inputId + "<br>");
		
		FeaturedStoreRepository fsr = new FeaturedStoreRepository();
		List<StoreRow> addOne = new ArrayList<StoreRow>();
		StoreRow row = new StoreRow();
		if (inputZone != null && inputZone != "") {
			row.setZone(inputZone);
			row.setStore(inputStore);
			row.setStoreTel(inputTel);
			row.setStoreAdd(inputAdd);
			row.setStoreProduct(inputProduct);
			row.setTwd97X(twd97X);
			row.setTwd97Y(twd97Y);
			row.setWgs84aX(wgs84aX);
			row.setWgs84aY(wgs84aY);
			addOne.add(row);
			fsr.insertDB(addOne);
			out.println("已新增資料");
		}else {
			out.println("無新增資料");
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String inputId = request.getParameter("id");
		String inputTel = request.getParameter("tel");	///範例:更新電話號碼欄位
		//out.println(request.getMethod() + "<br>");
		//out.println("Store id="+inputId + "<br>");
		
		FeaturedStoreRepository fsr = new FeaturedStoreRepository();
		StoreRow row = new StoreRow();
		if (inputId != null && inputId != "") {
			row = fsr.getById(Integer.valueOf(inputId));
			if (row.getId() != null) {
				row.setStoreTel(inputTel);	///範例:更新電話號碼欄位
				fsr.update(row);
				out.println("ID:" + inputId + "資料已更新");
			} else {
				out.println("無資料更新");
			}

		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String inputId = request.getParameter("id");
		//out.println(request.getMethod() + "<br>");
		//out.println("Store id="+inputId + "<br>");
		
		FeaturedStoreRepository fsr = new FeaturedStoreRepository();
		StoreRow row = new StoreRow();
		if (inputId != null && inputId != "") {
			row = fsr.getById(Integer.valueOf(inputId));
			if (row.getId() != null) {
				fsr.delete(Integer.valueOf(inputId));
				out.println("ID:" + inputId + "資料已刪除");
			} else {
				out.println("無資料刪除");
			}
		}
	}
	
}
