package assignment1;

public class subsets
{
	static int[] coins=new int[]{1,2,5,10,10,25,25,25,50};
	static int n=coins.length;
	
	public static void main(String args[])
	{	    
	    for (int i = 1;i <= n;i++)
        subset(0, 0, i);
	}
		 
/*Function to find the number of subsets in the given string*/
		 
static void subset(int start, int index, int num_sub)
{
	int i, j,temporary_sum=0,sum=0;
	if (index - start + 1  ==  num_sub)
	{
		if (num_sub  ==  1)
		{
			for (i = 0;i < n;i++){
				sum=coins[i];
		   		System.out.println(coins[i]+" = "+sum+"\n");
			}
		}
		else
		{
			for (j = index;j < n;j++)
		    {	sum=0;
				for(i = start;i < index;i++)
				{  System.out.print(" "+coins[i]);
					sum=sum+coins[i];
				}
		        System.out.print(" "+coins[j]+" = ");
		        temporary_sum=sum;
				temporary_sum=temporary_sum+coins[j];
				System.out.print(" "+temporary_sum+"\n\n");
		    }
		    if (start != n - num_sub)
		    subset(start + 1, start + 1, num_sub);
		}
	}
		else
		{
			subset(start, index + 1, num_sub);
		}
}
		
}