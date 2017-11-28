package Assignment12RMI;
/*
 * BattleshipClient.java
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
import java.util.Scanner;

/**
 * This class starts and maintains the client in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipClient {
    BattleshipPlayer player = null;
    BattleshipRemote service = null;

    /**
     * This method is the main method.
     * 
     * @param args
     *            command line arguments(ignored)
     */
    public static void main(String... strings) {
        new BattleshipClient().go();

    }

    /**
     * This method is used to connect client to server.
     * 
     */
    public void go() {
        int result = 1;
        Scanner sc = null;
        int k = 0;
        int coords[] = new int[2];

        Registry reg;
        String rules = "";
        try {
            reg = LocateRegistry.getRegistry(1078);
            service = (BattleshipRemote) reg.lookup(
                    "BattleshipRemoteImplObject");

            rules = service.connect();
            System.out.println(rules);

            // extract name and assign to player.
            int t = rules.indexOf("Player");
            String name = rules.substring(t, rules.indexOf(".", t));
            System.out.println("name" + name);
            player = new BattleshipPlayer(name.contains("A") ? 0 : 1, name,
                                          service);
            player.createTheFleet();
            boolean start = false;
            int countOfMeAsOppo = 1;
            do {

                start = service.playGame(player.getPlayerId());
                // as long as I am attacker
                if (start) {
                    countOfMeAsOppo = 1;// set this for next time when this
                                        // player becomes opponent
                    System.out.println(player.getName() + "\'s turn.");
                    System.out.println(player.getName()
                            + "... enter coordinates x, y of attack. Enter now:- ");

                    sc = new Scanner(System.in);

                    k = 0;

                    // we receive only 1 line from client
                    String line = sc.nextLine();
                    int begin = 0;
                    int end = line.indexOf(" ", begin);
                    while (end != -1) {
                        coords[k++] = new Integer(line.substring(begin, end));
                        begin = end + 1;
                        end = line.indexOf(" ", begin);
                    }

                    coords[k++] = new Integer(line.substring(begin, line
                            .length()));

                    int x = coords[0];
                    int y = coords[1];
                    String oppoName = service.getOpponentName(player.getName());
                    System.out.println(oppoName + " says... ");
                    int track = player.getTrackingGrid(x, y);

                    if (track != 0) {
                        switch (track) {

                        case BattleshipGame.MISS + 1:
                            System.out.println(
                                    "You already missed the spot. Try again with new coordinates!");
                            break;

                        case BattleshipGame.HIT + 1:
                            System.out.println(
                                    "You already hit here. Try again with new coordinates!");
                            break;

                        case BattleshipGame.SUNK + 1:
                            System.out.println(
                                    "You already hit here. Try again with new coordinates!");
                            break;

                        }
                        result = BattleshipGame.ALREADY_MARKED;

                    } else {
                        result = service.reportStatusOfAttack(player
                                .getPlayerId(), x, y);

                        switch (result) {

                        case BattleshipGame.MISS:
                            player.setTrackingGrid(x, y, BattleshipGame.MISS
                                                         + 1);
                            System.out.println("It\'s a miss!");
                            break;
                        case BattleshipGame.HIT:
                            player.setTrackingGrid(x, y, BattleshipGame.HIT
                                                         + 1);

                            System.out.println("My ship has been hit!");
                            break;
                        case BattleshipGame.SUNK:
                            player.setTrackingGrid(x, y, BattleshipGame.SUNK
                                                         + 1);
                            // this.clientOutputStream.println("Attacker has
                            // sunk your ship.");
                            System.out.println("My ship has been sunk!");
                            if (service.isAllShipsSunk(player.getPlayerId() == 0
                                    ? 1 : 0)) {
                                result = BattleshipGame.OVER;
                            }
                            break;

                        }
                    }
                } else {
                    // I am opponent
                    result = service.reportStatusOfAttackOnMe(player
                            .getPlayerId(), countOfMeAsOppo);
                    countOfMeAsOppo++;
                    switch (result) {

                    case BattleshipGame.MISS:

                        System.out.println("Attacker missed!");
                        break;
                    case BattleshipGame.HIT:

                        System.out.println("Attacker has hit your ship.");
                        break;
                    case BattleshipGame.SUNK:

                        System.out.println("Attacker has sunk your ship!");
                        if (service.isAllShipsSunk(player.getPlayerId())) {
                            result = BattleshipGame.OVER;
                        }
                        break;

                    }
                }

            } while (result != BattleshipGame.OVER);

            // check if all the ships are sunk, then game over is displayed
            if (result == BattleshipGame.OVER) {

                System.out.println(service.getWinnerName()
                        + " won! Game Over!!");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
