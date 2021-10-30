/*
ID: kellyxu
LANG: JAVA
TASK: lineup
*/

import java.io.*;
import java.util.*;

public class lineup {
	static String splitChar = ",";
	static TreeSet<String> lineupSet = new TreeSet<String>();
	static String newCow = null;
	
	static String spliceQueue(String x, String y, String queue) {
		if (queue == null || queue.equals(""))
			return null;
		
		String reverseQueue = "";
		String[] q = queue.split(splitChar);
		int len = q.length;
		for (int i=q.length-1; i>=0; i--) {
			if (i > 0)
				reverseQueue += q[i] + splitChar;
			else
				reverseQueue += q[i];
		}
		
		if (x.compareTo(q[0]) == 0) {
			newCow = y;
			if (y.compareTo(q[len-1]) > 0)
				return reverseQueue + splitChar + y;
			else
				return y + splitChar + queue;
		}
		else if (x.compareTo(q[len-1]) == 0) {
			newCow = y;
			if (y.compareTo(q[0]) > 0)
				return queue + splitChar + y;
			else
				return y + splitChar + reverseQueue;
		}
		if (y.compareTo(q[0]) == 0) {
			newCow = x;
			if (x.compareTo(q[len-1]) > 0)
				return reverseQueue + splitChar + x;
			else
				return x + splitChar + queue;
		}		
		else if (y.compareTo(q[len-1]) == 0) {
			newCow = x;
			if (x.compareTo(q[0]) > 0)
				return queue + splitChar + x;
			else
				return x + splitChar + reverseQueue;
		}
		else {
			newCow = null;
			return null;
		}
	}
	
	static String spliceQueue(String queue1, String queue2) {
		String[] q1 = queue1.split(splitChar);
		int len1 = q1.length;
		String reverseQueue1 = "";
		for (int i=q1.length-1; i>=0; i--) {
			if (i > 0)
				reverseQueue1 += q1[i] + splitChar;
			else
				reverseQueue1 += q1[i];
		}
		
		String[] q2 = queue2.split(splitChar);
		int len2 = q2.length;
		String reverseQueue2 = "";
		for (int i=q2.length-1; i>=0; i--) {
			if (i > 0)
				reverseQueue2 += q2[i] + splitChar;
			else
				reverseQueue2 += q2[i];
		}
		
		if (q1[0].compareTo(q2[0]) == 0) {
			if (q1[len1-1].compareTo(q2[len2-1]) > 0)
				return reverseQueue2 + queue1.substring(q1[0].length());
			else
				return reverseQueue1 + queue2.substring(q1[0].length());
		}
		else if (q1[0].compareTo(q2[len2-1]) == 0) {
			if (q1[len1-1].compareTo(q2[0]) > 0)
				return queue2 + queue1.substring(q1[0].length());
			else
				return reverseQueue1 + reverseQueue2.substring(q1[0].length());
		}
		if (q1[len1-1].compareTo(q2[0]) == 0) {
			if (q1[0].compareTo(q2[len2-1]) > 0)
				return reverseQueue2 + reverseQueue1.substring(q2[0].length());
			else
				return queue1 + queue2.substring(q2[0].length());
		}		
		else if (q1[len1-1].compareTo(q2[len2-1]) == 0) {
			if (q1[0].compareTo(q2[0]) > 0)
				return queue2 + reverseQueue1.substring(q2[len2-1].length());
			else
				return queue1 + reverseQueue2.substring(q2[len2-1].length());
		}
		else {
			return null;
		}
	}
	
	static void addQueue(String queue) {
		if (newCow == null)
			return;
		
		String newqueue;
		
		for (String q:lineupSet)
		{
			if (q.indexOf(newCow + splitChar) == 0 ||
					q.indexOf(splitChar + newCow) == q.length()-(splitChar + newCow).length()) {
				newqueue = spliceQueue(queue, q);
				if (newqueue != null) {
					lineupSet.remove(q);
					lineupSet.add(newqueue);
					return;
				}
			}
		}
		
		lineupSet.add(queue);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader inFile = new BufferedReader(new FileReader("lineup.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
		
		String cows = "Bessie,Buttercup,Belinda,Beatrice,Bella,Blue,Betsy,Sue";
		HashSet<String> cowSet = new HashSet<String>(Arrays.asList(cows.split(",")));
		
		HashSet<String> xySet = new HashSet<String>();
		
		String firstline = inFile.readLine();
		int n = Integer.parseInt(firstline);

		for (int i=0; i<n; i++) {
			String line = inFile.readLine();
			String[] word = line.split(" ");
			
			String x, y;
			if (word[0].compareTo(word[word.length-1]) > 0) {
				x = word[word.length-1];
				y = word[0];
			}
			else {
				x = word[0];
				y = word[word.length-1];
			}
			
			cowSet.remove(x);
			cowSet.remove(y);
			
			if (xySet.contains(x + splitChar + y))
				continue;
			else
				xySet.add(x + splitChar + y);

			Boolean b = false;
			for (String queue:lineupSet)
			{		    	
	    		String newqueue = spliceQueue(x, y, queue);
	    		if (newqueue != null) {
	    			b = true;
	    			lineupSet.remove(queue);

	    			addQueue(newqueue);
	    			break;
	    		}				
			}
						
		    if (!b) {
		    	lineupSet.add(x + splitChar + y);
		    }
		}
		
	    for (String s:cowSet)
	    {
	    	lineupSet.add(s);
	    }
	    
	    for (String s:lineupSet)
	    {
	    	String[] name = s.split(splitChar);
	    	for (int i=0; i<name.length; i++)
	    		outFile.println(name[i]);	    	
	    }
		
	    outFile.close();                                  // close the output file
	    inFile.close();
	}

}