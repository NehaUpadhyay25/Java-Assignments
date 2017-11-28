/*
 * BattleshipClientWriterThread.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class starts and maintains the server-i/o on the client side the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipClientWriterThread extends Thread {

    PrintWriter writer;
    Scanner sc;

    public BattleshipClientWriterThread(PrintWriter writer) {
        this.writer = writer;
        sc = new Scanner(System.in);
    }

    /**
     * This method is used to do i/o with server.
     * 
     */
    public void run() {

        try {

            writer.println(sc.nextLine());
            writer.flush();

        } catch (Exception e) {
            System.out.println("Error in writing to server.");
            e.printStackTrace();
        }

    }
}
