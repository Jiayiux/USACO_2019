/*
ID: kellyxu
LANG: JAVA
TASK: gymnastics
*/

import java.io.*;
import java.util.*;

public class gymnastics {
	public static void main(String[] args) throws IOException {
		BufferedReader inFile = new BufferedReader(new FileReader("gymnastics.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
		
		String firstline = inFile.readLine();
		String[] k_n = firstline.split(" ");
		int k = Integer.parseInt(k_n[0]);
		int n = Integer.parseInt(k_n[1]);
		
		HashSet<String> pairset = new HashSet<String>();
		
		String session = inFile.readLine();
		String[] cow = session.split(" ");
		for (int j=0; j<n; j++) {
			for (int m=j+1; m<n; m++) {
				String pair = cow[j] + "," + cow[m];
				pairset.add(pair);
			}
		}
		
		for (int i=1; i<k; i++) {
			session = inFile.readLine();
			String[] sess = session.split(" ");
			
			for (int j=0; j<n; j++) {
				for (int m=j+1; m<n; m++) {
					String pair = sess[j]+","+sess[m];
					if (!pairset.contains(pair)) {
						pairset.remove(sess[m]+","+sess[j]);
					}
				}
			}
		}
		
		outFile.println(pairset.size());
		
	    outFile.close();
	    inFile.close();
	}
}
