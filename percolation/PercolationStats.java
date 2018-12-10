import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	private double[] result;
	private double mean;
	private double stdd;
	
   public PercolationStats(int n, int trials)// perform trials independent experiments on an n-by-n grid
   {
	   if (n <= 0 || trials <= 0)
           throw new java.lang.IllegalArgumentException("n should be positive integer");
	   result =new double[trials];
	   for(int i=0;i<trials;i++)
	   {
		   Percolation per=new Percolation(n);
		   while(per.percolates()!=true)
		   {
			   int row=StdRandom.uniform(1,n+1);
			   int col=StdRandom.uniform(1,n+1);
			   per.open(row, col);
		   }
		   result[i]=1.0*per.numberOfOpenSites()/(n*n);
	   }
	   mean=StdStats.mean(result);
	   stdd=StdStats.stddev(result);
   }
   
   public double mean()  // sample mean of percolation threshold
   {
             return mean;
   }
   public double stddev()    // sample standard deviation of percolation threshold
   {
	   		return stdd;
   }
   public double confidenceLo() // low  endpoint of 95% confidence interval
   {
	   return mean-1.96*stdd/(Math.sqrt(result.length));
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
	   return mean+1.96*stdd/(Math.sqrt(result.length));
   }



   public static void main(String[] args) // test client (described below)
   {
	   int N=Integer.parseInt(args[0]);
	  int T=Integer.parseInt(args[1]);
	   /*int N = StdIn.readInt();
	  int T = StdIn.readInt();*/
	   Stopwatch wt = new Stopwatch();
	   
	   PercolationStats stats = new PercolationStats(N, T);
	   double elapsed = wt.elapsedTime();
       StdOut.println("mean = " + stats.mean());
       StdOut.println("stddev = " + stats.stddev());
       StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
       
	   /*System.out.println("mean = " + stats.mean());
       System.out.println("stddev = " + stats.stddev());
       System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");*/
	   
   }
}