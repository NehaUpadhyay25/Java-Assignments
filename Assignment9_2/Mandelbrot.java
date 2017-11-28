import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
* This is a multi-threaded version of Mandelbrot program given.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class Mandelbrot extends JFrame implements Runnable 
{

	private final int MAX     = 5000;
	private final int LENGTH       = 800;
	private final double ZOOM      = 1000;
	private static BufferedImage I;
	private double zx, zy, cX, cY, tmp;
	private int[] colors = new int[MAX];
	int x1,y1,x2,y2;

	public Mandelbrot() 
	{
		super("Mandelbrot Set");    
		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
 
	public Mandelbrot(int x1, int y1,int x2,int y2)
	{
		super("Mandelbrot Set");    
		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
        this.y2 = y2;
	}
 
	
  
	public void createSet(int numProcessors)    
	{
	    int i  = 0;
	    int half = 0;
		I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Thread[] threads = new Thread[numProcessors];
		
		
		half = (int)Math.sqrt(numProcessors);
		
		int w = getWidth();
		int h = getHeight();
		int x2 = 0;
		int y2=0;
		
		for (int x = 0; x < w; x+=w/half ){
		    for (int y = 0; y < h; y+=h/half ){
		    
		        x2 = x+(w/half)-1;
		        y2 = y+(h/half)-1;
		        
				Mandelbrot mObj = new Mandelbrot(x,y,x2,y2);     
				if( i < numProcessors ) {
				    threads[i] = new Thread(mObj);                
				    threads[i++].start();
				}
				
		    }
				
		}
		for(i=0;i<numProcessors;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

			
		repaint();
	}
    
	public void run()
	{
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                zx = zy = 0;
                cX = (x - LENGTH) / ZOOM;
                cY = (y - LENGTH) / ZOOM;
                int iter = 0;
                while ((zx * zx + zy * zy < 10) && (iter < MAX - 1)) { 
                    tmp = zx * zx - zy * zy + cX; 
                    zy = 2.0 * zx * zy + cY; 
                    zx = tmp; 
                    iter++; 
                } 
                if (iter > 0) 
                {
                    I.setRGB(x, y, colors[iter]); 
                } else {
                    I.setRGB(x, y, iter | (iter << 8)); 
                }
            }
            repaint();
        }
 }

 public void initColors()
 {
     for (int index = 0; index < MAX; index++) 
     {
         colors[index] = Color.HSBtoRGB(index/256f, 1, index/(index+8f));
     }
 }

 @Override
 public void paint(Graphics g) 
 {
     g.drawImage(I, 0, 0, this);
 }

 public static void main(String[] args) 
 {
     int numProcessors = Runtime.getRuntime().availableProcessors();
     Mandelbrot aMandelbrot = new Mandelbrot();
     aMandelbrot.setVisible(true);
     aMandelbrot.createSet(numProcessors);
 }
}