import java.io.*;											//Allows for usage of files.
import java.util.Scanner;									//Allows for reading of textfiles.
import java.util.ArrayList;									//Allows for usage of ArrayList objects.
import java.util.Random;									//Allows for generation of random numbers.

/**
 * Used for generating Items.
 * 
 * @author Jonathan Sohrabi 2018
 */
public class ItemGenerator {
	/**
	 * Used to store templates of different Items.
	 */
	private ArrayList<Item> itemList;
	
	/**
	 * Default constructor of the ItemGenerator class. Reads in Item information from ItemList.txt and adds them to an ArrayList of Item templates.
	 */
	public ItemGenerator() {
		itemList = new ArrayList<Item>();					//Creates a new ArrayList object for Item objects.
		final String file = "./textfiles/ItemList.txt";		//Filepath of the ItemList.txt file.
		Scanner read = null;								//Creates a null Scanner object.
		try {
			read = new Scanner( new File( file ) );			//Allows for reading of ItemList.txt file.
			String line;									
			
			//Loop at least once while there are unread lines in the textfile.
			do {
				line = read.nextLine();																	//Read in the next line of the file.
				String itemParams[] = line.split( "," );												//Split the line at each comma and set each value to an index of itemParams String array.
				itemList.add( new Item( itemParams[ 0 ], Integer.parseInt( itemParams[ 1 ] ) ) );		//Adds new indices to the itemList ArrayList for each different Item object.
			} while ( read.hasNext() );
			read.close();																				//Close the Scanner object.
		//If there was an error with file reading, print an error to the console and end the program.
		} catch ( IOException e ) {
			System.out.println( "An error occured while loading the item list. The program will now exit." );
			System.exit( 0 );
		}
	}
	
	/**
	 * Generates a random Item from the ArrayList of Item templates.
	 * 
	 * @return					A new instance of the generated Item.
	 */
	public Item generateItem() {
		Random random = new Random();																	//Creates a new Random object.
		int rng = random.nextInt( itemList.size() );													//Gets a random index of the itemList ArrayList.
		return new Item( itemList.get( rng ).getName(), itemList.get( rng ).getValue() );				//Returns a new Item object with the values of the randomly chosen Item.
	}
	
	/**
	 * Generates a Health Potion from the ArrayList of Item templates.
	 * 
	 * @return					A new instance of a Health Potion Item.
	 */
	public Item getPotion() {
		for ( Item i: itemList ) {						//Loop through each Item in the ArrayList.
			if ( i.getName() == "Health Potion" ) {		//If the Item name is "Health Potion" return it.
				return i;
			}
		}
		return null;									//Otherwise, return nothing.
	}
}
