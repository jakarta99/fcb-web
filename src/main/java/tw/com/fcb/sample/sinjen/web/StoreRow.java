package tw.com.fcb.sample.sinjen.web;

import lombok.Data;

@Data
public class StoreRow {

	private Long id;			//PRIMARY KEY
	private String zone;		//商圈名稱
	private String store;		//特色商家
	private String storeTel;	//店家電話
	private String storeAdd;	//店家地址
	private String storeProduct;//店家商品
	private String twd97X;		//twd97緯度
	private String twd97Y;		//twd97經度
	private String wgs84aX;		//wgs84a緯度
	private String wgs84aY;		//wgs84a經度
	
}
