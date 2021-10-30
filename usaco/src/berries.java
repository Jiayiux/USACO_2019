/*
ID: rayspace
LANG: JAVA
TASK: berries
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class berries
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader inFile = new BufferedReader(new FileReader("berries.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
		
		String firstline = inFile.readLine();
		String[] n_k = firstline.split(" ");
		int n = Integer.parseInt(n_k[0]);
		int k = Integer.parseInt(n_k[1]);
		
		ArrayList<Integer> berries = new ArrayList<Integer>();
		String secondline = inFile.readLine();
		String[] b = secondline.split(" ");
		for (int i=0; i<b.length; i++)
			berries.add(Integer.parseInt(b[i]));
		
		Collections.sort(berries, Collections.reverseOrder());
		
		/*
		for (int i=0; i<berries.size(); i++)
		{
			System.out.print(berries.get(i) + " ");
		}
		
		System.out.println();
		*/
		
		while (true)
		{
			int max = berries.get(0);
			int half = max / 2;
			if (max - half >= berries.get(k/2))
			{
				berries.remove(0);
				berries.add(max - half);
				berries.add(half);
				Collections.sort(berries, Collections.reverseOrder());
			}
			else
			{
				break;
			}
		}
		
		/*
		for (int i=0; i<berries.size(); i++)
		{
			System.out.println(berries.get(i));
		}
		*/
		
		int total=0;
		for (int i=0; i<k/2; i++)
		{
			total += berries.get(i+k/2);
		}
		
		outFile.println(total);
		
	    outFile.close();
	    inFile.close();		
	}
}
