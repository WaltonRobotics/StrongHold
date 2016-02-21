package motionProfilling;


/**
 *
 * Mathematical position
 * contains the value of the x,y,x',y',x'',y'',and the curvature
 */
public class MathPosition {
	public MathPosition(double s, double k, double x, double y, double dXds, double dYds, double d2Xds2, double d2Yds2)
	{
		this.s = s;
		this.curvature = k;
		this.x = x;
		this.y = y;
		this.dXds = dXds;
		this.dYds = dYds;
		this.d2Xds2 = d2Xds2;
		this.d2Yds2 = d2Yds2;
	}
	@Override
	public String toString() {
		return "MathPosition [s=" + s + ", curvature=" + curvature + ", x=" + x + ", dXds=" + dXds + ", d2Xds2="
				+ d2Xds2 + ", y=" + y + ", dYds=" + dYds + ", d2Yds2=" + d2Yds2 + "]";
	}
	/**
	 * The s value of this position
	 */
	public final double s;
	/**
	 * The curvature
	 */
	public final double curvature;
	/**
	 * The x value 
	 */
	public final double x;
	/**
	 * The first derivative of x
	 */
	public final double dXds;
	/**
	 * second derivative of x
	 */
	public final double d2Xds2;
	/**
	 * The y value
	 */
	public final double y;
	/**
	 * The first derivative of y
	 */
	public final double dYds;
	/**
	 * The second derivative of y
	 */
	public final double d2Yds2;	
	
}
