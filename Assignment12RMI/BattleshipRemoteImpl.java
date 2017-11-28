package Assignment12RMI;/*
 * BattleshipRemoteImpl.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class is remote implementation in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipRemoteImpl extends UnicastRemoteObject implements BattleshipRemote{

     
    int count = 0;
     BattleshipController controller = null;
    
    public BattleshipRemoteImpl(BattleshipController controller) throws RemoteException {
       this.controller = controller; 
    }

    public String connect() throws RemoteException {
        String rules = "Please understand the following rules of the game:-\n"
                + "1. First step in the game is to arrange your fleet on your grid.\n"
                + "2. After your grid is initialized, you should wait."
                + "The game begins automatically once the second player has also initialized his grid.\n"
                + "3. You should input in one line and only when you are asked to enter. Otherwise, wait for your turn.\n"
                + "YOUR NAME IS Player" + (count == 0 ? "A" : "B")
                + ".";
        count++;
        return rules;
    }

    public boolean checkOverlap(int id, int x1, int y1, int x2, int y2)
            throws RemoteException {
        
        return controller.checkOverlap(id, x1, y1, x2, y2);
    }

    public void arrange(int id, int i, int x1, int y1, int x2, int y2)
            throws RemoteException {
        controller.arrange(id, i, x1, y1, x2, y2);
    }

    public FleetInfo createFleet(int i) {
        return controller.createFleet(i);
    }

    public boolean playGame(int playerId) {
        return controller.playGame(playerId);
    }

    
    public int reportStatusOfAttack(int id, int i, int j) throws RemoteException {
        return controller.reportStatusOfAttack(id, i, j);
    }

    public boolean isAllShipsSunk(int playerId) throws RemoteException {
        return controller.isAllShipsSunk(playerId);
    }

    public String getOpponentName(String name) throws RemoteException {
        return controller.getOpponentName(name);
    }

    
    public int reportStatusOfAttackOnMe(int playerId, int countOfMeAsOppo)
            throws RemoteException {
        return controller.reportStatusOfAttackOnMe(playerId, countOfMeAsOppo);
        
    }

    public String getWinnerName() throws RemoteException {
        return controller.getWinnerName();
    }

}
