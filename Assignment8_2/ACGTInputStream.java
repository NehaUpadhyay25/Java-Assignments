package Assignment8_2;

import java.io.*;

/**This class is used to Decompress the compressed file.
 *  
 * @author Kritka  Sahni
 * @author Neha Upadhyay
 *
 */
public class ACGTInputStream extends FilterInputStream {
    
    int[] chars;
    int count;
    int it;
   
    public ACGTInputStream( InputStream in ) {
        super(in);
        count = 0;
        it = 0;
        chars = new int[4];
    }

    /**
     * This method reads the compressed file.
     * 
     */
    public int read() throws IOException {
        int content = 0, i = 0;
        byte res = 0;
        
        if( it == count ) {
            it = 0;
            count = (byte) super.read();
            
            if ( count == -1 ) {
                return -1;
            }
            
            res = (byte) super.read();
            
            for( i = 0; i < count ; i++ ) {
                content = ( 1 << ( 2 * i ) & res ) != 0 ? 1 : 0 ;
                content = (( 1 << ( 2 * i + 1 ) & res ) != 0 ? 1 : 0)<<1  |  content ;
                
                switch( content ) {
                    case 0 : content = 'a';
                        break;
                    case 1 : content = 'c';
                        break;
                    case 2 : content = 'g';
                        break;
                    case 3 : content = 't';
                        break;
                }
                chars[i] = content;
            }
            
            return chars[it++];
            
        } else if( it < count ){
            
            return chars[it++];
        }
        return 0;
    }
    
    /**
     * This is the test  method.
     * 
     * @param file	filename
     */
    public static void test(String file) {
        int content = 0;
        DataInputStream dis = null;
        OutputStreamWriter ow = null;
        try {
               
             dis = new DataInputStream( new ACGTInputStream( 
                    new FileInputStream(file)));
             ow = new OutputStreamWriter( 
                     new DataOutputStream(
                     new FileOutputStream("fileUC.txt")));
             while( (content = dis.read()) != -1){
                 ow.write(content);
                 ow.flush();
             }
             
            
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
        ACGTInputStream.test(args[0]);
    }
    
}
