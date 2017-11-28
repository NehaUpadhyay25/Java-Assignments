import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.IOException;

/**
* This class used does the Compression and Decompression of file.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class Compress {

	/**
     * This method is used to compress file.
     * 
     * @param     inputFileName     File to be compressed.
     */
	private static void compressFile( String inputFileName ) {
        try {
        	        	  
        	  int index = inputFileName.indexOf(".");
        	         	         	  
        	  String outputFileName = inputFileName.substring(0,index); 
        	  
        	  FileInputStream file = new FileInputStream(inputFileName);
              
        	  DataOutputStream outputStream = new DataOutputStream(
        			  new GZIPOutputStream( new FileOutputStream ( outputFileName + ".Z" ) ) );
        	  
        	  byte[] buffer = new byte[2048];
              int len;
              while( ( len=file.read( buffer ) ) != -1 ) {
                  outputStream.write( buffer, 0, len );
                  
              }
              //close resources
              outputStream.close();
              file.close();
        } 
        catch( IOException e ) {
        	e.printStackTrace();
        }
    }

	/**
     * This method is used to decompress file.
     * 
     * @param     inputFileName     File to be decompressed.
     */
	private static void decompressFile( String inputFileName ) {
    	try {
    		
    		int index = inputFileName.indexOf(".");
    		
    		FileOutputStream file = new FileOutputStream( inputFileName + ".uc" );
            
            DataInputStream inputStream = new DataInputStream( new GZIPInputStream
            		( new FileInputStream( inputFileName ) ) );
            
            
            byte[] buffer = new byte[1024];
            int len;
            while(( len = inputStream.read( buffer ) ) != -1 ) {
                file.write( buffer, 0, len );
            }
            //close resources
            file.close();
            inputStream.close();
        } 
    	catch( IOException e ) {
            e.printStackTrace();
        }  	        
    }

	/**
     * This method is the main method.
     * 
     * @param    args    command line arguments 
     */
    public static void main( String[] args ) {
		
		String inputFileName = args[0]; 
		
		if( inputFileName.endsWith( ".Z" ) ) {
			decompressFile( inputFileName );
		}
		else {
			compressFile( inputFileName );
		}
	}
}
