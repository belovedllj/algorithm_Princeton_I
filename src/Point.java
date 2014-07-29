import java.util.*;


public class Point implements Comparable<Point>{
	
	public final Comparator<Point> SLOPE_ORDER = new SlopeOrder(this);
	private final int x;
	private final int y;
		
	private static class SlopeOrder implements Comparator<Point> {
		private final Point p0;
		private SlopeOrder(Point p0){
			this.p0 = p0;
		}
		@Override
		public int compare(Point p1, Point p2) {
			if (p0.slopeTo(p1) < p0.slopeTo(p2))
				return -1;
			else if (p0.slopeTo(p1) > p0.slopeTo(p2))
				return 1;
			else 
				return 0;
		}		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		StdDraw.point(x, y);
	}
	
    public void drawTo(Point that) {
    	StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    public String toString() {
    	return "(" + x + ", " + y + ")";    	
    }    
    	
	@Override
	public int compareTo(Point that) {
		if (this.y < that.y)
			return -1;
		else if (this.y > that.y)
		    return 1;
		else if (this.x < that.x)
			return -1;
		else if (this.x > that.x)
			return 1;
		else
			return 0;
	}
	
	public double slopeTo(Point that) {
		if (this.y == that.y) {
			if (this.x == that.x)
			    return Double.NEGATIVE_INFINITY;
			else
				return 0;			
		}			
		else if (this.x == that.x)
			return Double.POSITIVE_INFINITY;
		
		return (double)(that.y - this.y) / (double)(that.x - this.x);			
	}
	

}
