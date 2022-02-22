package tw.com.fcb.sample.h25949.web;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FinancialIndicators {
	private long id;
	private int year;
	private BigDecimal exchangeRate;
	private BigDecimal foreign;
	private BigDecimal stockIndex;
	private BigDecimal stockAmount;

	/*
	@Override
	public String toString() {
		return "國內主要金融指標：年度=" + year + ", 匯率=" + exchangeRate + ", 外匯存底=" + foreign + ", 證券發行量加權股價指數=" + stockIndex
				+ ", 證券成交值=" + stockAmount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getForeign() {
		return foreign;
	}

	public void setForeign(BigDecimal foreign) {
		this.foreign = foreign;
	}

	public BigDecimal getStockIndex() {
		return stockIndex;
	}

	public void setStockIndex(BigDecimal stockIndex) {
		this.stockIndex = stockIndex;
	}

	public BigDecimal getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(BigDecimal stockAmount) {
		this.stockAmount = stockAmount;
	}
	*/
}
