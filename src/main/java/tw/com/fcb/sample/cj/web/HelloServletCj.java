package tw.com.fcb.sample.cj.web;

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
@WebServlet("/hello/cj")
public class HelloServletCj extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public HelloServletCj() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("Hello Cj id="+request.getParameter("id"));
        List<VehicleSharing> vehicleSharingList = new ArrayList<VehicleSharing>();

        String id = request.getParameter("id");

        if (request.getParameter("id") == null) {
            try {
                VehicleRepository vehicleRepository = new VehicleRepository();
                vehicleSharingList = vehicleRepository.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (VehicleSharing vehicleSharing:vehicleSharingList){
                String output = "Id = " + vehicleSharing.getId()+
                        " 年度   : " + vehicleSharing.getYear() +
                        " 月份   : " + vehicleSharing.getMonth() +
                        " 品牌   : " + vehicleSharing.getBrand() +
                        " 種類   : " + vehicleSharing.getType() +
                        " 數量 : " + vehicleSharing.getAmount();

                response.setContentType("text/html; charset=gbk");
                response.setCharacterEncoding("utf-8");
                out.println(output);
            }
        }else {
            try {
                VehicleRepository vehicleRepository = new VehicleRepository();
                vehicleSharingList = vehicleRepository.getByID(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (VehicleSharing vehicleSharing:vehicleSharingList){
                String output = "Id = " + vehicleSharing.getId()+
                        " 年度   : " + vehicleSharing.getYear() +
                        " 月份   : " + vehicleSharing.getMonth() +
                        " 品牌   : " + vehicleSharing.getBrand() +
                        " 種類   : " + vehicleSharing.getType() +
                        " 數量 : " + vehicleSharing.getAmount();

                response.setContentType("text/html; charset=gbk");
                response.setCharacterEncoding("utf-8");
                out.println(output);
            }
        }



        // response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
