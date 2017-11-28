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
import java.net.*;
import java.io.*;

/**
 * This class starts and maintains the server in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipServer {

    PrintWriter[] clientOutputStreams = null;
    InputStream[] streams = null;
    static int port = 5050;
    public static void main(String...strings) {
        new BattleshipServer().go();
        for(int i =0; i < strings.length; i++ ) {
             if(strings[i].equals("-port")) {
                port = new Integer(strings[++i]);
            }
        }
    }

    /**
     * This method is used to connect client to server.
     * 
     */
    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = null;
            clientOutputStreams = new PrintWriter[2];
            streams = new InputStream[2];
            int count = 0;
            while (true) {
                clientSocket = serverSocket.accept();
                clientOutputStreams[count] = new PrintWriter(clientSocket
                        .getOutputStream());
                streams[count] = clientSocket.getInputStream();
               
                clientOutputStreams[count].println("Please understand the following rules of the game:- \n");
                clientOutputStreams[count].println("1. First step in the game is to arrange your fleet on your grid.");
                clientOutputStreams[count].println("2. After your grid is initialized, you should wait. "
                        + "The game begins automatically once the second player has also initialized his grid.");
                clientOutputStreams[count].println("3. You should input in one line and only when you are asked to enter. Otherwise, wait for your turn.\n\n");
                clientOutputStreams[count].flush();
                // any time receive two clients, start them in a game
                count++;

                if (count == 2) {
                    // start a game
                    BattleshipModel model = new BattleshipModel();
                    BattleshipController controller = new BattleshipController(
                            model, clientOutputStreams, streams);
                    count = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
