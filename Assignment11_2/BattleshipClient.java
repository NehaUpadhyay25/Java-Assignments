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
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class starts and maintains the client in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipClient {

    Socket sock = null;
    BufferedReader reader;
    PrintWriter writer;
    Scanner sc;
    static String ip = "127.0.0.1";
    static int port = 5050;
    
    static Object o = new Object();

    public static void main(String... strings) {
        new BattleshipClient().go();
        for(int i =0; i < strings.length; i++ ) {
            if(strings[i].equals("-host")) {
                ip = strings[++i];
            }else if(strings[i].equals("-port")) {
                port = new Integer(strings[++i]);
            }
        }
    }

    /**
     * This method is used to connect client to server.
     * 
     */
    public void setupNetworking() {
        try {
            sock = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(sock
                    .getInputStream()));
            writer = new PrintWriter(sock.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to connect client to server and also create client i/o thread.
     * 
     */
    public void go() {
        setupNetworking();
        new BattleshipClientReaderThread(reader, writer).start();

    }
}
