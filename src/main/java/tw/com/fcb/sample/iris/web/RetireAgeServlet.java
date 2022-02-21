package tw.com.fcb.sample.iris.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.fcb.sample.copykobe.web.Consumption;
import tw.com.fcb.sample.jilldolala25.web.MaskMedical;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello-iris")
public class RetireAgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RetireAgeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
//        response.setCharacterEncoding("utf-8");
        
		out.println("Hello id!!!~~="+request.getParameter("id") + "<BR>");
		if (request.getParameter("id") == null) {
			try {  
				
//	            List<String> resultFile = new RetireAgeService().loadFromFile();
//	            out.println("***檔案內容輸出****");
//	            out.println("目前已經有 "+resultFile.size()+"筆資料<br>");
//	            out.println(resultFile);
	            
	            List<RetireAge> resultDB= new RetireAgeService().loadDB();
	            out.println("***資料庫輸出****");
	            out.println("目前已經有 "+resultDB.size()+"筆資料<br>");
	            for (RetireAge retireAge : resultDB) {
					String output = "Id = " + retireAge.getId()+
							" 項目別     	: " + retireAge.getType() +
				 			" 自願退休筆數   : " + retireAge.getVoluntary_cnt() +
				 			" 屆齡退休筆數   : " + retireAge.getAge_cnt() +
				 			" 命令退休筆數   : " + retireAge.getOrder_cnt() +
				 			" 測試enum : " + retireAge.getRetireEnum() + "<BR>";  
					out.println(output);
	            }
			}catch (Exception e) {
	                System.out.println("Unknown");
	                e.printStackTrace();
	        } 
		}else {
			try {
				RetireAge oneData = new RetireAgeService().findById(Long.valueOf(request.getParameter("id")));
				out.println(oneData);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        
            
////     	   2022/02/11 homework run
//            RetireAgeService reSe = new RetireAgeService();
//            reSe.runCrud();
            out.println("***end****");
            
        
        
//      response.getWriter().append("Served at: ").append(request.getContextPath());

	}

		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
