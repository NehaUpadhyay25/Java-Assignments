import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
* This class used does the Compression and Decompression of file.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/


public class CompressCustom {
    
	/**
     * This method is the main method.
     * 
     * @param    args    command line arguments 
     */
    public static void main( String... args ) {
        String file = args[0];
        int content = 0;
        
        String fileSub = file.substring( 0, file.indexOf(".") );
        
        if ( fileSub.endsWith("C") ) {
            try {
                
                DataInputStream dis = new DataInputStream( new ACGTInputStream( 
                       new FileInputStream(file)));
               
                OutputStreamWriter ow = new OutputStreamWriter( 
                  new DataOutputStream( new FileOutputStream("fileUC.txt") ) );
                
                while( ( content = dis.read() ) != -1 ){
                    ow.write(content);
                    ow.flush();
                }
               
               
           } catch ( FileNotFoundException e){
               e.printStackTrace();         
           } catch ( IOException e){
               e.printStackTrace();         
           }
            
        } else {
            try{
                DataInputStream dis = new DataInputStream( 
                        new FileInputStream(file));
                
                DataOutputStream dos = new DataOutputStream( new ACGTOutputStream(
                        new FileOutputStream("fileC.txt")));
                
                while( ( content = dis.read() ) != -1 ) {
                    
                     switch ( content ) {
                         case 'a' :
                         case 'c' :
                         case 'g' :
                         case 't' :
                             dos.write(content) ;
                             break;
                     }
                }
                dos.flush();
                
            } catch ( FileNotFoundException e){
                e.printStackTrace();         
            } catch ( IOException e){
                e.printStackTrace();         
            }
        }
    }
}
