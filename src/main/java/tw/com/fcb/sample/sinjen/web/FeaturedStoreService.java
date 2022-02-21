package tw.com.fcb.sample.sinjen.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeaturedStoreService {
	private static final String getWorkingDirectory= "c:\\fcb\\fcb-io\\";
	private static final String TAB = "\t";
	private static final String NEW_LINE = "\r\n";
	//private static final String REPORT_title = "新北市商圈特色商家";
	private static final String HEADING_1 = "商圈名稱";
	private static final String HEADING_2 = "特色商家";
	private static final String HEADING_3 = "店家電話";
	private static final String HEADING_4 = "店家地址";
	private static final String HEADING_5 = "店家商品";
	private static final String HEADING_6 = "twd97緯度";
	private static final String HEADING_7 = "twd97經度";
	private static final String HEADING_8 = "wgs84a緯度";
	private static final String HEADING_9 = "wgs84a經度";
	private static final String txtTitle = HEADING_1+TAB+HEADING_2+TAB+HEADING_3+TAB+HEADING_4+TAB+HEADING_5+TAB
			+HEADING_6+TAB+HEADING_7+TAB+HEADING_8+TAB+HEADING_9;

	
	public List<StoreRow> loadFromFile() throws IOException {

		//新北市商圈特色商家
		//https://data.gov.tw/dataset/125593
		String fileName = "Featured Store.csv";
		String openApiFile = getWorkingDirectory+fileName;
		List<StoreRow> result = new ArrayList<StoreRow>();

		BufferedReader br = new BufferedReader(new FileReader(openApiFile));
		String lineData;

		int idx = 0;

		while ((lineData = br.readLine()) != null) {

			idx = idx + 1;

			if (idx == 1)
				continue;

			//System.out.println(lineData);
			String[] data = lineData.split(",");

			StoreRow storeRow = new StoreRow();
			storeRow.setZone(data[0].replace("\"", "").trim());
			storeRow.setStore(data[1].replace("\"", "").trim());
			storeRow.setStoreTel(data[2].replace("\"", "").trim());
			storeRow.setStoreAdd(data[3].replace("\"", "").trim());
			storeRow.setStoreProduct(data[4].replace("\"", "").trim());
			storeRow.setTwd97X(data[5].replace("\"", "").trim());
			storeRow.setTwd97Y(data[6].replace("\"", "").trim());
			storeRow.setWgs84aX(data[7].replace("\"", "").trim());
			storeRow.setWgs84aY(data[8].replace("\"", "").trim());
			//System.out.println(storeRow);

			result.add(storeRow);

		}
		System.out.println(fileName+"讀檔完成，共："+result.size()+ "筆資料");
		br.close();
		return result;
	}
	
	public void writeTxtFile(List<StoreRow> listResult) throws IOException {
		StoreRow row = null;
		String fileName = "Featured Store.txt";
		FileWriter bw = new FileWriter(getWorkingDirectory+fileName);
		bw.write(txtTitle + NEW_LINE); //輸出標題
		
		int size = listResult.size();
		for (int i = 0; i < size; i++) {
			row = (StoreRow) listResult.get(i);
			bw.write(new StringBuffer(row.getZone()).append(TAB)
					.append(row.getStore()).append(TAB)
					.append(row.getStoreTel()).append(TAB)
					.append(row.getStoreAdd()).append(TAB)
					.append(row.getStoreProduct()).append(TAB)
					.append(row.getTwd97X()).append(TAB)
					.append(row.getTwd97Y()).append(TAB)
					.append(row.getWgs84aX()).append(TAB)
					.append(row.getWgs84aY()).append(TAB)
					.append(NEW_LINE).toString());
		}
		System.out.println(fileName+"寫檔完成，共："+size+ "筆資料");
		bw.flush();
		bw.close();
	}
	
	public void runCrud() {
		FeaturedStoreRepository fsr = new FeaturedStoreRepository();
		List<StoreRow> list = new ArrayList<StoreRow>();
		List<StoreRow> addOne = new ArrayList<StoreRow>();
		StoreRow row = new StoreRow();

		// 讀取全部筆數資料
		list = fsr.getDBQueryResult();
		
		// List<StoreRow> list.size()
		//System.out.println("讀取DB:featured_store完成，共" + list.size() + "筆資料");
		System.out.println("讀取DB:featured_store完成，count共" + fsr.count() + "筆資料");
		
		// 新增-Insert單筆資料
		addOne.add(list.get(0));	//範例:複製第一筆資料
		fsr.insertDB(addOne);
		
		/* 新增-Insert全部筆數資料
		try {
			row = loadFromFile();
			fsr.insertDB(list);
		} catch (IOException e) {
			e.printStackTrace();
		} */
		
		// 查詢getById()
		int id = list.get(list.size()-1).getId().intValue();	//範例:撈最後一筆id查詢資料
		fsr.getById(id);

		// 更新-Update單筆資料
		row = list.get(list.size()-1);		//範例:撈最後一筆更新資料
		row.setStoreTel("(02)8677-6929");	//範例:更新電話號碼欄位
		fsr.update(row);

		// 刪除-Delete單筆資料
		fsr.delete(id);

	}
	
}
