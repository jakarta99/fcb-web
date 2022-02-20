package tw.com.fcb.sample.copykobe.web;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionRepository {

    public Connection getConnection() throws SQLException {
        //Class.forName("org.postgresql.Driver");
        String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(dbUrl, username, password);

    }

    public List<Consumption> selectAll() throws SQLException {
        //連接DB
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        List resultList = new ArrayList<Consumption>();
        String sqlCmd = "select * from consumption";
        ResultSet resultSet = stmt.executeQuery(sqlCmd);
        Consumption consumption;
        while (resultSet.next()){
            consumption = new Consumption();
            consumption.setId(resultSet.getLong("id"));
            consumption.setYearMonth(resultSet.getString("yearmonth"));
//            consumption.setRegion(resultSet.getString("region"));
            consumption.setRegion(ConsumptionRegionEnum.valueOf(resultSet.getString("region")));
            consumption.setCrossBorderPercentage(resultSet.getString("crossBorderPercentage"));
            consumption.setCardCount(resultSet.getDouble("cardCount"));
            consumption.setTotalTradeCount(resultSet.getDouble("totalTradeCount"));
            consumption.setTotalTradeAmount(resultSet.getDouble("totalTradeAmount"));
            consumption.setCrossBorderCount(resultSet.getDouble("crossBorderCount"));
            consumption.setTotalCrossBorderAmount(resultSet.getDouble("totalCrossBorderAmount"));
            resultList.add(consumption);
        }

        conn.close();
        stmt.close();
        return resultList;
    }

    public List<Consumption> selectYearMonth(String yearMonth) throws SQLException {
        //連接DB
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        List resultList = new ArrayList<Consumption>();
        String sqlCmd = "select * from consumption where yearmonth = '" + yearMonth + "'";
        ResultSet resultSet = stmt.executeQuery(sqlCmd);
        Consumption consumption;
        while(resultSet.next()){
            consumption = new Consumption();
            consumption.setId(resultSet.getLong("id"));
            consumption.setYearMonth(resultSet.getString("yearmonth"));
//            consumption.setRegion(resultSet.getString("region"));
            consumption.setRegion(ConsumptionRegionEnum.valueOf(resultSet.getString("region")));
            consumption.setCrossBorderPercentage(resultSet.getString("crossBorderPercentage"));
            consumption.setCardCount(resultSet.getDouble("cardCount"));
            consumption.setTotalTradeCount(resultSet.getDouble("totalTradeCount"));
            consumption.setTotalTradeAmount(resultSet.getDouble("totalTradeAmount"));
            consumption.setCrossBorderCount(resultSet.getDouble("crossBorderCount"));
            consumption.setTotalCrossBorderAmount(resultSet.getDouble("totalCrossBorderAmount"));
            resultList.add(consumption);
        }
        return  resultList;
    }


    public List<Consumption> selectById(String id) throws SQLException {
        //連接DB
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        List resultList = new ArrayList<Consumption>();
        String sqlCmd = "select * from consumption where id = '" + id + "'";
        ResultSet resultSet = stmt.executeQuery(sqlCmd);
        Consumption consumption;
        while(resultSet.next()){
            consumption = new Consumption();
            consumption.setId(resultSet.getLong("id"));
            consumption.setYearMonth(resultSet.getString("yearmonth"));
//            consumption.setRegion(resultSet.getString("region"));
            consumption.setRegion(ConsumptionRegionEnum.valueOf(resultSet.getString("region")));
            consumption.setCrossBorderPercentage(resultSet.getString("crossBorderPercentage"));
            consumption.setCardCount(resultSet.getDouble("cardCount"));
            consumption.setTotalTradeCount(resultSet.getDouble("totalTradeCount"));
            consumption.setTotalTradeAmount(resultSet.getDouble("totalTradeAmount"));
            consumption.setCrossBorderCount(resultSet.getDouble("crossBorderCount"));
            consumption.setTotalCrossBorderAmount(resultSet.getDouble("totalCrossBorderAmount"));
            resultList.add(consumption);
        }
        return  resultList;
    }

    public void updatePercentage(Long id,String percentage) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        PreparedStatement preparedStatement = null;
        String sqlCmd = "update consumption SET crossBorderPercentage = ? where id = ?";
        preparedStatement = conn.prepareStatement(sqlCmd);
        preparedStatement.setString(1,percentage);
        preparedStatement.setLong(2,id);

        int updateCount = preparedStatement.executeUpdate();
        System.out.println("共 " + updateCount + " 筆資料更新");
    }

    public void deleteYearMonth(String deleteYearMonth) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        PreparedStatement preparedStatement = null;
        String sqlCmd = "delete from consumption WHERE yearMonth = ?";
        preparedStatement = conn.prepareStatement(sqlCmd);
        preparedStatement.setString(1,deleteYearMonth);

        int deleteCount = preparedStatement.executeUpdate();
        System.out.println("共 " + deleteCount + " 筆資料刪除");
    }

    public List<Consumption> selectAllSortByPercentage() throws SQLException {
        //連接DB
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        List resultList = new ArrayList<Consumption>();
        String sqlCmd = "select * from consumption order by crossBorderPercentage desc";
        ResultSet resultSet = stmt.executeQuery(sqlCmd);
        Consumption consumption;
        while (resultSet.next()){
            consumption = new Consumption();
            consumption.setId(resultSet.getLong("id"));
            consumption.setYearMonth(resultSet.getString("yearmonth"));
//            consumption.setRegion(resultSet.getString("region"));
            consumption.setRegion(ConsumptionRegionEnum.valueOf(resultSet.getString("region")));
            consumption.setCrossBorderPercentage(resultSet.getString("crossBorderPercentage"));
            consumption.setCardCount(resultSet.getDouble("cardCount"));
            consumption.setTotalTradeCount(resultSet.getDouble("totalTradeCount"));
            consumption.setTotalTradeAmount(resultSet.getDouble("totalTradeAmount"));
            consumption.setCrossBorderCount(resultSet.getDouble("crossBorderCount"));
            consumption.setTotalCrossBorderAmount(resultSet.getDouble("totalCrossBorderAmount"));
            resultList.add(consumption);
        }

        conn.close();
        stmt.close();
        return resultList;
    }
}

//SQL Table initial
//select * from consumption
//        drop table consumption;
//
//        create table consumption (
//        id serial primary key,
//        yearMonth varchar (06),
//        region    varchar (10),
//        crossBorderPercentage  varchar (10),
//        cardCount double precision,
//        totalTradeCount double precision,
//        totalTradeAmount double precision,
//        crossBorderCount double precision,
//        totalCrossBorderAmount double precision
//        )
