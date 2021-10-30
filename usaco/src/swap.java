/*
ID: rayspace
LANG: JAVA
TASK: swap
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class swap
{
	static int[] cows0;
	static int[] cows1;
	static int pos_map[];
	
	static int[] pairs_l;
	static int[] pairs_r;
	
	static public void swapCows(int l, int r)
	{
		int c;
		for (int i=0; i<(r+1-l)/2; i++)
		{
			System.out.println((l+i-1) + "," + (r-i-1));
			c = cows1[l+i-1]; 
			cows1[l+i-1] = cows1[r-i-1];
			cows1[r-i-1] = c;
		}
	}
	
	static public void println(int[] c, int n, String remark)
	{
		System.out.print(remark);
		for (int i=0; i<n; i++)
		{
			System.out.print(c[i] + ", ");
		}
		System.out.println();
	}
	
	static public void mapCows(int[] dest, int[] source, int n)
	{
		for (int i=0; i<n; i++)
		{
			dest[pos_map[i]] = source[i];
		}
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader inFile = new BufferedReader(new FileReader("D:\\usaco\\src\\swap.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("D:\\usaco\\src\\swap.out")));
		
		String firstline = inFile.readLine();
		String[] n_m_k = firstline.split(" ");
		int n = Integer.parseInt(n_m_k[0]);
		int m = Integer.parseInt(n_m_k[1]);
		int k = Integer.parseInt(n_m_k[2]);
		
		cows0 = new int[n];
		pairs_l = new int[m];
		pairs_r = new int[m];
		
		cows0 = new int[n];
		cows1 = new int[n];
		pos_map = new int[n];

		for (int i=0; i<n; i++)
		{
			cows0[i] = i;
			cows1[i] = i;
		}
			
		String line;
		for (int i=0; i<m; i++)
		{
			line = inFile.readLine();
			String[] l_r = line.split(" ");
			pairs_l[i] = Integer.parseInt(l_r[0]);
			pairs_r[i] = Integer.parseInt(l_r[1]);
		}
		
		for (int j=0; j<m; j++)
		{
			//System.out.println(pairs_l[j] + ", " + pairs_r[j]);
			swapCows(pairs_l[j], pairs_r[j]);
		}
		
		for (int i=0; i<n; i++)
		{
			pos_map[cows1[i]] = i;
		}
		
		println(pos_map, n, "got map : ");
		println(cows0, n, "base : ");
		for (int i=0; i<k; i++)
		{
			if (i % 2 == 0)
			{	
				mapCows(cows1, cows0, n);
				println(cows1, n, "The " + (i+1) + " Round : ");
			}
			else
			{
				mapCows(cows0, cows1, n);
				println(cows0, n, "The " + (i+2) + " Round : ");
			}
		}
		
		for (int i=0; i<n; i++)
		{
			//System.out.println(cows[i]);
			outFile.println(cows0[i]+1);
		}
		
	    outFile.close();
	    inFile.close();
	}

}
