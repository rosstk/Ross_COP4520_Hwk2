import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Queue;
import java.util.LinkedList;

public class Hwk2_1
{
	public static void main(String[] args)
	{
		Scanner newScanner = new Scanner(System.in);
		
		System.out.print("Enter the number of guests: ");
		int numGuests = newScanner.nextInt();
		Guest[] guests = new Guest[numGuests];
		
		// Initialize and start guests
		for (int i=0; i<numGuests; i++)
		{
			guests[i] = new Guest(i+1);
			guests[i].start();
		}
	}
}

class Guest extends Thread
{
	public static Lock vaseLock = new ReentrantLock();
	public static Queue<Guest> vaseQueue = new LinkedList<>();
	public boolean myTurn;
	public int noCount, num;
	
	
	public Guest(int num)
	{
		this.num = num;
		noCount = 0;
		myTurn = false;
	}
	
	// Guest randomly decides to view vase with 50% chance
	// If they say no to viewing it two times, they are done
	// running
	public void run()
	{
		Random rand = new Random();
		while (noCount < 2)
		{
			float randFloat = rand.nextFloat();
			if (randFloat < .5)
			{
				vaseQueue.add(this);
				if (vaseQueue.size() == 1) // Go in room if first in line
				{
					viewVase();
				}
				else
				{
					waitInLine();
				}
			}
			else
			{
				System.out.println("Guest #" + num + " has said no to viewing the vase.");
				noCount++;
			}
		}
		
		System.out.println("Guest #" + num + " has left the party.");
	}
	
	public void waitInLine()
	{
		System.out.println("Guest #" + num + " is waiting in line.");
		while (!myTurn)
		{
			//wait
		}
		viewVase();
	}
	
	public void viewVase()
	{
		vaseLock.lock();
		try
		{
			myTurn = false;
			System.out.println("Guest #" + num + " has viewed the vase.");
		} finally
		{
			System.out.println("Guest #" + num + " has left the showroom.");
			vaseLock.unlock();
			vaseQueue.remove();
			if (vaseQueue.size() > 0)
			{
				vaseQueue.peek().myTurn = true;
			}
		}
	}
	
}