/*
 * BattleshipPlayerThread.java
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
 * This class starts the thread to arrange players' fleets in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipPlayerThread extends Thread {
    BattleshipPlayer player;

    public BattleshipPlayerThread(BattleshipPlayer player) {
        this.player = player;
    }

    /**
     * This method is used to arrange the fleet.
     * 
     */
    public void run() {
        player.arrangeTheFleet();

    }

    
}
