/*
 * BattleshipRemote.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */
import java.rmi.*;

/**
 * This interface is remote interface in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public interface BattleshipRemote extends Remote {
    public String connect() throws RemoteException;
    public boolean checkOverlap(int id, int x1, int y1, int x2, int y2) throws RemoteException;
    public void arrange(int id, int i, int x1, int y1, int x2, int y2) throws RemoteException;
    public void createFleet(int i) throws RemoteException;
    public int getPlayerId() throws RemoteException;
    public boolean playGame(int playerId) throws RemoteException;
    public int reportStatusOfAttack(int id, int i, int j) throws RemoteException;
    public boolean isAllShipsSunk(int playerId) throws RemoteException;
    public String getOpponentName(int playerId) throws RemoteException;
    public int reportStatusOfAttackOnMe(int playerId, int countOfMeAsOppo) throws RemoteException;
    public String getWinnerName() throws RemoteException;
    public void setPlayerName(int id, String name) throws RemoteException;
    public void setAttackerId(int id) throws RemoteException;
}// BattleshipRemote
