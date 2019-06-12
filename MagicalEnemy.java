import java.util.Random;			//Allows for generating random values.

/**
 * Represents Enemies who are able to use magic.
 * 
 * @author Jonathan Sohrabi 2018
 */
public class MagicalEnemy extends Enemy implements Magical {
	/**
	 * Parameterized constructor for a MagicalEnemy.
	 * 
	 * @param n						The name of the MagicalEnemy.
	 * @param q						The quip of the MagicalEnemy.
	 * @param l						The level of the MagicalEnemy.
	 * @param m						The max HP of the MagicalEnemy.
	 * @param i						The item held by the MagicalEnemy.
	 */
	public MagicalEnemy( String n, String q, int l, int m, Item i ) {
		super( n, q, l, m, i );		//Calls the Enemy superclass constructor with the given parameters.
	}
	
	/**
	 * Chooses a random attack for the MagicalEnemy's "turn" in the combat phase. Each attack the MagicalEnemy can perform are:
	 * Regular Attack (40% chance)
	 * Magic Missile (30% chance)
	 * Fireball (20% chance)
	 * Thunderclap (10% chance)
	 * 
	 * @param e						The target of the MagicalEnemy's attack.
	 */
	public void attack( Entity e ) {
		Random random = new Random();						//Creates a new Random object.
		int attackType = random.nextInt( 100 ) + 1;			//Generates a random number in the range of [1,100] which we use to determine the type of attack.
		int damage = 0;										//The damage of the attack
		
		//If 1 < attackType <= 40, then MagicalEnemy does a regular attack.
		if ( ( attackType > 1 ) && ( attackType <= 40 ) ) {
			damage = ( random.nextInt( 4 ) + 1 ) * this.getLevel();				//Generates a Random number in the range of [1,4] and multiplies it by MagicalEnemy level to get attack damage.
			e.takeDamage( damage );												//Deals damage to MagicalEnemy's target.
			System.out.println( this.getName() + " strikes " + e.getName() + " for " + damage + " damage." );								//Write the attack to console.
		//If 40 < attackType <= 70, then MagicalEnemy does a Magic Missile attack.
		} else if (attackType > 40 && attackType <= 70) {
			damage = magicMissile();											//Set damage equal to return value of MagicMissile().
			e.takeDamage(damage);												//Deals damage to MagicalEnemy's target.
			System.out.println( this.getName() + " attacks " + e.getName() + " with a Magic Missile for " + damage + " damage." );			//Write the attack to console.
		//If 70 < attackType <= 90, then MagicalEnemy does a Fireball attack.
		} else if ( attackType > 70 && attackType <= 90 ) {
			damage = fireball();												//Set damage equal to return value of Fireball().
			e.takeDamage( damage );												//Deals damage to MagicalEnemy's target.
			System.out.println( this.getName() + " attacks " + e.getName() + " with a Fireball for " + damage + " damage." );				//Write the attack to console.
		//If attackType > 90, then MagicalEnemy does a Thunderclap attack.
		} else {
			damage = thunderclap();												//Set damage equal to return value of Thunderclap().
			e.takeDamage( damage );												//Deals damage to MagicalEnemy's target.
			System.out.println( this.getName() + " attacks " + e.getName() + " with a Thunderclap for " + damage + " damage." );			//Write the attack to console.
		}
	}

	/**
	 * Calculates the damage of a Magic Missile spell. Damage is a random number from [1,5], multiplied by the MagicalEnemy's level.
	 * 
	 * @return						The damage of the Magic Missile spell.
	 */
	@Override
	public int magicMissile() {
		Random random = new Random();								//Create a new Random object.
		return ( random.nextInt( 5 ) + 1 ) * this.getLevel();		//Generates a random number in the range of [1,5] and multiplies it by the MagicalEnemy level to get the Magic Missile damage and returns it.
	}

	/**
	 * Calculates the damage of a Fireball spell. Damage is a random number from [1,6], multiplied by the MagicalEnemy's level.
	 * 
	 * @return						The damage of the Fireball spell.
	 */
	@Override
	public int fireball() {
		Random random = new Random();								//Create a new Random object.
		return ( random.nextInt( 6 ) + 1 ) * this.getLevel();		//Generates a random number in the range of [1,6] and multiplies it by the MagicalEnemy level to get the Fireball damage and returns it.
	}

	/**
	 * Calculates the damage of a Thunderclap spell. Damage is a random number from [1,7], multiplied by the MagicalEnemy's level.
	 * 
	 * @return						The damage of the Thunderclap spell.
	 */
	@Override
	public int thunderclap() {
		Random random = new Random();								//Create a new Random object.
		return ( random.nextInt( 7 ) + 1 ) * this.getLevel();		//Generates a random number in the range of [1,7] and multiplies it by the MagicalEnemy level to get the Thunderclap damage and returns it.
	}
}
