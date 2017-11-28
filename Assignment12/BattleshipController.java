/*
 * BattleshipController.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */
import java.io.InputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;

/**
 * This class starts and maintains the controller in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipController {

    BattleshipModel model = null;
    int count = 0;
    int attackerId;

    String winnerName = "";

    public BattleshipController(BattleshipModel model,
            PrintWriter[] clientOutputStreams, InputStream[] streams) {
        this.model = model;
        model.initialize();

    }

    public BattleshipController(BattleshipModel model) {
        this.attackerId = 0;
        this.model = model;
        model.initialize();
    }

    /**
     * This method is used to create the fleet for this player.
     * 
     * @param id
     *            id of player
     */
    public FleetInfo createFleet(int i) {
        return model.createFleet(i);
    }

    public boolean playGame(int playerId) {
        if (playerId == attackerId) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to d if given coordinates overlap or touch the
     * current ship. For this it delegates calls to other ship objects for
     * check.
     * 
     * @param id
     *            id of player
     * @param x1
     *            x-coordinate of starting point
     * @param y1
     *            y-coordinate of starting point
     * @param x2
     *            x-coordinate of end point
     * @param y2
     *            y-coordinate of end point
     * @return boolean True if overlap or touch else false.
     */
    public boolean checkOverlap(int id, int x1, int y1, int x2, int y2) {

        return model.checkOverlap(id, x1, y1, x2, y2);
    }

    /**
     * This method is used to create the fleet for this player.
     * 
     * @param id
     *            id of player
     * @param x1
     *            x-coordinate of starting point
     * @param y1
     *            y-coordinate of starting point
     * @param x2
     *            x-coordinate of end point
     * @param y2
     *            y-coordinate of end point
     */
    public void arrange(int id, int i, int x1, int y1, int x2, int y2) {
        model.arrange(id, i, x1, y1, x2, y2);
    }

    /**
     * This method is used to determine if any ship of the player is hit.
     * 
     * @param id
     *            id of player
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     * @return int Uses TheGame constants for miss, hit, already marked, sunk,
     *         game over.
     */
    public int reportStatusOfAttack(int id, int x, int y) {
        int oppoId = (id == 0) ? 1 : 0;

        // get opponent's id and get status from opponent
        int result = model.reportStatusOfAttack(oppoId, x, y);
        if (result == BattleshipGame.MISS) {
            attackerId = oppoId;
        }

        return result;
    }

    /**
     * This method is used to determine if all ships sunk.
     * 
     * @param id
     *            id of player
     * @return boolean True if all sunk else false.
     */
    public boolean isAllShipsSunk(int id) {

        return model.isAllShipsSunk(id);
    }

    /**
     * This method is used to return name of opponent.
     * 
     * @param String
     *            name of attacker player
     */
    public String getOpponentName(String name) throws RemoteException {
        return model.getOpponentName(name);
    }

    /**
     * This method is used to report status of attack on opponent.
     * 
     * @param id
     *            id of player
     * @param int
     *            current request id
     * @return boolean True if all sunk else false.
     */
    public int reportStatusOfAttackOnMe(int playerId, int countOfMeAsOppo)
            throws RemoteException {
        return model.reportStatusOfAttackOnMe(playerId, countOfMeAsOppo);

    }

    /**
     * This method is used to return name of winner.
     * 
     * @return String name of winner
     */
    public String getWinnerName() throws RemoteException {
        this.winnerName = (attackerId == 0) ? "PlayerA" : "PlayerB";
        return this.winnerName;
    }

}
