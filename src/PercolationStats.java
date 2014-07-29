
/*----------------------------------------------------------------
 *  Author:        Eric Peng
 *  Written:       8/30/2013
 *  Last updated:  8/30/2013
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats args[0] args[1]
 *  
 *  Use this class to calculate the percolation probability.
 *
 *----------------------------------------------------------------*/

public class PercolationStats {

	private int gridSizeN;        // N by N grid
	private int experimentTimes;  
	// indexRecord[i] records probability in ith experiment
	private double[] indexRecord; 
	//private int openedSitesCount;
	private double probabilitySum; // Sum of probabilities in all T experiments
	
	/**
	 * There is no getUF() method in Percolation class
	 * so, I could not use UF.count()
	 *  I have to count how many sites is open in this class 
	 */
	
	public PercolationStats(int N, int T) {
		gridSizeN = N;
		experimentTimes = T;
		
		indexRecord = new double[experimentTimes];
		for (int count = 0; count < experimentTimes; count++) {
			Percolation percolationExperiment = new Percolation(gridSizeN);
			int openedSitesCount = 0;
			double probability = 0;
			while (true) {
				int i = StdRandom.uniform(1, N + 1);
				int j = StdRandom.uniform(1, N + 1);
				if (percolationExperiment.isOpen(i, j))
					continue;
				else {
					percolationExperiment.open(i, j);			
					openedSitesCount++;
				}
			if (percolationExperiment.percolates())
				break;
			}
			probability = openedSitesCount / (double) (gridSizeN * gridSizeN);
			probabilitySum += probability;
			indexRecord[count] = probability;
		}
	}
	
	public double mean() {
		return probabilitySum / (double) experimentTimes;
	}
	
	public double stddev() {
		if (experimentTimes == 1)
		return Double.NaN;
		else {
			double varianceSum = 0;
			for (int i = 0; i < experimentTimes; i++) {
				varianceSum += (indexRecord[i] - mean()) * 
						(indexRecord[i] - mean());
			}
			return Math.sqrt(varianceSum / (double) (experimentTimes - 1));
		}
			
	}
	
	public double confidenceLo() {
		if (experimentTimes == 1)
			return Double.NaN;
		else 
			return mean() - 1.96 * stddev() / Math.sqrt((double) experimentTimes);		
	}
	
	public double confidenceHi() {
		if (experimentTimes == 1)
			return Double.NaN;
		else 
			return mean() + 1.96 * stddev() / Math.sqrt((double) experimentTimes);	
	}
	
	public static void main(String[] args) {
		int gridRowNumberN = Integer.parseInt(args[0]);
		int experimentTimes = Integer.parseInt(args[1]);
		if (gridRowNumberN < 1 || experimentTimes < 1)
			throw new IndexOutOfBoundsException
			("gridRowNumberN or experimentTimes must be positive.");
		PercolationStats percolation = new PercolationStats
				(gridRowNumberN, experimentTimes);
		StdOut.printf("%-27s", "mean");
		StdOut.print(" = ");
		StdOut.printf("%-20.18f", percolation.mean());
		StdOut.println();
		StdOut.printf("%-27s", "stddev");
		StdOut.print(" = ");
		StdOut.printf("%-20.18f", percolation.stddev());
		StdOut.println();
		StdOut.printf("%-27s", "95% confidence interval");
		StdOut.print(" = ");
		StdOut.printf("%-20.18f", percolation.confidenceLo());
		StdOut.print(", ");
		StdOut.printf("%-20.18f", percolation.confidenceHi());
	}
}
