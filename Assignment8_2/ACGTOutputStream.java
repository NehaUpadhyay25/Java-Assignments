import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**This class is used to compress the file.
 *  
 * @author Kritka  Sahni
 * @author Neha Upadhyay
 *
 */

public class ACGTOutputStream extends FilterOutputStream {
    
    int count = 0;
    byte[] res;
    
    public ACGTOutputStream ( OutputStream out ) {
        super(out);
        res = new byte[2];
    }
    
    /**
     * This method writes the compressed data in the file.
     * 
     * @param  content  content of the file
     */
    public void write( int content ) throws IOException {
    	
        byte t = 0;   
        
        switch( content ) {
        	case 'a': t = 0;
            	  	  break;
             case 'c': t = 1;
                      break;            
            case 'g': t = 2;
                      break;
            case 't': t = 3;
                      break;         
        }
                        																						
        res[1] = (byte)( t << count | res[1] );
        count += 2;
                
        if( count == 8 ) {
        	res[0] = (byte)(count/2);
                    
            super.write(res[0]);
            super.write(res[1]);
            
            count = 0;
            res[0]=0;
            res[1]=0;
        }      
    }
    
    /**
     * This method writes the compressed data in the file.
     */
    public void flush() throws IOException { 
    	
    	res[0] = (byte)(count/2);
                       
        super.write(res[0]);
        super.write(res[1]);
        super.flush();
        count = 0;
        res[0]=0;
        res[1]=0;
    }
    
    /**
     * This is the test method.
     * 
     * @param file	filename
     */
    public static void test ( String file ) {
        int content = 0;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            dis = new DataInputStream( 
                    new FileInputStream( file ) );
            
            dos = new DataOutputStream( new ACGTOutputStream(
                    new FileOutputStream( "fileC.txt" ) ) );          
       
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
    
    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments 
     */
    public static void main( String... args ) {
        ACGTOutputStream.test(args[0]);
    }
}

