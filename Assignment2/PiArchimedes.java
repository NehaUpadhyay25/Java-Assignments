package assignment2;

class PiArchimedes{
 public static void main(String args[]){
 // computes the value for pi using inscribed regular
 // polygons like Archimedes
 double s = 1;// hexagon of side lenght 1 inscribed in a circle of
 // radius 1
 int N = 96;// N limits the size of the largest polygon to 2*N
 int n = 6; // n = 6 begins with a hexagon
 double E;
 while(n <= N){
 double c = c(s);
 double d = d(c);
 s = new_s(s,d);// computes s for a 2n sided polygon
 // pi for dodecagon is 6*s - in general pi = n*s
 double pi = n*s;// pi estimate is computed as
 //(number of sides)/2 * (length of each side)
 E = Math.PI - pi;//E is the computation error
 System.out.println("number of sides is "+2*n);
 System.out.println("c = "+c+" d = "+d+" s2 = "+s+" Pi = "+pi);
 System.out.println("Error in calculation = "+E);
 n *= 2;
 }
 }
 // the parameter s2 is for the next polygon with 2*n sides
 static double new_s(double s1,double d){
 double s2=Math.sqrt((s1/2)*(s1/2)+(d*d));
 return s2;
 }
 //Compute the parameter c1 from s1
 static double c(double s1){
 double c1 = Math.sqrt(1-(s1/2)*(s1/2));
 return c1;
 }
 //Compute the parameter d using c.
 static double d(double c){
 double d = 1.0 - c;
 return d;
 }
} 