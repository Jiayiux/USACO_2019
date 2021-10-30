/*
ID: kellyxu
LANG: JAVA
TASK: meetings
*/

import java.io.*;
import java.util.*;

public class meetings
{
	static class Cow {
		public int id;
		public float x;
		public int w;
		public int d;
		public float t;
		public int action;	// 1: left barn, 2: right barn, 100: left-right meeting, 200: right-left meeting, 300 - left-left meeting, 400: right-right meeting 
		
		public Cow() {
		}
		
		public float getX() {
			return x;
		}
	}
	
	static ArrayList<Cow> cows = new ArrayList<Cow>();
	static long total_weight = 0;
	static long total_weight_in_barn = 0;
	
	static void updateCowsStatus(int id, float mint) {
		for (int i=0; i<cows.size(); i++) {
			if (i == id) {
				if (cows.get(id).action == 1 || cows.get(id).action == 2) {
					total_weight_in_barn = total_weight_in_barn + cows.get(id).w;
					cows.remove(id);
					i--;
					id = -1;
				}
				else {
					cows.get(i).x += cows.get(i).d * mint;
					cows.get(i+1).x += cows.get(i+1).d * mint;
					
					int d = cows.get(i).d;
					cows.get(i).d = cows.get(i+1).d;
					cows.get(i+1).d = d;
					
					cows.get(i+1).action = cows.get(i).action = 0;
					cows.get(i+1).t = cows.get(i).t = Float.MAX_VALUE;
					i++;
				}
			}
			else {
				cows.get(i).x += cows.get(i).d * mint;
				cows.get(i).action = 0;
				cows.get(i).t = Float.MAX_VALUE;
			}
		}
	}
	
	static class SortByX implements Comparator<Cow> {
		@Override
		public int compare(Cow c1, Cow c2) {
			if(c1.getX() > c2.getX()){
				return 1;
			}
			return -1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader inFile = new BufferedReader(new FileReader("D:\\usaco\\src\\meetings.in"));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("D:\\usaco\\src\\meetings.out")));
		
		String firstline = inFile.readLine();
		String[] n_l = firstline.split(" ");
		int n = Integer.parseInt(n_l[0]);
		int l = Integer.parseInt(n_l[1]);
		
		for (int i=0; i<n; i++) {
			String line = inFile.readLine();
			String[] w_x_d = line.split(" ");
			
			Cow cow = new Cow();
			cow.id = i;
			cow.w = Integer.parseInt(w_x_d[0]);
			cow.x = Integer.parseInt(w_x_d[1]);
			cow.d = Integer.parseInt(w_x_d[2]);
			cow.t = Float.MAX_VALUE;
			cow.action = 0;
			
			total_weight += cow.w;
			
			cows.add(cow);
		}
		
		for (int i=0; i<cows.size(); i++) {
			Cow c = cows.get(i);
			System.out.printf("id=%d, x=%f, speed=%d\n", c.id, c.x, c.d);
		}
		System.out.println();

		
		if (cows.size() == 1) {
			outFile.println("0");
			return;
		}
		 
		Collections.sort(cows,new SortByX());
		
		long meetings = 0;
		
		while (total_weight_in_barn < total_weight / 2) {
			float mint = Float.MAX_VALUE;
			int minid = -1;
			
			for (int i=0; i<cows.size(); i++) {
				if (i == 0) {
					if (cows.get(i).d < 0) {
						cows.get(i).action = 1;
						cows.get(i).t = cows.get(i).x / -cows.get(i).d;
						
						if (mint > cows.get(i).t) {
							mint = cows.get(i).t;
							minid = i;
						}
					}
				}
				else if (i == cows.size()-1) {
					if (cows.get(i).d > 0) {
						cows.get(i).t = (l - cows.get(i).x) / cows.get(i).d;
						cows.get(i).action = 2;
						
						if (mint > cows.get(i).t) {
							mint = cows.get(i).t;
							minid = i;
						}
					}
					continue;
				}

				if (cows.get(i).d > 0 && cows.get(i+1).d < 0) {
					cows.get(i+1).t = cows.get(i).t = (cows.get(i+1).x - cows.get(i).x) / (cows.get(i).d - cows.get(i+1).d);
					cows.get(i).action = 100;
					cows.get(i+1).action = 200;
					
					if (mint > cows.get(i).t) {
						mint = cows.get(i).t;
						minid = i;
					}
					
					i++;
				}
				else if (cows.get(i).d > 0 && cows.get(i+1).d > 0 && cows.get(i).d > cows.get(i+1).d)
				{
					float t = (cows.get(i+1).x - cows.get(i).x) / (cows.get(i).d - cows.get(i+1).d);
					
					if (t < cows.get(i).t) {
						cows.get(i+1).t = cows.get(i).t = t;
						cows.get(i).action = 500;
						cows.get(i+1).action = 600;
						
						if (mint > cows.get(i).t) {
							mint = cows.get(i).t;
							minid = i;
						}
					}
				}
				else if (cows.get(i).d < 0 && cows.get(i+1).d < 0 && cows.get(i+1).d < cows.get(i).d)
				{
					float t = (cows.get(i+1).x - cows.get(i).x) / (cows.get(i).d - cows.get(i+1).d);
					if (t < cows.get(i).t) {
						cows.get(i+1).t = cows.get(i).t = t;
						
						cows.get(i).action = 300;
						cows.get(i+1).action = 400;
						
						if (mint > cows.get(i).t) {
							mint = cows.get(i).t;
							minid = i;
						}
					}
				}
			}
			
			System.out.printf("acion=%d\n", cows.get(minid).action);
			
			if (cows.get(minid).action >= 100)
				meetings++;
			
			updateCowsStatus(minid, mint);
			
			
			for (int i=0; i<cows.size(); i++) {
				Cow c = cows.get(i);
				System.out.printf("id=%d, x=%f, speed=%d\n", c.id, c.x, c.d);
			}
			System.out.println();
		}
		
		outFile.println(meetings);
		
	    outFile.close();
	    inFile.close();
	}

}
