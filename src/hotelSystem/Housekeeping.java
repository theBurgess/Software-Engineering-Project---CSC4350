package hotelSystem;

public class Housekeeping {
	
	

	private int roomId;
	private String roomType;
	private String status;
	private String asssignment;
	
	
	
	
	public Housekeeping(int roomId, String roomType, String status, String asssignment) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.status = status;
		this.asssignment = asssignment;
		
	}



	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAsssignment() {
		return asssignment;
	}

	public void setAsssignment(String asssignment) {
		this.asssignment = asssignment;
	}
	
	

}