import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Fast {
	private static boolean addInOrNot = false;
	private static ArrayList<Point> colliearFirstPointCollection = new ArrayList<Point>();

	public static void main(String[] args) {
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		int[] input = new In("inarow.txt").readAllInts();
		int inputNumber = input[0];
		Point[] inputPoints = new Point[inputNumber];
		for (int i = 0; i < inputNumber; i++) {
			inputPoints[i] = new Point(input[2 * i + 1], input[2 * i + 2]);
			inputPoints[i].draw();
		}
        StdRandom.shuffle(inputPoints);
		for(int i = 0; i < inputNumber - 2; i++) {
			Point originPoint = inputPoints[i];
			sort(inputPoints, i + 1, inputNumber - 1, originPoint);
			if (addInOrNot) {
				colliearFirstPointCollection.add(originPoint);
				addInOrNot = false;
			}
		}
		StdDraw.show(0);
	}
			
			
			
			
			
			
			
			//Arrays.sort(inputPoints, i + 1, inputNumber, slopeOrder);
			
			//for (int l=1; l<inputNumber; l++) {
				//StdOut.println(inputPoints[l].slopeTo(originPoint));
			//}
			
			//if (i >= inputNumber - 3) break;
			/*for(int j = i + 1; j < inputNumber - 2; ) {
				double slope1 = inputPoints[j].slopeTo(originPoint);
				//if (j >= inputNumber - 2) break;
				double slope2 = inputPoints[j + 2].slopeTo(originPoint);
				if (slope1 != slope2) {
					j++; continue;
				}
				else {
					int k = j + 3;
					/*if (k >= inputNumber) {
						Point[] collinearPoints = new Point[4];
						collinearPoints[0] = originPoint;
						collinearPoints[1] = inputPoints[j];
						collinearPoints[2] = inputPoints[j + 1];
						collinearPoints[3] = inputPoints[j + 2];
						Arrays.sort(collinearPoints);
						for (int w = 0; w < 4; w++) {
							StdOut.print(collinearPoints[w]);
							if (w != 3)
								StdOut.print(" -> ");								
						}
						StdOut.println("");
						collinearPoints[0].drawTo(collinearPoints[3]);
					}
					
					while (true) {
						if ( k >= inputNumber|| inputPoints[k].slopeTo(originPoint) != slope1) {
							Point[] collinearPoints = new Point[k - j + 1];
							collinearPoints[0] = originPoint;
							for (int t = 1; t < k - j + 1; t++){
								collinearPoints[t] = inputPoints[j + t - 1];
							}
							boolean noRepetition = true;
							for(Point usedPoint : colliearFirstPointCollection ) {
								if (usedPoint.slopeTo(collinearPoints[0]) == slope1) {
									j = k;
						     		noRepetition = false;
								    break;
								}
							}
							if (!noRepetition) {
								break;
							}
							Arrays.sort(collinearPoints);
							for (int w = 0; w < k - j + 1; w++) {
								StdOut.print(collinearPoints[w]);
								if (w != k - j)
									StdOut.print(" -> ");								
							}
							StdOut.println("");
							collinearPoints[0].drawTo(collinearPoints[k - j]);
							//for (int v = 1; v < k - j + 1; v++) {
								//swap(inputPoints, i + v, j + v -1);
							//}
							//i += k - j;
							if (k - j >= 3) {
								colliearFirstPointCollection.add(originPoint);
							}
							j = k;
							break;
						}
						else {
							k++;
						}
					}
				}				    
			}
		}
		
		
		
		StdDraw.show(0);
	}*/
	private static void sort(Point[] a, int lo, int hi, Point firstPoint) {
		//Point firstPoint = a[lo];
		Comparator<Point> slopeOrder = firstPoint.SLOPE_ORDER;
		if (hi <= lo) return;
		int lt = lo, i = lo + 1, gt = hi;
		Point v = a[lo];
		while (i <= gt) {
			int cmp = slopeOrder.compare(a[i], v);
			if (cmp < 0) swap(a, lt++, i++);
			else if (cmp > 0) swap(a, i, gt--);
			else i++;
		}
		int sameSlopeNumber = i - lt;
		if (sameSlopeNumber >= 4) addInOrNot = true;
		if (sameSlopeNumber >= 3) {			
			Point[] collinearPoints = new Point[sameSlopeNumber + 1];
			collinearPoints[0] = firstPoint;
			boolean noRepetition = true;
			for(Point usedPoint : colliearFirstPointCollection ) {
				if (usedPoint.slopeTo(firstPoint) == a[lt].slopeTo(firstPoint)) {
					noRepetition = false;
				    break;
				}
			}
				
			if (noRepetition) {
				for (int t = 1; t <= sameSlopeNumber; t++) {
					collinearPoints[t] = a[lt + t -1];
				}
				Arrays.sort(collinearPoints);
				for (int w = 0; w < sameSlopeNumber + 1; w++) {
					StdOut.print(collinearPoints[w]);
					if (w != sameSlopeNumber)
						StdOut.print(" -> ");								
				}
				StdOut.println("");
				collinearPoints[0].drawTo(collinearPoints[sameSlopeNumber]);
			}					
		}
		sort(a, lo, lt-1, firstPoint);
		sort(a, gt + 1, hi, firstPoint);
	}
		
		
	
	private static void swap(Point[] a, int low, int high) {
		Point tem = a[low];
		a[low] = a[high];
		a[high] = tem;
	}
}
