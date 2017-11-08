package hotelSystem;

public class GuestRoom extends Room {
	
	/**Contains list of different types of room: 1:Single, 2:Twin, 3:Double, 4:Suite.
	*/
	private String[] roomTypes = {"Single","Twin","Double","Twin Double","Suite"};
	/**Flags whether housekeeping is needed
	*/
	private Boolean needsHousekeeping;

	//Constructors
	public GuestRoom(String s,int i) {
		super(s,i);
		setPeriodLength(24);
		setBasePrice(100.00);
		this.roomPrice = getBasePrice() + (getRoomType()*getPriceMult());
		this.needsHousekeeping = false;
	}
	
	//Accessors:
	/**Returns needsHousekeeping
	*/
	public Boolean getNeedsHousekeeping(){
		return needsHousekeeping;
	}

	
	/**Returns String containing room status
	*/
	public String toString() {
		return super.toString()+"\nRoom Type: "+roomTypes[getRoomType()]+"\nPeriod Length: "+getPeriodLength()+"\nPrice: "+String.format("$%2.2f", getRoomPrice())+"\nNeeds Housekeeping: "+getNeedsHousekeeping();
	}

	//Mutators:
	/**Sets needsHousekeeping.
	*/
	public void setNeedsHousekeeping(Boolean b){
		this.needsHousekeeping = b;
	}

}
