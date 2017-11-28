package Assignment10_3;

import java.util.ArrayList;
import java.util.Random;
/**
* This class builds the functioning of elevator using Sabbath's Algorithm.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class Elevator extends Thread {
	
	static Random rand = new Random();
	public int floor = 0;
	public static int k = 5;
	public int weight = 300;
	public int numOfFloors = 4;
	public int numOfPeople = 0;
	public int[] weights = new int[k];
	public int totalWeight = 0;
	public static ArrayList<Integer> newList = new ArrayList<Integer>();
	public int actualPeople = 0;
	public Object obj = new Object();
	public boolean val = true;
	
	/**
	 * This method is the main run method of the main thread.
	 */
	public void run()
	{
		System.out.println("Elevator Started");
		
		while(val)
		{
			if(floor == 0) // elevator goes up from floor 0 to 4
			{
				floor = floor + 1;
				System.out.println("elevator on the floor " +floor);
				System.out.println("value of k " +k);
				int peopleOnFloor = rand.nextInt(k);
				System.out.println("number of people on the floor is "+peopleOnFloor);
	
				for(int i=0; i<peopleOnFloor; i++)
				{
					weights[i] = rand.nextInt((100-40) + 1) + 40;
				}
	
				System.out.println("the weights are as follows");
				for(int j=0;j<peopleOnFloor;j++)
				{
					if(totalWeight + weights[j] > 300)
					{
						System.out.println("Weight is much will break");
						break;
					}
					actualPeople = actualPeople + 1;
					totalWeight = totalWeight + weights[j];
					newList.add(weights[j]);
					System.out.println(weights[j]);
				}
				System.out.println("Number of people in the lift " +actualPeople);
				System.out.println("total weight of the people in the elevator " +totalWeight);
				System.out.println(" ");
				
				for(int a=1; a<4;a++)
				{
					floor = floor + 1;
					System.out.println("elevator on the floor " +floor);
					System.out.println("value of k " +k);
					peopleOnFloor = rand.nextInt(k);
					System.out.println("number of people on the floor is "+peopleOnFloor);
		
					for(int i=0; i<peopleOnFloor; i++)
					{
						weights[i] = rand.nextInt((100-40) + 1) + 40;
					}
		
					System.out.println("the weights are as follows");
					for(int j=0;j<peopleOnFloor;j++)
					{
						if(totalWeight + weights[j] > 300)
						{
							System.out.println("Weight is much will break");
							break;
						}
						actualPeople = actualPeople + 1;
						totalWeight = totalWeight + weights[j];
						newList.add(weights[j]);
						System.out.println(weights[j]);
					}
					System.out.println("Number of people in the lift " +actualPeople);
					System.out.println("total weight of the people in the elevator " +totalWeight);
					System.out.println(" ");

				}
				
				synchronized(obj)
				{
					int removePeople = people(actualPeople);
					System.out.println("Number of people getting down " +removePeople);
					if(actualPeople - removePeople >=0)
					{
						actualPeople = actualPeople - removePeople;
					
						if(removePeople < newList.size() && newList.size() >=0)
						{
							for(int i = 0 ; i < removePeople - 1; i++)
							{
								totalWeight = totalWeight - newList.remove(i);
							}
						}
					}
					System.out.println("number of people in the lift " +actualPeople);
					if(actualPeople == 0)
					{
						int newWeight = 0;
						for(int i = 0 ; i<newList.size();i++)
						{
							newWeight = newWeight + newList.get(i);
						}
						totalWeight = totalWeight - newWeight;
						System.out.println("Total Weight is as follows " +totalWeight);
						val = false;
						break;
					}
				}
			}
			
			else // elevator goes down from floor 4 to 0
			{
				if(floor>=0){
				for(int b = 4;b>=1;b--)
				{
					floor = floor - 1;
					System.out.println("elevator on the floor " +floor);
					System.out.println("value of k " +k);
					int peopleOnFloor = rand.nextInt(k);
					System.out.println("number of people on the floor is "+peopleOnFloor);
		
					for(int i=0; i<peopleOnFloor; i++)
					{
						weights[i] = rand.nextInt((100-40) + 1) + 40;
					}
		
					System.out.println("the weights are as follows");
					for(int j=0;j<peopleOnFloor;j++)
					{
						if(totalWeight + weights[j] > 300)
						{
							System.out.println("Weight is much will break");
							break;
						}
						actualPeople = actualPeople + 1;
						totalWeight = totalWeight + weights[j];
						newList.add(weights[j]);
						System.out.println(weights[j]);
					}
					System.out.println("Number of people in the lift " +actualPeople);
					System.out.println("total weight of the people in the elevator " +totalWeight);
					System.out.println(" ");
				}
				
				synchronized(obj)
				{
					int removePeople = people(actualPeople);
					System.out.println("Number of people getting down " +removePeople);
					actualPeople = actualPeople - removePeople;
					if(actualPeople - removePeople >=0)
					{
						if(removePeople < newList.size() && newList.size() >=0)
						{
							for(int i = 0 ; i < removePeople - 1; i++)
							{
								totalWeight = totalWeight - newList.remove(i);
							}
						}
					}
					System.out.println("number of people in the lift " +actualPeople);
					if(actualPeople == 0)
					{
						int newWeight = 0;
						for(int i = 0 ; i<newList.size();i++)
						{
							newWeight = newWeight + newList.get(i);
						}
						totalWeight = totalWeight - newWeight;
						System.out.println("Total Weight is as follows " +totalWeight);
						val = false;
						break;
					}
				}
				}
			}
			
			
		}
	}
	
	/**
	 * 
	 * @param actualPeople	The actual number of people in the elevator
	 * @return removePeople the number of people to be removed
	 */
	
	public static int people(int actualPeople)
	{
		int removePeople = rand.nextInt(k);
		return removePeople;
	}
	
	/**
	 * The main method which starts the thread.
	 * 
	 * @param args command line arguments(ignored) 
	 */
	public static void main(String[] args)
	{
		Elevator elevator = new Elevator();
		elevator.start();
	}

}
