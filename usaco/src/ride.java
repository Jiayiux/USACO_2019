/*
ID: kellyxu
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

public class ride
{
	public static int getNumber(String w)
	{
		if (w == null)
			return -2;
		
		int p=1;
		for (int i=0; i<w.length(); i++)
		{
			int n = w.charAt(i) - 'A' + 1;
			if (n < 1 || n > 26)
				return -1;
			p = p*n;
		}
		
		return p;
	}
	
	public static void main(String[] args) throws IOException
	{
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
		
		// input file name goes above
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

	    try
	    {
			String comet = f.readLine();
			int comet_number = getNumber(comet);
			
			if (comet_number == -1)
			{
				out.println("wrong comet name: " + comet);
				
			    out.close();                                  // close the output file
			    f.close();
			    
				System.exit(1);
			}
			else if (comet_number == -2)
			{
			    out.close();                                  // close the output file
			    f.close();
			    
				System.exit(1);				
			}
				
			
			String group = f.readLine();		
			int group_number = getNumber(group);
			if (group_number == -1)
			{
				out.println("wrong group name: " + group);
				
			    out.close();                                  // close the output file
			    f.close();
			    
				System.exit(2);
			}
			else if (group_number == -2)
			{
			    out.close();                                  // close the output file
			    f.close();
			    
				System.exit(1);				
			}
			
			
			if (comet_number % 47 == group_number % 47)
			{
				out.println("GO");
			}
			else
			{
				out.println("STAY");
			}	    	
	    }
	    
	    catch(IOException ex)
	    {
	    	
	    }
		
	    out.close();                                  // close the output file
	    f.close();
	}

}
