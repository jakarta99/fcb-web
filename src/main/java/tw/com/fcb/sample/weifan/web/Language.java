package tw.com.fcb.sample.weifan.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter

public class Language {

	private int Seq;
	private String CreateDate;
	private String year;
	private String ChineseName;
	private LanguageLevelEnum Level;
	private String SignNum;
	private String JoinNum;
	private String PassNum;

	public String toSqlcmd() {
		return "insert into language values(" + Seq + ",'" + CreateDate + "', '" + year + "', '" + ChineseName
				+ "','" + Level + "', '" + SignNum + "', '" + JoinNum + "', '" + PassNum + "');";
	}


	
	

	
}
