import java.awt.Point;						//Allows for usage of Point objects.
import java.util.Random;					//Allows for generation of random numbers.

/**
 * The main class of the dungeonmaster game.
 * 
 * @author Jonathan Sohrabi 2018
 */
public class Main {
	/**
	 * The main method of the dungeonmaster program. Takes in non-combat commands from the player and performs the requested actions.
	 * @param args					The command-line arguments of the dungeonmaster game.
	 */
	public static void main( String[] args ) {
		String hName, hQuip;						//hName = Hero name, hQuip = Hero quip.
		EnemyGenerator eg = new EnemyGenerator();	//Create new EnemyGenerator object.
		boolean gameExit = false;					//Used to keep track of when the player is finished playing.
		Map map = new Map();						//Create a new Map object.
		int curMap = 1;								//Sets the starting Map number to 1.
		char tile;
		final String MOVEMENT = "1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit Game";			//Shows movement options to player.
		
		System.out.println( "What is your name, adventurer?" );
		hName = CheckInput.getString();				//Gets name of the Hero.
		System.out.println( "What is your battlecry, " + hName + "?" );
		hQuip = CheckInput.getString();				//Gets quip of the Hero.
		map.loadMap( curMap );						//Loads Map 1.
		Hero h = new Hero( hName, hQuip, map );		//Creates a new Hero object with the given parameters.
		map.reveal( h.getLocation() );
		map.displayMap( h.getLocation() );
		//Loop until gameExit is true.
		do {
			if ( map.getCharAtLoc( h.getLocation() ) == 'f' ) {			//If the Hero is standing on an 'f' tile:
				System.out.println( "You've found the next floor." );
				h.increaseLevel();										//Increase Hero level.
				h.increaseMaxHP( 10 );									//Add 10 to Hero maxHP.
				h.heal( 10 );											//Add 10 to Hero HP.
				if ( curMap == 3 ) {									//If the current Map is Map3, load Map1.
					curMap = 1;
					map.loadMap( 1 );
				} else {												//Load the next Map in order.
					curMap++;
					map.loadMap( curMap );
				}
				map.reveal( h.getLocation() );							//Set the Hero's location on the Map to revealed.
				map.displayMap( h.getLocation() );						//Display the Map to the console.
			}
			if ( map.getCharAtLoc( h.getLocation() ) == 'i' ) {			//If the Hero is standing on an 'i' tile, call itemRoom method.
				itemRoom( h, map );
			}
			while ( map.getCharAtLoc( h.getLocation() ) == 'm' ) {		//If the Hero is standing on an 'm' tile:
				gameExit = !monsterRoom( h, map, eg );					//Set gameExit to negation of monsterRoom method return.
			}
			System.out.println( MOVEMENT );
			switch ( CheckInput.getIntRange( 1, 5 ) ) {					//Gets input from player in range of [1,5]
				//If input from player is 1.
				case 1:
					tile = h.goNorth();									//Call goNorth() method for Hero class.
					if ( tile == 's' ){									//If tile is 's', call store method.
						store( h );
					}
					if ( tile == 'f' ) {								//If tile is 'f', load next Map.
						System.out.println( "You've found the next floor." );
						h.increaseLevel();								//Increase Hero level.
						h.increaseMaxHP( 10 );							//Increase Hero maxHP by 10.
						h.heal( 10 );									//Heal Hero HP by 10.
						if ( curMap == 3 ) {							//If current Map is 3, set next Map to 1 and load it.
							curMap = 1;
							map.loadMap( 1 );
						} else {										//Load the next Map in order.
							curMap++;
							map.loadMap( curMap );
						}
						map.reveal( h.getLocation() );					//Reveal Map at Hero location.
						map.displayMap( h.getLocation() );				//Display Map at Hero location.
					}
					if ( tile == 'i' ) {								//If Hero is on 'i' tile, call itemRoom method.
						itemRoom( h, map );
					}
					if ( tile == 'm' ) {								//If Hero is on 'm' tile:
						gameExit = !monsterRoom( h, map, eg );			//Set gameExit equal to negation of monsterRoom method return.
					}
					h.display();							//Display Hero details.
					map.reveal( h.getLocation() );			//Set the Map tile at the Hero's location to revealed.
					map.displayMap( h.getLocation() );		//Display the Map to the console.
					break;												//Break out of this switch statement.
				//If player input 2.
				case 2:
					tile = h.goSouth();									//Call goSouth() method for Hero.
					if ( tile == 's' ){									//If Hero standing on 's' tile, call store method.
						store( h );
					}
					if ( tile == 'f' ) {								//If Hero standing on 'f' tile:
						System.out.println( "You've found the next floor." );
						h.increaseLevel();								//Increase Hero level.
						h.increaseMaxHP( 10 );							//Increase Hero maxHP by 10.
						h.heal( 10 );									//Heal Hero HP by 10.
						if ( curMap == 3 ) {							//If current Map is 3, load Map 1.
							curMap = 1;
							map.loadMap( 1 );
						} else {										//Else load the next Map in order.
							curMap++;
							map.loadMap( curMap );
						}
						map.reveal( h.getLocation() );					//Reveal the Map at the Hero location.
						map.displayMap( h.getLocation() );				//Display the Map.
					}
					if ( tile == 'i' ) {								//If Hero standing on 'i' tile, call itemRoom method.
						itemRoom( h, map );
					}
					if ( tile == 'm' ) {								//If Hero standing on 'm' tile:
						gameExit = !monsterRoom( h, map, eg );			//Set gameExit to negation of monsterRoom method.
					}
					h.display();							//Display Hero details.
					map.reveal( h.getLocation() );			//Set the Map tile at the Hero's location to revealed.
					map.displayMap( h.getLocation() );		//Display the Map to the console.
					break;												//Break out of switch statement.
				//If player input is 3.
				case 3:
					tile = h.goEast();									//Call goEast method for Hero.
					if ( tile == 's' ){									//If Hero is standing on 's' tile, call store method.
						store( h );
					}
					if ( tile == 'f' ) {								//If Hero standing on 'f' tile:
						System.out.println( "You've found the next floor." );
						h.increaseLevel();								//Increase Hero level by 1.
						h.increaseMaxHP( 10 );							//Increase Hero maxHP by 1.
						h.heal( 10 );									//Heal Hero HP by 10.
						if ( curMap == 3 ) {							//If current Map is 3, load Map 1.
							curMap = 1;
							map.loadMap( 1 );
						} else {										//Else, load Map 1.
							curMap++;
							map.loadMap( curMap );
						}
						map.reveal( h.getLocation() );					//Reveal Map at Hero location.
						map.displayMap( h.getLocation() );				//Display Map to console.
					}
					if ( tile == 'i' ) {								//If Hero standing on 'i' tile, call itemRoom method.
						itemRoom( h, map );
					}
					if ( tile == 'm' ) {								//If Hero standing on 'm' tile:
						gameExit = !monsterRoom( h, map, eg );			//Set gameExit to negation of monsterRoom method.
					}
					h.display();							//Display Hero details.
					map.reveal( h.getLocation() );			//Set the Map tile at the Hero's location to revealed.
					map.displayMap( h.getLocation() );		//Display the Map to the console.
					break;												//Break out of switch statement.
				//If user input 4.
				case 4:
					tile = h.goWest();									//Call goWest method for Hero.
					if ( tile == 's' ){									//If Hero standing on 's' tile, call store method.
						store( h );
					}
					if ( tile == 'f' ) {								//If Hero standing on 'f' tile:
						System.out.println( "You've found the next floor." );
						h.increaseLevel();								//Increase Hero level.
						h.increaseMaxHP( 10 );							//Increase Hero maxHP by 10.
						h.heal( 10 );									//Heal Hero HP by 10.
						if ( curMap == 3 ) {							//If current Map is 3, load Map 1.
							curMap = 1;
							map.loadMap( 1 );
						} else {										//Else, load the next Map in order.
							curMap++;
							map.loadMap( curMap );
						}
						map.reveal( h.getLocation() );					//Reveal the Map at the Hero location.
						map.displayMap( h.getLocation() );				//Display the Map to the console.
					}
					if ( tile == 'i' ) {								//If Hero standing on 'i' tile, call itemRoom method..
						itemRoom( h, map );
					}
					if ( tile == 'm' ) {								//If Hero standing on 'm' tile:
						gameExit = !monsterRoom( h, map, eg );			//Set gameExit to negation of monsterRoom method.
					}
					h.display();							//Display Hero details.
					map.reveal( h.getLocation() );			//Set the Map tile at the Hero's location to revealed.
					map.displayMap( h.getLocation() );		//Display the Map to the console.
					break;												//Break out of this switch statememt.
				//If user input 5.
				case 5:
					gameExit = true;									//Set gameExit to true;
					break;												//Break out of this switch statement.
			}
		} while ( !gameExit );
		System.out.println( "Game Over" );
		System.exit( 0 );												//Exit the program.
	}
	
	/**
	 * Begins combat when the hero enters a room notated by the character 'm' and calculates the combat result.
	 * 
	 * @param h						The current Hero object.
	 * @param m						The current Map object.
	 * @param eg					The current EnemyGenerator object.
	 * @return						True if the Hero is alive at the end of combat, false otherwise.
	 */
	public static boolean monsterRoom( Hero h, Map m, EnemyGenerator eg ) {
		Entity e = eg.generateEnemy( h.getLevel() );			//Create a new Entity from generateEnemy method of eg parameter.
		Point heroLoc = h.getLocation();						//Get Point location of Hero and put it in heroLoc field.
		boolean fightResult;									//Store result of fight method.
		boolean heroAlive;										//Keeps track of if Hero is alive.
		boolean finalReturn = true;								//Used for what this method should return.

		m.reveal( h.getLocation() );
		m.displayMap( h.getLocation() );
		System.out.println( "You've encountered a " + e.getName() + ".");
		
		fightResult = fight(h, (Enemy)e );						//Call fight method and store return value to fightResult.
		
		if ( h.getHP() != 0 )									//If Hero has positive HP, they are alive
			heroAlive = true;
		else													//If Hero has negative HP, they are dead.
			heroAlive = false;
		
		if ( !fightResult && heroAlive ) {						//If Hero is alive and fightResult returned false, return true in this method.
			finalReturn = true;
		} else if ( fightResult && heroAlive ) {				//If Hero is alive and fightResult returned true, return true in this method.
			m.removeCharAtLoc( heroLoc );
			finalReturn = true;
		} else if ( !fightResult && !heroAlive ) {				//If Hero is dead and fightResult returned false, return false in this method.
			finalReturn = false;
		}
		
		return finalReturn;										//Return value of finalReturn.
	}
	
	/**
	 * Takes in combat commands from the player and performs the requested actions.
	 * 
	 * @param h						The current Hero object.
	 * @param e						The Enemy object fighting the Hero.
	 * @return						True if the Hero is alive at the end of combat, false if the Hero dies during combat..
	 */
	public static boolean fight( Hero h, Enemy e ) {
		boolean fightEnd = false;							//Track whether fighting is done.
		final String MENU1 = "1. Fight\n2. Run Away";
		final String DEAD = "Oh dear, you have died.";
		
		//Do while Hero still in combat.
		do {
			h.display();
			System.out.println();
			e.display();									//Display Enemy details.
			System.out.println( MENU1 );
			switch( CheckInput.getIntRange( 1,2 ) ) {		//Get int value from player in range of [1,2].
				//If player input 1.
				case 1:
					h.attack( e );							//Call attack method for Hero.
					if ( e.getHP() != 0 ) {					//If Enemy has positive HP:
						e.attack( h );						//Enemy attacks Hero back.
						if ( h.getHP() != 0 ) {				//If Hero survives Enemy's attacks, print quips to console and continue fighting.
							System.out.println( h.getName() + " yells out a battlecry. \"" + h.getQuip() + "\"!" );
							System.out.println( e.getName() + " cries back. \"" + e.getQuip() + "\"!" );
							fightEnd = false;
						} else {							//If Enemy attack kills Hero, write that the Hero died and return false;
							System.out.println( DEAD );
							fightEnd = true;
							return false;
						}
					} else {								//If Hero kills the Enemy:
						System.out.println( "You defeated the " + e.getName() + "!" );
						if ( h.pickUpItem( e.getItem() ) ) {					//If Hero was able to pick up the Item, tell the player what Item they picked up.
							System.out.println( "You recieved a " + e.getItem().getName() + " from its corpse." );
						} else {												//If Hero was not able to pick up the Item, tell the player their inventory is full.
							System.out.println( "The " + e.getName() + " dropped an item, but your inventory is full." );
						}
						fightEnd = true;				//Set fightEnd to true to end combat.
						return true;
					}
					break;								//Break out of this switch statement.
				//If user input 2, Hero runs away.
				case 2:
					Random rand = new Random();			//Create new Random object.
					boolean moved = false;				//Track if Hero has moved.
					
					//Do until Hero has ran away.
					do {
						switch( rand.nextInt( 4 ) + 1 ) {				//Get a random int in the range of [1,4].
							//If random int was 1:
							case 1:
								if ( h.getLocation().getX() != 0 ) {	//If the Hero is not at top of Map, move the Hero north.
									h.goNorth();
									moved = true;						//Set moved to true to break out of loop.
								} else {								//Else, set moved to false to try moving again.
									moved = false;
								}
								break;	 								//Break out of this switch statement.
							//If random int was 2:
							case 2:
								if ( h.getLocation().getX() != 4 ) {	//If Hero is not at bottom of Map, move Hero south.
									h.goSouth();
									moved = true;						//Set moved to true to break out of loop.
								} else {								//Else, set moved to false to try moving again.
									moved = false;
								}
								break;									//Break out of this switch statement.
							//If random int was 3:
							case 3:
								if ( h.getLocation().getY() != 0 ) {	//If Hero is not at left-most part of Map, move Hero west.
									h.goWest();
									moved = true;						//Set moved to true to break out of loop.
								} else {								//Else, set moved to false and try moving again.
									moved = false;
								}
								break;									//Break out of this switch statement.
							//If random int was 4:
							case 4:
								if ( h.getLocation().getY() != 4 ) {	//If Hero is not at right-most part of Map, move Hero east.
									h.goEast();
									moved = true;						//Set moved to true to break out of loop.
								} else {								//Else, set moved to false and try moving again.
									moved = false;
								}
								break;									//Break out of this switch statement.
						}
					} while ( !moved );
					fightEnd = true;									//Indicate end of combat.
					return false;
				}
		} while ( !fightEnd );
		return true;
	}
	
	/**
	 * Performs shop actions requested by the player when the Hero is on a Map tile notated by the character 's'.
	 * 
	 * @param h						The current Hero object.
	 */
	public static void store( Hero h ) {
		boolean shopLeave = false;				//Indicates if shop has been left.
		int itemIndex;
		
		System.out.println( "Hello, " + h.getName() + "." );
		//Do until player has left the shop.
		do {
			System.out.println( "1. Buy Potions" );
			System.out.println( "2. Sell Items" );
			System.out.println( "3. Exit" );
			switch ( CheckInput.getIntRange( 1, 3 ) ) {					//Get input from user in range of [1,3].
				//If user input 1:
				case 1:
					if ( h.getGold() >= 25 && h.getNumItems() < 5 ) {	//If Hero has at least 25 gold and at least one inventory space, spend 25 gold to get a Health Potion.
						h.spendGold( 25 );
						h.pickUpItem( new Item( "Health Potion", 25 ) );
						System.out.println( "Here's your potion, " + h.getName() + "." );
					} else if ( h.getNumItems() == 5 ) {				//If Hero has 5 full inventory, tell the player.
						System.out.println( "Your inventory is full." );
					} else if ( h.getGold() < 25 ){						//If Hero has less than 25 gold, tell the player.
						System.out.println( "You don't have enough gold to buy a potion." );
					}
					break;												//Break out of this switch statement.
				//If user input 2:
				case 2:
					boolean doneSelling = false;						//Indicates if Hero is done selling.
					Item soldItem;
					
					//Do until Hero is done selling.
					do {
						System.out.println( "Choose an item to sell:" );
						h.displayItems();								//Display Hero inventory.
						System.out.println( "0. Leave" );
						itemIndex = CheckInput.getIntRange( 0, h.getNumItems() ) - 1;		//Get input from user, subtract 1.
						if ( itemIndex != -1 ) {						//If user input something in range of [1,5], remove item at that index and sell it.
							soldItem = h.removeItem( itemIndex );
							h.collectGold( soldItem.getValue() );
							System.out.println( "You sold the " + soldItem.getName() + " for " + soldItem.getValue() + " gold." );
						} else if ( itemIndex == -1 ) {					//If user input 0, indicate Hero is done seling
							doneSelling = true;
						}
					} while ( !doneSelling );
					break;												//Break out of this switch statement.
				//If user input 3, exit shop.
				case 3:
					shopLeave = true;
					break;
			}
		} while ( !shopLeave );
	}
	
	/**
	 * Adds an Item to the Hero's inventory when they enter a tile notated by the character 'i', provided the Hero is able to pick up the Item.
	 * 
	 * @param h						The current Hero object.
	 * @param m						The current Map object.
	 */
	public static void itemRoom( Hero h, Map m ) {
		ItemGenerator ig;
		if ( h.getNumItems() != 5 ) {						//If Hero has inventory space:
			ig = new ItemGenerator();						//Create new ItemGenerator object.
			Item i = ig.generateItem();						//Generate a random Item.
			h.pickUpItem( i );								//Hero picks up Item.
			System.out.println( "You picked up a " + i.getName() + "." );
			m.removeCharAtLoc( h.getLocation() );			//Sets tile at Hero location to 'n'.
		} else {											//If the user has 5 items, they do not pick anything up and map tile stays the same.
			System.out.println( "There is an item here, but your inventory is full." );
		}
	}
}