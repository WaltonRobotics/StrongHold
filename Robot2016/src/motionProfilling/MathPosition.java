package motionProfilling;

/**
 * @author piyush
 * Mathematical position
 * contains the value of the x,y,x',y',x'',y'',and the curvature
 */
public class MathPosition {
	@Override
	public String toString() {
		return "MathPosition [s=" + s + ", curvature=" + curvature + ", x=" + x + ", dXds=" + dXds + ", d2Xds2="
				+ d2Xds2 + ", y=" + y + ", dYds=" + dYds + ", d2Yds2=" + d2Yds2 + "]";
	}
	/**
	 * The s value of this position
	 */
	public double s;
	/**
	 * The curvature
	 */
	public double curvature;
	/**
	 * The x value 
	 */
	public double x;
	/**
	 * The first derivative of x
	 */
	public double dXds;
	/**
	 * second derivative of x
	 */
	public double d2Xds2;
	/**
	 * The y value
	 */
	public double y;
	/**
	 * The first derivative of y
	 */
	public double dYds;
	/**
	 * The second derivative of y
	 */
	public double d2Yds2;	
	
}
