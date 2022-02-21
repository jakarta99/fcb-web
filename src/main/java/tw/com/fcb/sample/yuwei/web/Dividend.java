package tw.com.fcb.sample.yuwei.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dividend {
	
	Long id;
	
	private int allocationOfAnnual;
	
	private double cashDividend;
	
	private double stockDividend;
	
	private double  total;
	
	private double totalCashDividendUnit;
	
	private double shareholdingRatio;
	
	private String issuingCompany;

	private IndustryEnum industry;
}
