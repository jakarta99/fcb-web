package tw.com.fcb.sample.cj.web;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private static Statement stmt;

    public VehicleRepository() throws SQLException {

        Connection conn = getConnection();
        stmt = conn.createStatement();
    }

    public static Connection getConnection() throws SQLException {

        String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static List<VehicleSharing> getByYear(int year) throws SQLException {

        String sqlCmd = "select * from vehiclesharing where year = '" + year + "'";
        ResultSet rs = stmt.executeQuery(sqlCmd);



        List<VehicleSharing> vehicleSharingList = new ArrayList<VehicleSharing>();
        while (rs.next()) {
            VehicleSharing vehicleSharing = new VehicleSharing();
            vehicleSharing.setId(rs.getInt("id"));
            vehicleSharing.setYear(rs.getInt("year"));
            vehicleSharing.setMonth(rs.getInt("month"));
            vehicleSharing.setBrand(VehicleBrandEnum.valueOf(rs.getString("brand")));
            vehicleSharing.setType(rs.getInt("type"));
            vehicleSharing.setAmount(rs.getInt("amount"));
//            System.out.println("!!!"+vehicleSharing);
            vehicleSharingList.add(vehicleSharing);
//            System.out.println(vehicleSharingList);
        }
        return vehicleSharingList;
    }

    public static int insert(VehicleSharing vehicleSharing) throws SQLException {

        String sqlCmd = "INSERT INTO vehiclesharing (year, month, brand, type, amount) values ("+vehicleSharing.getYear()+", "
                +     vehicleSharing.getMonth()+" , "
                + "'"+vehicleSharing.getBrand()+"', "
                +     vehicleSharing.getType()+" , "
                +     vehicleSharing.getAmount()
                + ")";
        stmt.executeUpdate(sqlCmd);

        return -1;

    }

    public static int truncate() throws SQLException {

        String sqlCmd = "TRUNCATE TABLE vehiclesharing;";
        stmt.executeUpdate(sqlCmd);

        return -1;

    }

    public static List<VehicleSharing> getByID(String id) throws SQLException {

        String sqlCmd = "select * from vehiclesharing where id = '" + id + "'";
        ResultSet rs = stmt.executeQuery(sqlCmd);



        List<VehicleSharing> vehicleSharingList = new ArrayList<VehicleSharing>();
        while (rs.next()) {
            VehicleSharing vehicleSharing = new VehicleSharing();
            vehicleSharing.setId(rs.getInt("id"));
            vehicleSharing.setYear(rs.getInt("year"));
            vehicleSharing.setMonth(rs.getInt("month"));
            vehicleSharing.setBrand(VehicleBrandEnum.valueOf(rs.getString("brand")));
            vehicleSharing.setType(rs.getInt("type"));
            vehicleSharing.setAmount(rs.getInt("amount"));
//            System.out.println("!!!"+vehicleSharing);
            vehicleSharingList.add(vehicleSharing);
//            System.out.println(vehicleSharingList);
        }
        return vehicleSharingList;
    }

    public static List<VehicleSharing> findAll() throws SQLException {

        String sqlCmd = "select * from vehiclesharing;";
        ResultSet rs = stmt.executeQuery(sqlCmd);


        List<VehicleSharing> vehicleSharingList = new ArrayList<VehicleSharing>();
        while (rs.next()) {
            VehicleSharing vehicleSharing = new VehicleSharing();
            vehicleSharing.setId(rs.getInt("id"));
            vehicleSharing.setYear(rs.getInt("year"));
            vehicleSharing.setMonth(rs.getInt("month"));
            vehicleSharing.setBrand(VehicleBrandEnum.valueOf(rs.getString("brand")));
            vehicleSharing.setType(rs.getInt("type"));
            vehicleSharing.setAmount(rs.getInt("amount"));
//            System.out.println("!!!"+vehicleSharing);
            vehicleSharingList.add(vehicleSharing);
//            System.out.println(vehicleSharingList);
        }
        return vehicleSharingList;
    }

    public static int update(int id, int amount) throws SQLException {

        String sqlCmd = "UPDATE vehiclesharing" +
                " SET amount = " + amount +
                " WHERE id = "+ id + ";" ;
        Integer result = stmt.executeUpdate(sqlCmd);

        return result;
    }

    public static int delete(int id) throws SQLException {

        String sqlCmd = "DELETE FROM vehiclesharing" +
                " WHERE id = " + id + " ;" ;
        Integer result = stmt.executeUpdate(sqlCmd);

        return result;
    }

}
