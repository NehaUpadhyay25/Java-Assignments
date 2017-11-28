/*
 * Gameable.java
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
* This interface represents ability to initialize and 
* play a grid based Game between two players.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public interface Gameable {

    void createGrid();
    void createPlayers();
    void playGame( Player attacker, Player opponent );
    
} // Gameable
