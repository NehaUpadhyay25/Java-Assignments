import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class BattleshipUDPClient extends Thread {

    static int port = 6060;
    InetAddress addr;
    DatagramSocket sock;
    DatagramPacket packet;
    DatagramPacket dp;
    byte[] buf = new byte[1024];
    String name = "";
    Scanner sc = null;
    
    public static void main(String... strings) {

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("-port")) {
                port = new Integer(strings[++i]);
            }
        }

        new BattleshipUDPClient().start();
    }

    public void run() {

        String req = "REQ: " + BattleshipGame.REQ_INIT;
        try {
            addr = InetAddress.getLocalHost();
            sock = new DatagramSocket();
            buf = req.getBytes();
            packet = new DatagramPacket(buf, buf.length, addr, port);

            // 1. send empty packet to get rules
            sock.send(packet);

            // 1. receive rules
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            sock.receive(dp);
            String pktData = new String(dp.getData());
            //display rules
            System.out.println(pktData);
            
            int t = 0;
            if ((t = pktData.indexOf("Player")) != -1) {
                name = pktData.substring(t, pktData.indexOf(".", t));
            }

            BattleshipClientResponseThread res = null;
            
            // 2. receive grid response
            res = new BattleshipClientResponseThread(BattleshipGame.RES_GRID, this.name,sock);
            res.start();
            while (!res.isResponseReceived()) {
                // 2. send request for grid
                req = name + " REQ: " + BattleshipGame.REQ_GRID;
                buf = req.getBytes();
                packet = new DatagramPacket(buf, buf.length, addr, port);
                sock.send(packet);
                try{
                    sleep(10000);
                }catch(Exception e) {
                    e.printStackTrace();
                }
                System.out.println("sending request for grid");
            }
                    
            sc = new Scanner(System.in);
            //while response from server contains "enter now:-" and is res_next_ip , means client
            // enters coords which are sent to server
            int coords[] = new int[3];
            
            
            int count_nxt_ip = 0;
            int count_res_nxt_ip = 0;
            
            do{
                
            res = null;
            System.out.println("Now input:-");
            int k = 0;
            sc = new Scanner(System.in);
            sc.useDelimiter("\\s");
            while (sc.hasNextInt()) {
                coords[k++] = sc.nextInt();
            }
            
            // 3. receive next input response
            res = new BattleshipClientResponseThread(BattleshipGame.RES_NXT_IP+count_res_nxt_ip,
                    this.name, sock);
            res.start();
            while (!res.isResponseReceived()) {
                // 3. send request for further coords input opportunities
                req = name + " REQ: " + BattleshipGame.REQ_NXT_IP+count_nxt_ip+".";
                req += coords[0]+" "+coords[1]+" "+coords[2]+".";
                buf = req.getBytes();
                packet = new DatagramPacket(buf, buf.length, addr, port);
                sock.send(packet);
                try {
                    sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("sending request for next input # "+count_nxt_ip);
            }
            
            count_nxt_ip++;
            count_res_nxt_ip++;
            }while(!res.getResponse().contains(BattleshipGame.END_REQ_NXT_IP));
            
            //now once end input received, send request to start game.
            System.out.println("sending request for turn");


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        sc = new Scanner(System.in);
        //while response from server contains "enter now:-" and is res_next_ip , means client
        // enters coords which are sent to server
        int[] coords = new int[2];
        
        
        int count_nxt_ip = 0;
        int count_res_nxt_ip = 0;
        BattleshipClientResponseThread res = null;

        do{
            
        System.out.println("Now input:-");
        int k = 0;
        sc = new Scanner(System.in);
        sc.useDelimiter("\\s");
        while (sc.hasNextInt()) {
            coords[k++] = sc.nextInt();
        }
        
        // 3. receive next input response
        res = new BattleshipClientResponseThread(BattleshipGame.RES_NXT_IP+count_res_nxt_ip,
                this.name, sock);
        res.start();
        while (!res.isResponseReceived()) {
            // 3. send request for further coords input opportunities
            req = name + " REQ: " + BattleshipGame.REQ_NXT_IP+count_nxt_ip+".";
            req += coords[0]+" "+coords[1]+".";
            buf = req.getBytes();
            packet = new DatagramPacket(buf, buf.length, addr, port);
            try {
                sock.send(packet);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("sending request for next input # "+count_nxt_ip);
        }
        
        count_nxt_ip++;
        count_res_nxt_ip++;
        }while(!res.getResponse().contains(BattleshipGame.END_REQ_NXT_IP));
        
        //now once end input received, send request to start game.
        System.out.println("sending request for turn");


    }
}
