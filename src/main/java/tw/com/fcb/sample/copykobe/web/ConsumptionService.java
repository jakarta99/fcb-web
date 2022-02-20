package tw.com.fcb.sample.copykobe.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionService {

    public List<Consumption> inputFile() throws IOException, SQLException {
        //信用卡跨境消費統計資料檔位置
        String fileLocation = "/Users/copykobe/Downloads/BANK_MCT_ALL_ICR.CSV";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
        String rowData;
        int index = 0;

        ConsumptionRepository consumptionRepository = new ConsumptionRepository();
        Connection conn = consumptionRepository.getConnection();
        Statement stmt = conn.createStatement();

        List<Consumption> result = new ArrayList<Consumption>();
        while ((rowData = bufferedReader.readLine()) != null){
            index++;
            if (index == 1){
                continue;
            }
            String [] data = rowData.split(",");
            Consumption consumption = new Consumption();
            consumption.setYearMonth(data[0]);
            //地區別代碼：63000000臺北市、64000000高雄市、65000000新北市、66000000臺中市、67000000臺南市、68000000桃園市
            switch (data[1]){
                case "63000000":
//                    consumption.setRegion("臺北市");
                    consumption.setRegion(ConsumptionRegionEnum.臺北市);
                    break;
                case "64000000":
                    consumption.setRegion(ConsumptionRegionEnum.高雄市);
                    break;
                case "65000000":
                    consumption.setRegion(ConsumptionRegionEnum.新北市);
                    break;
                case "66000000":
                    consumption.setRegion(ConsumptionRegionEnum.臺中市);
                    break;
                case "67000000":
                    consumption.setRegion(ConsumptionRegionEnum.臺南市);
                    break;
                case "68000000":
                    consumption.setRegion(ConsumptionRegionEnum.桃園市);
                    break;
                default:
//                    consumption.setRegion("其它縣市");
                    consumption.setRegion(ConsumptionRegionEnum.其它縣市);
            }
            //計算跨境消費比重
            double CrossBorderTradePercentage;
            CrossBorderTradePercentage = (Double.parseDouble(data[5]) / Double.parseDouble(data[3]))*100;
            DecimalFormat df = new DecimalFormat("###.##");
            consumption.setCrossBorderPercentage(df.format(CrossBorderTradePercentage) + "%");

            consumption.setCardCount(Double.parseDouble(data[2]));
            consumption.setTotalTradeCount(Double.parseDouble(data[3]));
            consumption.setTotalTradeAmount(Double.parseDouble(data[4]));
            consumption.setCrossBorderCount(Double.parseDouble(data[5]));
            consumption.setTotalCrossBorderAmount(Double.parseDouble(data[6]));

            System.out.println(consumption);

            //insert DB
            String sqlCmd = "INSERT INTO consumption (YearMonth,Region,CrossBorderPercentage,"+
                                                     "CardCount,TotalTradeCount,TotalTradeAmount,"+
                                                     "CrossBorderCount,TotalCrossBorderAmount) " +
                                                     " values ("
                    + "'"+consumption.getYearMonth()+"', "
                    + "'"+consumption.getRegion()+"', "
                    + "'" +consumption.getCrossBorderPercentage()+"', "
                    + "" +consumption.getCardCount()+", "
                    + "" +consumption.getTotalTradeCount()+", "
                    + "" +consumption.getTotalTradeAmount()+", "
                    + "" +consumption.getCrossBorderCount()+", "
                    + "" +consumption.getTotalCrossBorderAmount()+""
                    + ") ";
            System.out.println(sqlCmd);
            stmt.executeUpdate(sqlCmd);

            result.add(consumption);
        }
        conn.close();
        stmt.close();

        return result;
    }



}
