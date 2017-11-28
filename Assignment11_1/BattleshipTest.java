/*
 * BattleshipTest.java
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
 * This class used to test the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipTest {

    /**
     * This method is the main method.
     * 
     * @param args
     *            command line arguments(ignored)
     */
    public static void main(String... strings) {
        BattleshipModel model = new BattleshipModel();
        BattleshipController controller = new BattleshipController(model);
    }
}
