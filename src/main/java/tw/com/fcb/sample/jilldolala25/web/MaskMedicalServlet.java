package tw.com.fcb.sample.jilldolala25.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.poi.util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/mask-medical")
public class MaskMedicalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MaskMedicalService maskMedicalService = new MaskMedicalService();
	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	String updateDate = now.format(formatter);
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
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			for (MaskMedical maskMedical : maskList) {
				String output = "Id = " + maskMedical.getId() +
						" 醫事機構代碼   : " + maskMedical.getMedicalcode() +
						" 醫事機構名稱   : " + maskMedical.getMedicalname() +
						" 醫事機構地址   : " + maskMedical.getMedicalname() +
						" 醫事機構電話   : " + maskMedical.getMedicalphone() +
						" 成人口罩剩餘數 : " + maskMedical.getAldultcount() +
						" 兒童口罩剩餘數 : " + maskMedical.getKidscount();

				response.setContentType("text/html; charset=utf8");

				out.println(output + "</br>");
			}
		} else {
			String request1 = request.getParameter("id");
			MaskMedical maskMedical;
			try {
				maskMedical = maskMedicalService.findById(Long.valueOf(request1));
				String output = "Id = " + maskMedical.getId() +
						" 醫事機構代碼   : " + maskMedical.getMedicalcode() +
						" 醫事機構名稱   : " + maskMedical.getMedicalname() +
						" 醫事機構地址   : " + maskMedical.getMedicalname() +
						" 醫事機構電話   : " + maskMedical.getMedicalphone() +
						" 成人口罩剩餘數 : " + maskMedical.getAldultcount() +
						" 兒童口罩剩餘數 : " + maskMedical.getKidscount();

				response.setContentType("text/html; charset=utf-8");
//					response.setCharacterEncoding("utf-8");
				out.println(output + "</br>");
//					out.println(maskmedical.toString());

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}


	// response.getWriter().append("Served at: ").append(request.getContextPath());

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SneakyThrows
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action.equals("insert")) {
			String medicalCode = request.getParameter("medical_code");
			String medicalName = request.getParameter("medical_name");
			String medicalAddress = request.getParameter("medical_address");
			String medicalPhone = request.getParameter("medical_phone");
			String medicalAldultCount = request.getParameter("aldult_count");
			String medicalKidsCount = request.getParameter("kids_count");
			MaskMedicalEnum maskMedicalEnum = MaskMedicalEnum.valueOf(request.getParameter("medical_type"));
			MaskMedical maskMedical = new MaskMedical();
			maskMedical.setMedicalcode(medicalCode);
			maskMedical.setMedicalname(medicalName);
			maskMedical.setMedicaladdress(medicalAddress);
			maskMedical.setMedicalphone(medicalPhone);
			maskMedical.setAldultcount(Integer.parseInt(medicalAldultCount));
			maskMedical.setKidscount(Integer.parseInt(medicalKidsCount));
			maskMedical.setMaskMedicalEnum(maskMedicalEnum);
			maskMedical.setDate(updateDate);

			maskMedicalService.insertData(maskMedical);
			out.println("資料新增成功 : " + maskMedical);
		}else if (action.equals("update")) {
			String id = request.getParameter("id");
			String phone = request.getParameter("medical_phone");
			if (id != null) {
				maskMedicalService.updateData(Long.valueOf(id), phone);
				MaskMedical maskMedical = maskMedicalService.findById(Long.valueOf(id));
				out.println("資料更新成功 id = " + id + "日期被更新為 : " + maskMedical.getDate() + "  電話被更新為 :" + maskMedical.getMedicalphone());
			}else{
				out.println("輸入錯誤");

			}
		}else if (action.equals("delete")){
			String requestId = request.getParameter("id");

			maskMedicalService.deleteData(Long.valueOf(requestId));
			out.println("資料刪除成功 id = " + requestId);
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String phone = request.getParameter("medical_phone");
		if (id != null) {
			try {
				maskMedicalService.updateData(Long.valueOf(id), phone);

			MaskMedical maskMedical = null;
				maskMedical = maskMedicalService.findById(Long.valueOf(id));

			out.println("資料更新成功 id = " + id + "日期被更新為 : " + maskMedical.getDate() + "  電話被更新為 :" + maskMedical.getMedicalphone());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			out.println("輸入錯誤");

		}

	}

	@SneakyThrows
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		String requestId = request.getParameter("id");

		maskMedicalService.deleteData(Long.valueOf(requestId));
		out.println("資料刪除成功 id = " + requestId);

	}
}