
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Scanner;

/**
 * This class starts and maintains the game in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipGame {

    // Minimum and maximum coordinates of the ocean as specified in the game.
    private static final int minRow = 0;
    private static final int minColumn = 0;
    private static final int maxRow = 9;
    private static final int maxColumn = 9;
    private int count = 0;

    // Constants used to report ship hit, miss, sunk conditions
    // and over when all ships of a player are sunk.
    public static final int MISS = 0;
    public static final int HIT = 1;
    public static final int SUNK = 2;
    public static final int OVER = 3;
    public static final int ALREADY_MARKED = 4;

    // grids in the current game
    Ocean aOcean;
    Ocean bOcean;

    // players in the current game
    BattleshipPlayer playerA;
    BattleshipPlayer playerB;

    BattleshipController controller;
    BattleshipModel model;

    PrintWriter[] clientOutputStreams;
    BufferedReader[] readers = null;
    BattleshipPlayerThread[] threads;
    InputStream[] streams;

    public BattleshipGame(BattleshipController controller,
            BattleshipModel model, PrintWriter[] clientOutputStreams,
            InputStream[] streams) {
        this.controller = controller;
        this.model = model;
        this.clientOutputStreams = clientOutputStreams;
        this.streams = streams;
        // arrange fleets in each player's thread and then start game
        threads = new BattleshipPlayerThread[2];
    }

    public BattleshipGame(BattleshipController controller,
            BattleshipModel model) {
        this.controller = controller;
        this.model = model;

    }

    /**
     * This method is used to play the Battleship game between two players.
     * 
     * @param attacker
     *            initial attacker is player A
     * @param attacker
     *            initial opponent is player B
     */
    public void playGame(BattleshipPlayer attacker, BattleshipPlayer opponent) {
        BattleshipPlayer temp = null;
        int result = 1;
        Scanner sc = null;
        int k = 0;
        int coords[] = new int[2];

        // continues game unless all the ships of one player are sunk
        while (result != BattleshipGame.OVER) {
            PrintWriter attackerOutputStream = attacker.getOutputStream();
            PrintWriter opponentOutputStream = opponent.getOutputStream();
            InputStream attackerInputStream = attacker.getInputStream();
            opponentOutputStream.println(opponent.getName()
                    + "... Wait for your turn...");
            opponentOutputStream.flush();
            // Attacker keeps attacking unless it gets a miss.
            do {

                attackerOutputStream.println(attacker.getName() + "\'s turn.");
                attackerOutputStream.println(attacker.getName()
                        + "... enter coordinates x, y of attack. Enter now:- ");
                attackerOutputStream.flush();

                sc = new Scanner(attackerInputStream);

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

                coords[k++] = new Integer(line.substring(begin, line.length()));

                // status needs to reported to attacker so send in attacker's
                // output stream
                result = opponent.reportStatusOfAttack(coords[0], coords[1],
                        attackerOutputStream);

            } while (result != BattleshipGame.OVER
                    && result != BattleshipGame.MISS);

            // check if all the ships are sunk, then game over is displayed
            if (result == BattleshipGame.OVER) {
                attackerOutputStream.println(attacker.getName()
                        + " won! Game Over!!");
                attackerOutputStream.flush();

                opponentOutputStream.println(attacker.getName()
                        + " won! Game Over!!");
                opponentOutputStream.flush();
            } else {

                // If game is not over, switch roles between players.
                temp = attacker;
                attacker = opponent;
                opponent = temp;
            }

        }

    }

    /**
     * This method is used to create grids in the current game.
     * 
     */
    public void createGrid() {
        aOcean = new Ocean(minRow, minColumn, maxRow, maxColumn,
                this.clientOutputStreams[0]);
        aOcean.initializeGrid();

        bOcean = new Ocean(minRow, minColumn, maxRow, maxColumn,
                this.clientOutputStreams[1]);
        bOcean.initializeGrid();
    }

    /**
     * This method is used to create players in the current game.
     * 
     */
    public void createPlayers() {

        playerA = new BattleshipPlayer(aOcean, "Player A", 0, this.controller,
                this.clientOutputStreams[0], this.streams[0]);
        playerB = new BattleshipPlayer(bOcean, "Player B", 1, this.controller,
                this.clientOutputStreams[1], this.streams[1]);

        playerA.createTheFleet();
        playerB.createTheFleet();
    }

    /**
     * This method is used to create view of the game.
     * 
     */
    public void createView() {

        // these 2 called in run now
        // create grids for the players
        this.createGrid();

        // create players
        this.createPlayers();

    }

    /**
     * This method is used to receive update from Model.
     * 
     */
    public void update(Observable observable, Object obj) {

        if (obj instanceof NotificationObject) {
            NotificationObject obj2 = ((NotificationObject) obj);
            int playerId = obj2.getPlayer();
            BattleshipPlayer player = playerId == 0 ? playerA : playerB;
            int status = obj2.getStatus();
            if (status == BattleshipModel.FLEET) {
                // get fleet info and hand it to the corresponding player
                FleetInfo info = (FleetInfo) obj2.getInfo();
                player.setFleetInfo(info);

                threads[playerId] = new BattleshipPlayerThread(player);

                threads[playerId].start();
                count++;

                if (count == 2) {
                    try {
                        for (int i = 0; i < 2; i++) {
                            threads[i].join();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Exception in geame thread after join on player threads");
                        e.printStackTrace();
                    }

                    // start game between the two players after their fleets
                    // arranged
                    this.playGame(this.playerA, this.playerB);

                }

            }

        }
    }

} // BattleshipGame
