
import java.util.Arrays;
//import java.util.Comparator;


public class Brute {

	/*Point rr = new Point(1, 2);
	Point aaa = new Point(3,4);
	Point bbb =  new Point(4,7);
	Comparator<Point> a = rr.SLOPE_ORDER;
	int tt = a.compare(aaa, bbb);*/
	
	
	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		//StdOut.println(9.0/7.0 == 90.0/70.0);
		int[] input = new In(args[0]).readAllInts();
		int inputNumber = input[0];
		Point[] inputPoints = new Point[inputNumber];
		for (int i = 0; i < inputNumber; i++) {
			inputPoints[i] = new Point(input[2 * i + 1], input[2 * i + 2]);
			inputPoints[i].draw();
		}
		for(int i = 0; i < inputNumber; i++) {
			//double slope = 0;
			Point originPoint = inputPoints[i];
			for (int j = i + 1; j < inputNumber; j++) {
				Point secondPoint = inputPoints[j];
				double slope1 = originPoint.slopeTo(secondPoint);
				for (int k = j + 1; k < inputNumber; k++) {
					Point thirdPoint = inputPoints[k];
					double slope2 = originPoint.slopeTo(thirdPoint);
					if (slope1 != slope2) continue;
					for (int t = k + 1; t< inputNumber; t++) {
						Point fourthPoint = inputPoints[t];
						double slope3 = originPoint.slopeTo(fourthPoint);
						if (slope1 == slope3) {
							Point[] collinearPoints = new Point[4];
							collinearPoints[0] = originPoint;
							collinearPoints[1] = secondPoint;
							collinearPoints[2] = thirdPoint;
							collinearPoints[3] = fourthPoint;
							Arrays.sort(collinearPoints);
							for (int w = 0; w < 4; w++) {
								StdOut.print(collinearPoints[w]);
								if (w != 3)
									StdOut.print(" -> ");								
							}
							StdOut.println("");
							collinearPoints[0].drawTo(collinearPoints[3]);							
						}
					}
				}
			}
		}
		StdDraw.show(0);
	}
}
