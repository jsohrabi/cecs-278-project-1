import java.io.*;						//Allows for usage of files.
import java.util.ArrayList;				//Allows for usage of ArrayList objects.
import java.util.Random;				//Allows for generation of random numbers.
import java.util.Scanner;				//Allows for reading of textfiles.

/**
 * Used for generating Enemies.
 * 
 * @author Jonathan Sohrabi 2018
 */
public class EnemyGenerator {
	/**
	 * Holds templates for each different kind of Enemy.
	 */
	private ArrayList<Enemy> enemyList;
	/**
	 * Used for random Item generation when generating new Enemy objects.
	 */
	private ItemGenerator ig;
	
	/**
	 * Default constructor for the EnemyGenerator class. Reads and parses Enemy information from the "EnemyList.txt" textfile.
	 */
	public EnemyGenerator() {
		enemyList = new ArrayList<Enemy>();						//ArrayList to hold templates for Enemy objects.
		this.ig = new ItemGenerator();							//Creates a new instance of an ItemGenerator.
		final String file = "./textfiles/EnemyList.txt";		//Filepath of the EnemyList.txt file.
		Scanner read = null;									//Creates a null Scanner object.
		try {
			read = new Scanner( new File( file ) );				//Allows for reading of the EnemyList.txt file.
			String line;										//Null String to be used as the currently read line of the textfile.
			String enemyParams[];								//Empty String array to hold read-in Enemy parameters.
			
			//Loop at least once while the textfile still has unread lines.
			do {
				line = read.nextLine();							//Read in the next line of the textfile.
				enemyParams = line.split( "," );				//Split the line at each comma and fill enemyParams array with the values between commas.
				if ( enemyParams[3].equals( "p" ) )				//If the Enemy is supposed to be a physical Enemy (denoted by 'p'), create a new regular Enemy template.
					enemyList.add( new Enemy( enemyParams[0], enemyParams[1], 1, Integer.parseInt( enemyParams[2] ), null ) );
				if ( enemyParams[3].equals( "m" ) ) {			//If the Enemy is supposed to be a magical Enemy (denoted by 'm'), create a new MagicalEnemy template for that Enemy.
					enemyList.add( new MagicalEnemy( enemyParams[0], enemyParams[1], 1, Integer.parseInt( enemyParams[2] ), null ) );
				}
			} while ( read.hasNext() );
			read.close();										//Close the Scanner object.
		//If an IOException occured during file reading, write an error message to the console and close the program.
		} catch ( IOException e ) {
			System.out.println( "An error occured while loading the enemy list. The program will now exit." );
			System.exit( 0 );
		}
	}
	
	/**
	 * Generates an Enemy from the ArrayList of Enemy templates and returns a new instance of that Enemy. If the Enemy is a Magical type, the generated Enemy will be a MagicalEnemy.
	 * 
	 * @param level					The level of the Enemy to be generated.
	 * @return						The generated Enemy.
	 */
	public Enemy generateEnemy( int level ) {
		Random random = new Random();						//Create new Random object.
		int eRng = random.nextInt( enemyList.size() );		//Generate a random index of the enemyList ArrayList.
		Enemy eTemplate = enemyList.get( eRng );			//Get the Enemy template at the random index of the ArrayList.
		//If the template Enemy is an instance of a MagicalEnemy, return a new MagicalEnemy object with the template values, parameterized level, and a randomly generated Item.
		if ( eTemplate instanceof MagicalEnemy )
			return new MagicalEnemy( eTemplate.getName(), eTemplate.getQuip(), level, eTemplate.getMaxHP() * level, ig.generateItem() );
		//If the template Enemy is not an instance of a MagicalEnemy, return a new Enemy with the template values, parameterized level, and a randomly generated Item.
		else
			return new Enemy( eTemplate.getName(), eTemplate.getQuip(), level, eTemplate.getHP() * level, ig.generateItem() );
	}
}
