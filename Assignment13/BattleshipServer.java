package Assignment13;
/*
 * BattleshipServer.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * This class starts and maintains the server in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */

public class BattleshipServer {

    /**
     * This method is the main method.
     * 
     * @param args
     *            command line arguments(ignored)
     */
    public static void main(String args[]) {
        Registry reg;
        BattleshipModel model = new BattleshipModel();
        BattleshipController controller = new BattleshipController(model);

        try {
            reg = LocateRegistry.createRegistry(1078);
            BattleshipRemoteImpl obj = new BattleshipRemoteImpl(controller);
            reg.rebind("BattleshipRemoteImplObject", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
