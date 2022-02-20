package tw.com.fcb.sample.kai.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Data
//@Builder
//定期定額前十名交易戶數證券統計
public class FileSecurities {
	private String securitiesOrder;
	private String stockCode;
	private String stockName;
	private String stockTransaction;
	private String etfCode;
	private String etfName;
	private String etfTransaction;
	private FileCurrencyEnum currencyEnum;
}