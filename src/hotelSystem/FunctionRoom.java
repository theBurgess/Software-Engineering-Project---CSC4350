package hotelSystem;

/**FunctionRoom extends the Room superclass. Contains roomType.
*/
public class FunctionRoom extends Room {

	//State:
	/**Contains list of different types of room: 1: Conference Room, 2: Banquet Hall. (more code efficient than creating subclasses for each room type?)
	*/
	private String[] roomTypes = {"Conference Room","Banquet Hall"};
		
	/**Contains the number of people the function room can hold
	*/
	private int occupancy;
	
	//Constructors:
	/** Sets above state to initial values
	*/
	public FunctionRoom(String s, int i) {
		super(s,i);
		setPeriodLength(1);
		setBasePrice(50.00);
		this.roomPrice = getBasePrice() + (getRoomType()*getPriceMult());
		if(i==2){
			this.occupancy = 75;
		}
		else{
			this.occupancy = 15;
		}	
	}
	
	//Accessors:
	
	/**Returns occupancy.
	*/
	public int getOccupancy() {
		return this.occupancy;
	}
	
	/**Returns String containing room status
	*/
	public String toString() {
		return super.toString()+"\nRoom Type: "+roomTypes[getRoomType()]+"\nPeriodLength: "+getPeriodLength()+"\nPrice: "+String.format("$%2.2f", getRoomPrice())+"\nOccupancy: "+getOccupancy();
	}

	
	//Mutators:
	/**Sets Occupancy.
	*/
	public void setOccupancy(int i) {
		this.occupancy = i;
	}
}