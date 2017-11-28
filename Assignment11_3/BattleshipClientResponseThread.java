import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BattleshipClientResponseThread extends Thread {

    String resExp = "";
    boolean recvd = false;
    DatagramSocket sock;
    String name = "";
    String response = "";
  
    public BattleshipClientResponseThread(String res, String name, DatagramSocket sock ) {
        resExp = res;
        this.name = name;
        this.sock = sock;
    }
    
    public void run() {
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        String pktData = null;
        try {
            do {
                buf = new byte[1024];
                dp = new DatagramPacket(buf, buf.length);
                sock.receive(dp);
                pktData = new String(dp.getData());
            }while(!(pktData.contains(this.name) && pktData.contains(
                    this.resExp)));
            recvd = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //extract header and show response from server
        pktData = pktData.substring(pktData.indexOf("."));
        System.out.println(pktData);
        response = pktData;
        
    }
    
    public boolean isResponseReceived() {
        return this.recvd;
    }
    
    public String getResponse(){
        return this.response;
    }
    
    
}
