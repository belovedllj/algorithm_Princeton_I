/*----------------------------------------------------------------
 *  Author:        Eric Peng
 *  Written:       8/30/2013
 *  Last updated:  8/30/2013
 *
 *  Compilation:   javac Percolation.java
 *  
 *  Use this class to calculate the percolation probability.
 *
 *----------------------------------------------------------------*/

public class Percolation {
	
	private WeightedQuickUnionUF UF;
	// two-dimension array to record each site open or not.	
	// Top virtual site is 0, downmost virtual site is N^2-1.
	private boolean[][] siteIsOpen;  
	private int rowNumber;
	
	
	
	public Percolation(int N) {
		rowNumber = N;
		UF = new WeightedQuickUnionUF(rowNumber * rowNumber + 2); 
		siteIsOpen = new boolean[N][N];    // Initialize each element to false.
		
	}
	/**
	 *   siteIsOpen start with[0][0],  Union first row's sites to top virtual site
	 *   and Union last row's sites to downmost virtual site.
	 *   Union sites which are open besides [i][j] site. (has 4 possible open sites).
	 *   
	 */
	public void open(int i, int j) {
		if (i <= 0 || i > rowNumber) 
			throw new IndexOutOfBoundsException
			    ("row index i out of bound");
		if (j <= 0 || j > rowNumber) 
			throw new IndexOutOfBoundsException
			    ("row index j out of bound");
		if (siteIsOpen[i - 1][j - 1] == true) 
			return;
			siteIsOpen[i - 1][j - 1] = true;
			if (i == 1) {
				UF.union(0, (i - 1) * rowNumber + j);
			}
			// We don't use else if, just in case rowNumber == 1;
			if (i == rowNumber) { 
				UF.union(rowNumber * rowNumber + 1, (i - 1) * rowNumber + j);
			}
			if (j != 1 && siteIsOpen[i - 1][j - 2] == true)
				UF.union((i - 1) * rowNumber + j, (i - 1) * rowNumber + j - 1);
			if (j != rowNumber && siteIsOpen[i - 1][j] == true)
				UF.union((i - 1) * rowNumber + j, (i - 1) * rowNumber + j + 1);
			if (i != rowNumber && siteIsOpen[i][j - 1] == true)
				UF.union((i - 1) * rowNumber + j, i * rowNumber + j);
			if (i != 1 && siteIsOpen[i - 2][j - 1] == true)
				UF.union((i - 1) * rowNumber + j, (i - 2) * rowNumber + j);
				
	}
	
	public boolean isOpen(int i, int j) {
		if (i <= 0 || i > rowNumber) 
			throw new IndexOutOfBoundsException
			       ("row index i out of bound");
		if (j <= 0 || j > rowNumber) 
			throw new IndexOutOfBoundsException
			       ("row index j out of bound");
			
		return siteIsOpen[i - 1][j - 1];
	}
	/** If site[i][j]'s root equals top virtual site's root,
	 *  return true
	*/
	public boolean isFull(int i, int j) {
		if (i <= 0 || i > rowNumber) 
			throw new IndexOutOfBoundsException
			         ("row index i out of bound");
		if (j <= 0 || j > rowNumber) 
			throw new IndexOutOfBoundsException
			         ("row index j out of bound");
		return UF.find((i - 1) * rowNumber + j) == UF.find(0);
	}
	/** top virtual site's root equals downmost virtual
	 *  site's root, return true.
	*/
	public boolean percolates() {
		return UF.find(rowNumber * rowNumber + 1) == UF.find(0);
	}
}
