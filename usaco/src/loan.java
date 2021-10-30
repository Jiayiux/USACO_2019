/*
ID: rayspace
LANG: JAVA
TASK: loan
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class loan
{

	public static void main(String[] args) throws IOException
	{
		BufferedReader inFile = new BufferedReader(new FileReader("loan.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
		
		String firstline = inFile.readLine();
		String[] n_k_m = firstline.split(" ");
		long n = Long.parseLong(n_k_m[0]);
		long k = Long.parseLong(n_k_m[1]);
		long m = Long.parseLong(n_k_m[2]);
		
		long x;
	    
		for (x=n/m; x>0; x--)
		{
			long g = 0;
			long y;
			int i;
			for (i=0; i<k; i++)
			{
				y = (n - g) / x;
				if (y < m)
					y = m;
				g += y;
				//System.out.println("需还：" + n + "已还：" + g + "基数：" + x);
				if (g > n)
					break;
			}
			
			if (g > n && i == k-1)
			{
				//System.out.println(x);
				outFile.println(x);
				break;
			}
		}
		
	    outFile.close();
	    inFile.close();
	}

}
