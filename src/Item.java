/**
 * Used in the Item system of the game.
 * 
 * @author Jonathan Sohrabi 2018
 */
public class Item {
	/**
	 * The name of the Item.
	 */
	private String name;
	/**
	 * The price and health value of the item.
	 */
	private int value;
	
	/**
	 * Constructor for the Item class. Creates a new Item object with the given parameters.
	 * 
	 * @param n						Name of the Item.
	 * @param v						Value of the Item.
	 */
	public Item( String n, int v ) {
		this.name = n;				//Set Item name to parameter 'n'.
		this.value = v;				//Set Item value to parameter 'v'.
	}
	
	/**
	 * Gets the name of the Item.
	 * 
	 * @return						The name of the Item.
	 */
	public String getName() {
		return this.name;			//Return Item name field.
	}
	
	/**
	 * Gets the value of the Item.
	 * 
	 * @return						The value of the Item.
	 */
	public int getValue() {
		return this.value;			//Return Item value field.
	}
}