import java.awt.Point;						//Allows for usage of Point objects.
import java.io.*;							//Allows for usage of files.
import java.util.Scanner;					//Allows for reading of text files.

/**
 * Used as the mapping system of the dungeon floors.
 * 
 * @author Jonathan Sohrabi 2018
 */
public class Map {
	/**
	 * Holds information for each tile on the map.
	 */
	private char[][] map;
	/**
	 * Holds information on whether each tile has been revealed.
	 */
	private boolean[][] revealed;
	
	/**
	 * Default constructor for the Map class. Creates new arrays for map tiles and discovered rooms.
	 */
	public Map() {
		map = new char[ 5 ][ 5 ];				//Instantiate map to a 5x5 character array.
		revealed = new boolean[ 5 ][ 5 ];		//Instantiate revealed to a 5x5 boolean array.
	}
	
	/**
	 * Reads in Map tiles from "MapX.txt" textfiles (where X is the map number) and parses them into an array.
	 * 
	 * @param mapNum				The number of the map to load.
	 */
	public void loadMap( int mapNum ) {
		Scanner read = null;										//Create null Scanner object.
		final String file = "./textfiles/Map" + mapNum + ".txt";	//Filepath of each map.
		try {
			read = new Scanner( new File( file ) );					//Allows for reading of the map textfile.
			String line;
			int i = 0;												//Keeps track of each "row" in the map.
			
			//Do while file has unread lines.
			do {
				line = read.nextLine();								//Read in the next line of the file.
				if ( line != null ) {								//If the line is not null:
					char[] mapVals = line.toCharArray();			//Create a new 1D character array to hold each character that was read.
					for ( int j = 0; j < map.length; j++ ) {		//Loop for each index of the map array.
						map[ i ][ j ] = mapVals[ j ];				//Sets map index value to the respective character in mapVals array.
						revealed[ i ][ j ] = false;					//Sets revealed index value to false.
					}
					i++;											//Increment to the next map "row".
				}
			} while ( read.hasNext() );
			read.close();											//Close the Scanner object.
		//If an error occured during file reading, print an error to the console and exit the program.
		} catch ( IOException e ) {
			System.out.println( "An error occured while loading the next map. The program will now exit." );
			System.exit( 0 );
		}
	}
	
	/**
	 * Gets the character value of the Map at the parameterized Point.
	 * 
	 * @param p						The Point of the map to get the character of.
	 * @return						The character of the map at the parameterized Point.
	 */
	public char getCharAtLoc( Point p ) {
		return map[ ( int )p.getX() ][ ( int )p.getY() ];			//Return the map character value at the index given by the Point parameter.
	}
	
	/**
	 * Displays the Map on the console, showing discovered Map tiles and hiding undiscovered tiles.
	 * 
	 * @param p						The Point location of the hero.
	 */
	public void displayMap( Point p ) {
		for ( int i = 0; i < map.length; i++ ) {					//Loop through each "row" of the map.
			for ( int j = 0; j < map[i].length; j++ ) {				//Loop through each "column" of the map.
				if ( ( p.getX() == i ) && ( p.getY() == j ) )		//If the Hero's location is at that index, print an asterisk to the console.
					System.out.print( "*" );
				else												//In other cases:
					if ( !revealed[ i ][ j ] )						//If the map index has not been revealed, print an 'x' to the console.
						System.out.print( "x" );
					else											//If the map index has been revealed, print character value at that map index.
						System.out.print( map[ i ][ j ]);
			}
			System.out.println();									//Write a new line to the console after a "row" has been looped through.
		}
	}
	
	/**
	 * Finds the Point on the Map where with an index value of 's'.
	 * 
	 * @return						The Point on the Map with an 's' index value.
	 */
	public Point findStart() {
		Point start = new Point();
		for ( int i = 0; i < map.length; i++ ) {
			for ( int j = 0; j < map[i].length; j++ ) {
				if ( map[ i ][ j ] == 's' )
					start.setLocation( i, j );
			}
		}
		return start;
	}
	
	/**
	 * Sets the Map index value to discovered at the location of the parameterized Point.
	 * 
	 * @param p						The Point on the Map that is to be marked as discovered.
	 */
	public void reveal( Point p ) {
		revealed[ ( int )p.getX() ][( int )p.getY() ] = true;				//Set the revealed array value to true at the given parameter Point p.
	}
	
	/**
	 * Sets the Map index value as 'n' at the location of the parameterized Point.
	 * 
	 * @param p						The Point where the Map index value is to be changed to an 'n'.
	 */
	public void removeCharAtLoc( Point p ) {
		map[ ( int )p.getX() ][ ( int )p.getY() ] = 'n';					//Replace the map array value at the given parameter Point p with the character 'n'.
	}
}
