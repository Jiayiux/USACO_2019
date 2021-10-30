/*
ID: kellyxu
LANG: JAVA
TASK: whereami
*/

import java.io.*;

public class whereami {
	public static void main(String[] args) throws IOException {
		BufferedReader inFile = new BufferedReader(new FileReader("whereami.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));
		
		String firstline = inFile.readLine();
		int n = Integer.parseInt(firstline);
		
		String secondline = inFile.readLine();
		int i;
		for (i=1; i<=n-1; i++) {
			Boolean b = true;
			for (int j=0; j<n-i; j++) {
				String colorseq = secondline.substring(j, j+i);
				if (secondline.indexOf(colorseq, j+1) >= 0) {
					b = false;
					break;
				}
			}
			
			if (b) {
				outFile.println(i);
				break;
			}
		}
		
		if (i == n) {
			outFile.println(i);			
		}
		
	    outFile.close();
	    inFile.close();
	}
}
