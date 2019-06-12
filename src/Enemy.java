import java.util.Random;						//Allows for generation of random numbers.

/**
 * Represents an Enemy.
 * 
 * @author Jonathan Sohrabi 2018
 */
public class Enemy extends Entity {
	/**
	 * The held Item of the Enemy.
	 */
	private Item item;
	
	/**
	 * Constructor for the Enemy object.
	 * 
	 * @param n					The name of the Enemy.
	 * @param q					The quip of the Enemy.
	 * @param l					The level of the Enemy.
	 * @param m					The max HP of the Enemy.
	 * @param i					The held Item of the Enemy.
	 */
	public Enemy( String n, String q, int l, int m, Item i ) {
		super( n, q, l, m );					//Calls the Entity superclass constructor with the given parameters.
		this.item = i;							//Sets this object's item field to the parameter i in the constructor's arguments.
	}
	
	/**
	 * Gets the Item held by the Enemy.
	 * 
	 * @return					The Enemy's held Item.
	 */
	public Item getItem() {
		return this.item;						//Returns this Enemy's held Item.
	}

	/**
	 * Called for the attack "turn" of the Enemy in the combat phase.
	 * 
	 * @param e					The target of the Enemy's attacks.
	 */
	@Override
	public void attack( Entity e ) {
		Random random = new Random();																				//Creates new Random object to allow for random number generation.
		int damage = ( random.nextInt( 4 ) + 1 ) * this.getLevel();													//Generates a Random number in the range of [1,4] and multiplies it by Enemy's level to get attack damage.
		e.takeDamage( damage );																						//Deals damage to the Enemy's target.
		System.out.println( this.getName() + " attacks " + e.getName() + " for " + damage + " damage" );			//Writes Enemy's damage to the console.
	}
}