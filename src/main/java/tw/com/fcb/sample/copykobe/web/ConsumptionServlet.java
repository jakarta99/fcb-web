package tw.com.fcb.sample.copykobe.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;



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
import lombok.Data;
import tw.com.fcb.sample.jilldolala25.web.MaskMedical;


/**
     * Servlet implementation class HelloServlet
     */
    @Data
    @WebServlet("/consumption")
    public class ConsumptionServlet extends HttpServlet{
        private static final long serialVersionUID = 1L;

        /**
         * Default constructor.
         */
        public ConsumptionServlet() {
            // TODO Auto-generated constructor stub
        }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            PrintWriter out = response.getWriter();

            ConsumptionRepository consumptionRepository = new ConsumptionRepository();
            List<Consumption> resultList = new ArrayList<>();
            if (request.getParameter("id") == null) {
                try {
                    resultList = consumptionRepository.selectAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (Consumption consumption : resultList) {
                    String output = "Id = " + consumption.getId()+
                            " 年月   : " + consumption.getYearMonth() +
                            " 都市   : " + consumption.getRegion() +
                            " 境外銷費比   : " + consumption.getCrossBorderPercentage() +
                            " 總交易量   : " + consumption.getTotalTradeCount() +
                            " 境外交易量 : " + consumption.getCrossBorderCount();

                    response.setContentType("text/html; charset=gbk");
                    response.setCharacterEncoding("utf-8");
                    out.println(output);
                }
            }else{
                String requestId = request.getParameter("id");
                try {
                    resultList = consumptionRepository.selectById(requestId);
                    for (Consumption consumption : resultList) {
                        String output = "Id = " + consumption.getId() +
                                " 年月   : " + consumption.getYearMonth() +
                                " 都市   : " + consumption.getRegion() +
                                " 境外銷費比   : " + consumption.getCrossBorderPercentage() +
                                " 總交易量   : " + consumption.getTotalTradeCount() +
                                " 境外交易量 : " + consumption.getCrossBorderCount();

                        response.setContentType("text/html; charset=gbk");
                        response.setCharacterEncoding("utf-8");
                        out.println(output);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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


