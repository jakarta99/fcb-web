package tw.com.fcb.sample.jilldolala25.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/mask-medical")
public class MaskMedicalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MaskMedicalService maskMedicalService = new MaskMedicalService();

    /**
     * Default constructor. 
     */
    public MaskMedicalServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();


			if (request.getParameter("id") == null) {
				List<MaskMedical> maskList = new ArrayList<>();

				try {
					maskList = maskMedicalService.getAll();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (MaskMedical maskMedical : maskList) {
					String output = "Id = " + maskMedical.getId()+
							" 醫事機構代碼   : " + maskMedical.getMedicalcode() +
							" 醫事機構名稱   : " + maskMedical.getMedicalname() +
							" 醫事機構地址   : " + maskMedical.getMedicalname() +
							" 醫事機構電話   : " + maskMedical.getMedicalphone() +
							" 成人口罩剩餘數 : " + maskMedical.getAldultcount() +
							" 兒童口罩剩餘數 : " + maskMedical.getKidscount();

					response.setContentType("text/html; charset=gbk");
					response.setCharacterEncoding("utf-8");
					out.println(output);
				}
			}else{
				String request1 = request.getParameter("id");
				MaskMedical maskMedical;
				try {
					maskMedical = maskMedicalService.findById(Long.valueOf(request1));
					String output = "Id = " + maskMedical.getId()+
							" 醫事機構代碼   : " + maskMedical.getMedicalcode() +
							" 醫事機構名稱   : " + maskMedical.getMedicalname() +
							" 醫事機構地址   : " + maskMedical.getMedicalname() +
							" 醫事機構電話   : " + maskMedical.getMedicalphone() +
							" 成人口罩剩餘數 : " + maskMedical.getAldultcount() +
							" 兒童口罩剩餘數 : " + maskMedical.getKidscount();

					response.setContentType("text/html; charset=gbk");
					response.setCharacterEncoding("utf-8");
					out.println(output);
//					out.println(maskmedical.toString());

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

	}

		

		
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
