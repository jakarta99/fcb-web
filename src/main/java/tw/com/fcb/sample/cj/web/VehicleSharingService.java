package tw.com.fcb.sample.cj.web;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VehicleSharingService {

    List<VehicleSharing> result = new ArrayList<VehicleSharing>();
    HashMap<Integer, Integer> total = new HashMap<Integer, Integer>();


    public List<VehicleSharing> loadFromFile() throws IOException, SQLException {

        DataInputStream in = new DataInputStream(new FileInputStream(new File("/Users/xiechengrong/Desktop/workspace/fcb-io/VehicleSharing.csv")));
        BufferedReader br= new BufferedReader(new InputStreamReader(in,"MS950"));

        String lineData;
        int idx = 0;
        int year;
        int month;
        int yearLast = 0;
        int yearTotal = 0;

        VehicleRepository repository = new VehicleRepository();
        System.out.println(repository.truncate());

        while ((lineData = br.readLine()) != null) {
            idx = idx+1;
            if(idx == 1) continue;

            lineData = lineData.replace(" ","");
            String[] data = lineData.split(",");

            VehicleSharing vehicleSharing = new VehicleSharing();

            String tempYearMonth = data[0];
            String tempYear;
            String tempMonth;
            tempYear = tempYearMonth.substring(0,tempYearMonth.indexOf("年"));
            tempMonth = tempYearMonth.substring(4,tempYearMonth.indexOf("月"));

            year = Integer.valueOf(tempYear);
            month = Integer.valueOf(tempMonth);

            vehicleSharing.setYear(year);
            vehicleSharing.setMonth(month);

            vehicleSharing.setBrand(VehicleBrandEnum.valueOf(data[1]));

            switch(data[2]) {
                case "共享小客車":
                    vehicleSharing.setType(1);
                    break;
                case "共享機車":
                    vehicleSharing.setType(2);
                    break;
                default:
                    vehicleSharing.setType(0);
            }

            String amountString;
            int amount = 0;
            if (lineData.indexOf("\"") == -1){
                amount = Integer.valueOf(data[3]);
                vehicleSharing.setAmount(amount);
            }else {
                amountString = lineData.substring(lineData.indexOf("\"")+1,lineData.indexOf("\"",lineData.indexOf("\"")+1));
                amountString = amountString.replace(",","");
                amount = Integer.valueOf(amountString);
                vehicleSharing.setAmount(amount);
            }

            System.out.println(vehicleSharing);
            result.add(vehicleSharing);

            repository.insert(vehicleSharing);

            if (year == yearLast && month == 12){
                yearTotal += amount;
            }else if (yearLast == 0){

            } else {
                total.put(yearLast,yearTotal);
                yearTotal = 0;
            }

            yearLast = year;

        }

        total.put(yearLast,yearTotal);
        for (Integer i : total.keySet()) {
            System.out.println( i + " 年12月" + "共有 " + total.get(i) + " 台共享交通工具");
        }

        return result;
    }

}
