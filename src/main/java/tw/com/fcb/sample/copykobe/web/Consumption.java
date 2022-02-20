package tw.com.fcb.sample.copykobe.web;

import lombok.Data;

@Data
public class Consumption {
    private Long id;
    private String yearMonth;
//    private String region;
    private ConsumptionRegionEnum region;
    private String crossBorderPercentage;
    private double cardCount;
    private double totalTradeCount;
    private double totalTradeAmount;
    private double crossBorderCount;
    private double totalCrossBorderAmount;

}
