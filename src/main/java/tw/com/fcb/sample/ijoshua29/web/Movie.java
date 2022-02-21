package tw.com.fcb.sample.ijoshua29.web;

public class Movie {
	
	private Long id;
	private String code;
	private String name;
	private int price;
	private MovieRoomEnum room;
	
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", code=" + code + ", name=" + name + ", price=" + price + ", room=" + room + "]";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public MovieRoomEnum getRoom() {
		return room;
	}
	public void setRoom(MovieRoomEnum room) {
		this.room = room;
	}
	
	
}