
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

import java.io.IOException;
import java.net.*;
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

    // Constants for protocol
    public static final String REQ_INIT = "REQ_INIT";
    public static final String REQ_GRID = "REQ_GRID";
    public static final String REQ_NXT_IP = "REQ_NEXT_INPUT";
    public static final String END_REQ_NXT_IP = "END_REQ_NXT_IP";
    public static final String END_REQ_NXT_IP_OK = "END_REQ_NXT_IP_OK";

    // Constants for protocol
    public static final String RES_GRID = "RES_GRID";
    public static final String RES_NXT_IP = "RES_NEXT_INPUT";

    // grids in the current game
    Ocean aOcean;
    Ocean bOcean;

    // players in the current game
    BattleshipPlayer playerA;
    BattleshipPlayer playerB;

    BattleshipController controller;
    BattleshipModel model;
    BattleshipPlayerThread[] threads;
    DatagramSocket sock;

    public BattleshipGame(BattleshipController controller,
            BattleshipModel model, DatagramSocket sock) {
        this.controller = controller;
        this.model = model;
        this.sock = sock;
        // arrange fleets in each player's thread and then start game
        threads = new BattleshipPlayerThread[2];
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

        int countA = 0, countB = 0;
        int res_countA = 0, res_countB = 0;
        int port = 0;
        String res = " ";
        String req = " ";
        byte[] buf2 = null;
        DatagramPacket packet2 = null;
        String opp_res = " ";
        // continues game unless all the ships of one player are sunk
        while (result != BattleshipGame.OVER) {
            while(true) {
                opp_res = "";
            buf2 = new byte[256];
            packet2 = new DatagramPacket(buf2, buf2.length);
            System.out.println(attacker.getName()
                    + " waiting for REQ_nxt_IP" + countA);
            buf2 = new byte[512];
            packet2 = new DatagramPacket(buf2, buf2.length);
            try {
                sock.receive(packet2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            req = new String(packet2.getData());
            System.out.println("recvd:- " + req);
            if (req.contains(attacker.getName()) && req.contains(
                    BattleshipGame.REQ_NXT_IP + countA)) {
                // received a valid request from current player so send him
                // his grid
                System.out.println("recvd  req_ip" + attacker.getName());
                countA++;
                break;
            }
            }
            port = packet2.getPort();
            InetAddress addr= packet2.getAddress();
            
            // Attacker keeps attacking unless it gets a miss.
            do {
                // waits for req_nxt_ip_attacker_# from attacker and if it
                // matches, sends response_#
                res +=  attacker.getName()+" RES: "+BattleshipGame.RES_NXT_IP+res_countA+".";
                res += opp_res;
                res += attacker.getName() + "\'s turn.\n";
                res += attacker.getName()
                        + "... enter coordinates x, y of attack: \n";
                res_countA++;
                byte[] buf = new byte[1024];
                buf = res.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length,
                        addr, port);


                while (true) {
                    try {
                        // till request for next input not received keep sending
                        // this packet RES_GRID

                        sock.send(packet);
                        try {
                            Thread.currentThread().sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        System.out.println(attacker.getName()+" waiting for REQ_nxt_IP"+countA);
                        buf2 = new byte[512];
                        packet2 = new DatagramPacket(buf2, buf2.length);
                        sock.receive(packet2);
                        req = new String(packet2.getData());
                        System.out.println("recvd:- "+req);
                        if(req.contains(attacker.getName()) && req.contains(BattleshipGame.REQ_NXT_IP+countA)){
                            //received a valid request from current player so send him his grid
                            System.out.println("recvd  req_ip"+attacker.getName());
                            countA++;
                            break;
                        }
                        
                        //from the req_next_input, extract coords
                        String pktData2 = new String(packet2.getData());
                        pktData2 = pktData2.substring(pktData2.indexOf(".")+1);
                        System.out.println(pktData2);
                        int s1 = pktData2.indexOf(" ");
                        int x1 = new Integer(pktData2.substring(0, s1));
                        int s2 = pktData2.indexOf(".",s1+1);
                        int y1 = new Integer(pktData2.substring(s1+1, s2));
                       
                        System.out.println("recvd: "+x1+" "+y1);
                        coords[0] = x1;
                        coords[1] = y1;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                
                result = opponent.reportStatusOfAttack(coords[0], coords[1]);
                opp_res += opponent.getName()+"says...\n";
                if(result == BattleshipGame.ALREADY_MARKED){
                    opp_res += 
                    
                                "You already tried here. Try again with new coordinates!\n";
                }else if (result == BattleshipGame.MISS){
                    opp_res += 
                            
                            "It\'s a miss!\n";
                }else if (result == BattleshipGame.HIT){
                    opp_res += 
                            
                            "My ship has been hit!\n";
                }else if (result ==  BattleshipGame.OVER || result ==  BattleshipGame.SUNK){
                    opp_res += 
                            
                            "My ship has been sunk!\n";
                }
               
            } while (result != BattleshipGame.OVER
                    && result != BattleshipGame.MISS);

            // check if all the ships are sunk, then game over is displayed
            if (result == BattleshipGame.OVER) {
                opp_res += attacker.getName() + " won! Game Over!!\n";
                opp_res += BattleshipGame.END_REQ_NXT_IP+".";
                byte[] buf =opp_res.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length,addr,port);
                
                        try {
                            //till request for next input not received keep sending this packet RES_GRID

                            sock.send(packet);
                            
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    
            } else {

                // If game is not over, switch roles between players.
                temp = attacker;
                attacker = opponent;
                opponent = temp;
                int tmp = countA;
                countA = countB;
                countB = tmp;
                
                tmp = res_countA;
                res_countA = res_countB;
                res_countB = tmp;
            }

        }

    }

    /**
     * This method is used to create grids in the current game.
     * 
     */
    public void createGrid() {
        aOcean = new Ocean(minRow, minColumn, maxRow, maxColumn);
        aOcean.initializeGrid();

        bOcean = new Ocean(minRow, minColumn, maxRow, maxColumn);
        bOcean.initializeGrid();
    }

    /**
     * This method is used to create players in the current game.
     * 
     */
    public void createPlayers() {

        playerA = new BattleshipPlayer(aOcean, "PlayerA", 0, this.controller,
                sock);
        playerB = new BattleshipPlayer(bOcean, "PlayerB", 1, this.controller,
                sock);

        playerA.createTheFleet();
        playerB.createTheFleet();
    }

    /**
     * This method is used to create view of the game.
     * 
     */
    public void createView() {
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
