/*
ID: kellyxu
LANG: JAVA
TASK: triangles
*/

import java.io.*;
import java.util.*;

public class triangles
{
	static class Problem2
	{
		HashMap<Integer,HashSet<Integer>> vertical = new HashMap<Integer,HashSet<Integer>>();
		HashMap<Integer,HashSet<Integer>> horizontal = new HashMap<Integer,HashSet<Integer>>();
		
		class FencePostPoint implements Comparable
		{
			int x, y;
			
			public FencePostPoint(int i, int j)
			{
				x = i;
				y = j;
			}
			
			public void print()
			{
				System.out.println(x + ", " + y);
			}
			
			public boolean isRightAngle ()
			{
				if (vertical.get(x).size() >= 2 && horizontal.get(y).size() >= 2)
					return true;
				else
					return false;
			}
			
			public long area()
			{
				long total_x = 0;
				long total_y = 0;
				
				HashSet<Integer> s = vertical.get(x);
				for (int i : s)
				{
					if (y > i)
						total_y += y - i;
					else
						total_y += i - y;
				}
				
				s = horizontal.get(y);
				for (int i : s)
				{
					if (x > i)
						total_x += x - i;
					else
						total_x += i - x;
				}
				
				//System.out.println("面积：" + total_x*total_y);
				return total_x*total_y;
			}

			@Override
			public int compareTo(Object o)
			{
				FencePostPoint s = (FencePostPoint) o;
			    if (this.x > s.x)
			    {
			      return 1;
			    }
			    else if (this.x < s.x)
			    {
			      return -1;
			    }
			    else
			    {
			    	if (this.y >= s.y)
			    	{
			    		return 1;
			    	}
			    	else
			    	{
			    		return -1;
			    	}
			    }
			}
		}
		
		public void solution() throws IOException
		{
			BufferedReader inFile = new BufferedReader(new FileReader("D:\\usaco\\src\\triangles.in"));
			PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("D:\\usaco\\src\\triangles.out")));
			
			String firstline = inFile.readLine();
			int N = Integer.parseInt(firstline);
			
			ArrayList<FencePostPoint> fpps = new ArrayList<FencePostPoint>();
			
			String line;
			int x, y;
			for (int i=0; i<N; i++)
			{
				line = inFile.readLine();
				String[] x_y = line.split(" ");
				x = Integer.parseInt(x_y[0]);
				y = Integer.parseInt(x_y[1]);
				
				FencePostPoint fpp = new FencePostPoint(x, y);
				fpps.add(fpp);
				
				if (vertical.containsKey(x))
					vertical.get(x).add(y);
				else
				{
					HashSet<Integer> s = new HashSet<Integer>();
					s.add(y);
					vertical.put(x, s);
				}
				
				if (horizontal.containsKey(y))
					horizontal.get(y).add(x);
				else
				{
					HashSet<Integer> s = new HashSet<Integer>();
					s.add(x);
					horizontal.put(y, s);
				}
			}
			
			long totalArea = 0;
			
			for (int i=0; i<fpps.size(); i++)
			{
				if (fpps.get(i).isRightAngle())
				{
					totalArea = totalArea + fpps.get(i).area();
				}
			}
	        
			//Collections.sort(fences);
			//Collections.reverse(fences);
			
			/*
			for(Map.Entry<Integer, HashSet<Integer>> entry : lines_x.entrySet()){
			    int mapKey = entry.getKey();
			    System.out.print("X = " + mapKey + " ; Y = ");
			    HashSet<Integer> mapValue = entry.getValue();
				Iterator iterator = mapValue.iterator();
				while (iterator.hasNext()) {
					System.out.print(iterator.next() + ", ");			
				}
				System.out.println();
			}
			
			for(Map.Entry<Integer, HashSet<Integer>> entry : lines_y.entrySet()){
			    int mapKey = entry.getKey();
			    System.out.print("Y = " + mapKey + " ; X = ");
			    HashSet<Integer> mapValue = entry.getValue();
				Iterator iterator = mapValue.iterator();
				while (iterator.hasNext()) {
					System.out.print(iterator.next() + ", ");			
				}
				System.out.println();
			}
			
			for (int i=0; i<fences.size(); i++)
			{
				fences.get(i).print();
			}
			*/
			
			outFile.println(totalArea % (1000000007));
		    outFile.close();
		    inFile.close();
		}
		
	}

	public static void main(String[] args) throws IOException
	{
		Problem2 p = new Problem2();
		
		p.solution();
	}

}
