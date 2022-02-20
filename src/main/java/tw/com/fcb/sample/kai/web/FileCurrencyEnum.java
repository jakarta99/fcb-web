package tw.com.fcb.sample.kai.web;

public enum FileCurrencyEnum {
	USD, EUR, JPY, ZAR, TWD,
}


//CREATE type curr_enum as enum('USD', 'EUR', 'JPY', 'ZAR', 'TWD');
//ALTER TABLE stock ADD COLUMN curr_code curr_enum;
//SELECT * FROM stock;