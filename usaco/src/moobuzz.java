/*
ID: kellyxu
LANG: JAVA
TASK: moobuzz
*/

import java.io.*;

public class moobuzz
{

	public static void main(String[] args) throws IOException {
		BufferedReader inFile = new BufferedReader(new FileReader("moobuzz.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
		
		String firstline = inFile.readLine();
		long n = Long.parseLong(firstline);
		
		long base = (long) Math.ceil((double)n/8);
		long m = 15 * base;
		
		long k = 8 * base;
		
		for (long i=m; i>=0; i--) {
			if (i % 3 == 0 || i % 5 == 0 || i % 15 == 0) {
				continue;
			}
			
			if (k == n) {
				outFile.println(i);
				break;
			}
			else {
				k--;				
			}
		}

	    outFile.close();
	    inFile.close();		
	}
}
