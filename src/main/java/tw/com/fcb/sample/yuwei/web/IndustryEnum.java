package tw.com.fcb.sample.yuwei.web;


public enum IndustryEnum {
	financial(17,"金融服務業"),
	semiconductor(24,"半導體業"),
	steel(10,"鋼鐵業"),
	biomedical(22,"生技業");
	
	private int code;
	private String description;
	
	IndustryEnum(int code,String desc){
		this.code = code;
		this.description = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
}


//CREATE type industry_enum as enum('financial','semiconductor','steel','biomedical');
//ALTER TABLE Dividend ADD COLUMN industry industry_enum;
//select * from Dividend;