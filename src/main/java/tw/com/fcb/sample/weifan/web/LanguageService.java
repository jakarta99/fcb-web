package tw.com.fcb.sample.weifan.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LanguageService {
	
	public List<Language> loadFromFile() throws IOException {
		
		String FileName = "d:\\A53000000A-110067-003.csv";
		FileReader fr = new FileReader(FileName);
		BufferedReader br = new BufferedReader(fr);
		List<Language> result = new ArrayList<Language>();
		
		String lineData;
		
		while( (lineData = br.readLine()) != null) {
			lineData = lineData.replaceAll("\"", "");
			String[] data = lineData.split(",");
			
			
			Language language = new Language();
			
			try {
			language.setSeq(Integer.valueOf(data[0]));
			language.setCreateDate(data[1]);
			language.setYear(data[2]);
			language.setChineseName(data[3]);
			language.setLevel(LanguageLevelEnum.valueOf(data[4]));
			language.setSignNum(data[5]);
			language.setJoinNum(data[6]);
			language.setPassNum(data[7]);
			result.add(language);
			}catch(NumberFormatException e) {
				System.out.println("資料型態錯誤");
			}
			
		}

		br.close();
		return result;
		
	}
	
	
}
