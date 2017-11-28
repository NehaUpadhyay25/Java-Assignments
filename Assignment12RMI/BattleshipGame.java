package Assignment12RMI;
/*
 * BattleshipGame.java
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
 * This class starts and maintains the game in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipGame {

    // Minimum and maximum coordinates of the ocean as specified in the game.
    public static final int minRow = 0;
    public static final int minColumn = 0;
    public static final int maxRow = 9;
    public static final int maxColumn = 9;

    // Constants used to report ship hit, miss, sunk conditions
    // and over when all ships of a player are sunk.
    public static final int MISS = 0;
    public static final int HIT = 1;
    public static final int SUNK = 2;
    public static final int OVER = 3;
    public static final int ALREADY_MARKED = 4;

} // BattleshipGame
