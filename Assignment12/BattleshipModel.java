import java.rmi.RemoteException;

public class BattleshipModel {

    // Constants used to report ship hit, miss, sunk conditions
    // and over when all ships of a player are sunk.
    public static final int FLEET = 1;

    private Fleet[] fleets;
    private int countOfMeAsOppo = 0;
    private int attackStatus = -1;

    /**
     * This method is used to initialize model.
     * 
     * @param id
     *            id of player
     */
    public void initialize() {

        // as soon as players ready, notify view for players' inputs
        fleets = new Fleet[2];

    }

    /**
     * This method is used to create the fleet for this player.
     * 
     * @param id
     *            id of player
     */

    public FleetInfo createFleet(int i) {
        fleets[i] = new Fleet();
        return fleets[i].getFleetInfo();
    }

    /**
     * This method is used to d if given coordinates overlap or touch the
     * current ship. For this it delegates calls to other ship objects for
     * check.
     * 
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

        boolean status = fleets[id].checkOverlap(x1, y1, x2, y2);
        return status;

    }

    /**
     * This method is used to arrange i'th ship in the ocean.
     * 
     * @param i
     *            i'th ship in the fleet
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
        fleets[id].arrange(i, x1, y1, x2, y2);

    }

    /**
     * This method is used to determine if any ship is hit.
     * 
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     */
    public int reportStatusOfAttack(int id, int x, int y) {
        int result = fleets[id].reportStatusOfAttack(x, y);
        this.attackStatus = result;
        this.countOfMeAsOppo++;

        return result;
    }

    /**
     * This method is used to determine if all ships sunk.
     * 
     * @return boolean True if all sunk else false.
     */
    public boolean isAllShipsSunk(int id) {

        return fleets[id].isAllShipsSunk();
    }

    /**
     * This method is used to return name of opponent.
     * 
     * @param String
     *            name of attacker player
     */
    public String getOpponentName(String name) throws RemoteException {
        return name.contains("A") ? "PlayerB" : "PlayerA";
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
        while (countOfMeAsOppo != this.countOfMeAsOppo) {
            System.out.println("countOfMeAsOppo request id: "+countOfMeAsOppo+" received from : "+playerId );
        }

        if (this.attackStatus == BattleshipGame.MISS) {// set for next player
                                                       // who will be new
                                                       // opponent
            this.countOfMeAsOppo = 0;
        }

        return this.attackStatus;

    }

}
