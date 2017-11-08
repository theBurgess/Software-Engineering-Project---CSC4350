package hotelSystem;

/** Room is a representation of the rooms in the hotel that can be reserved. 
*Contains: state and behaviour for adding and editing rooms. 
*Room class is a *superclass that is inherited by guest room, and function room.
*/

public class Room {
	
//State:
	/** Contains the number the room will be identified by
	*/
	private String roomNumber;

	/**Contains roomType
	*/
	private int roomType;

	
	/** Flag for whether the room is currently occupied 
	*/
	private Boolean isOccupied;

	/** Contains length in hours of one reservation period.
	*/
	private int periodLength;

	/** Base Price of the room for one period. 
	*/
	private double basePrice;
	
	/**Contains price Multiplier for better room type.
	*/
	private double priceMult;

	/**Contains price per period
	*/
	public double roomPrice;

	
	//Constructors
	public Room(String s,int i) {
		this.roomNumber = s;
		this.isOccupied = false;
		this.roomType = (i-1); 
		this.priceMult = 50.00;
	}
	public Room() {
		this("undef.", 1);
	}
	
	//Accessors
	/** Returns roomNumber
	*/
	public String getRoomNumber() {
		return roomNumber;
	}
	/**Returns room type.
	*/
	public int getRoomType() {
		return roomType;
	}
	/** Returns isOccupied
	*/
	public Boolean getIsOccupied() {
		return isOccupied;
	}
	/** Returns periodLength
	*/
	public int getPeriodLength() {
		return periodLength;
	}
	/** Returns basePrice
	*/
	public double getBasePrice() {
		return basePrice;
	}
	/**Returns priceMult
	*/
	public double getPriceMult() {
		return priceMult;
	}
	/**Returns price per night based on room type
	*/
	public double getRoomPrice(){
		return getBasePrice() + (getRoomType()*getPriceMult());
	}
	/**Returns String containing room status
	*/
	public String toString() {
		return "Room Number: "+roomNumber+"\nOccupied: "+isOccupied;
	}
	
	//Mutators
	/** Sets roomNumber
	*/
	public void setRoomNumber(String s) {
		this.roomNumber = s;
	}
	
	/**Sets room type.
	*/
	public void setRoomType(int i) {
		this.roomType = i-1;
	}
	/** Sets isOccupied
	*/
	public void setIsOccupied(Boolean b) {
		this.isOccupied = b;
	}
	/** Sets periodLength
	*/
	public void setPeriodLength(int i) {
		this.periodLength = i;
	}
	/** Sets basePrice
	*/
	public void setBasePrice(double d) {
		this.basePrice = d;
	}
	/**Sets priceMult
	*/
	public void setPriceMult(double d) {
		this.priceMult = d;
	}
	

	
	
	
}