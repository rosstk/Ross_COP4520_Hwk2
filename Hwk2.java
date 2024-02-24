import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hwk2
{
	public static void main(String[] args)
	{
		Scanner newScanner = new Scanner(System.in);
		Random rand = new Random();
		
		System.out.print("Enter the number of guests: ");
		int numGuests = newScanner.nextInt();
		GuestThread[] guests = new GuestThread[numGuests];
		
		// Initialize and start guests
		for (int i=0; i<numGuests; i++)
		{
			guests[i] = new GuestThread(i+1);
			guests[i].start();
		}
		
		// Call guests
		while (GuestThread.numUniqueVisits != numGuests)
		{
			int randInt = rand.nextInt(numGuests);
			guests[randInt].called = true;
		}
		
		GuestThread.partyGoing = false;
		boolean allVisited = true;
		int j;
		for (j=0; j<numGuests; j++)
		{
			guests[j].interrupt();
			if (!guests[j].hasSeenEmptyPlate)
			{
				System.out.print("Guest #" + (j+1) + " did not visit the labyrinth. The minotaur ");
				System.out.println("is not happy!");
				allVisited = false;
			}
		}
		if (allVisited)
		{
			System.out.println("All guests went through the labyrinth. Guests went through the labyrinth.");
		}
		else
		{
			System.out.println("All guests did not go through the labyrinth.");
		}
	}
}

class GuestThread extends Thread 
{
	public static boolean cupcakeFlag = true, partyGoing = true;
	public static Lock mazeLock = new ReentrantLock(), exitLock = new ReentrantLock();
	public static int numUniqueVisits = 0;
	public int num;
	public boolean called, hasSeenEmptyPlate, isTracker;
	
	// Give guests num and they have not visited by default
	public GuestThread(int num)
	{
		this.num = num;
		hasSeenEmptyPlate = false;
	}
	
	public void run()
	{
		while (partyGoing)
		{
			while (!called)
			{
				// Minotaur has not called guest to go
				//wait
			}
			called = false;
			findExit();
		}
	}
	
	void findExit()
	{
		mazeLock.lock();
		try
		{
			System.out.println("Guest #" + this.num + " was invited into the labyrinth.");
			System.out.println("Guest #" + this.num + " has exited the labyrinth.\n");
		} finally
		{
			mazeLock.unlock();
		}
		askForCupcake();
	}
	
	void askForCupcake()
	{
		exitLock.lock();
		try
		{
			if (numUniqueVisits == 0)
			{
				isTracker = true;
			}
			
			if (cupcakeFlag && isTracker)
			{
				eatCupcake();
				numUniqueVisits++;
				cupcakeFlag = false;
			}
			else if (!cupcakeFlag)
			{
				if (!hasSeenEmptyPlate)
				{
					cupcakeFlag = true;
					System.out.println("Guest #" + this.num + " has asked for a cupcake.");
					hasSeenEmptyPlate = true;
				}
			}
		} finally
		{
			exitLock.unlock();
		}
		leaveExit();
	}
	
	void leaveExit()
	{
		System.out.println("Guest #" + this.num + " has left the exit.\n");
	}
	
	void eatCupcake()
	{
		System.out.println("Guest #" + this.num + " has eaten the cupcake.");
		hasSeenEmptyPlate = true;
	}
}