package hotelSystem;

public class Housekeeping {
	private int id;
	private int roomId;
	private String status;
	private String asssignment;
	
	
	
	
	public Housekeeping(int id, int roomId, String status, String asssignment) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.status = status;
		this.asssignment = asssignment;
		
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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