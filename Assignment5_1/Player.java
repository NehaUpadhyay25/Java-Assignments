/*
 * Player.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */


/**
* This abstract class represents a Player in a game.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public abstract class Player implements Attackable{

    // Name of the player.
    String name;
    
    // Grid of the player
    Grid grid;
    
    /**
     * This method is used to return name of the player.
     * 
     * @return String Returns name.
     */
    public String getName() {
        return this.name;
    }
    
} // Player
