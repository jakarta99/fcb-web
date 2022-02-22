package tw.com.fcb.sample.weifan.web;

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
 * Servlet implementation class LanguageServlet
 */
@WebServlet("/language")
public class LanguageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String seq = request.getParameter("seq");
		String func = request.getParameter("func");
		String level = request.getParameter("level");
		List<Language> result = new ArrayList<Language>();
		DataBaseService DB = new DataBaseService();
		Language Lan = new Language();
		
		response.setContentType("text/html; charset=utf-8");
		
		switch (func) {
		//find all
		case "1" :
			try {
				result = DB.findAll();
				for(int i = 0; i < result.size(); i++){
					out.println(result.get(i).toString()+"<br>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("資料庫錯誤");
				e.printStackTrace();
			}
			break;
		//insert
		case "2" :	
			try {
				Lan = DB.findByID(1);
				Lan.setSeq(Integer.valueOf(seq));
				DB.Insert(Lan);
				out.println("新增資料 : "+ Lan.toString()+"<br>");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				out.print("資料庫錯誤");
				e1.printStackTrace();
			}
			break;
		//update
		case "3" :
			try {
				Lan = DB.findByID(Integer.valueOf(seq));
				out.println("原始資料為 : "+ Lan.toString()+"<br>");
				Lan.setLevel(LanguageLevelEnum.valueOf(level));
				out.println("更新資料為 : "+ Lan.toString()+"<br>");
				DB.Update(Lan);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("資料庫錯誤");
				e.printStackTrace();
			}
			break;
		//delete
		case "4" :
			try {
				DB.Delete(Integer.valueOf(seq));
				out.println("成功刪除資料");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("資料庫錯誤");
				e.printStackTrace();
			}
			break;
		//find by id
		case "5" :
			try {
				Lan = DB.findByID(Integer.valueOf(seq));
				out.println("資料為 : "+ Lan.toString()+"<br>");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("資料庫錯誤");
				e.printStackTrace();
			}
			break;
		}
		
		


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
