package motionProfilling;

public class MathPosition {
	@Override
	public String toString() {
		return "MathPosition [s=" + s + ", curvature=" + curvature + ", x=" + x + ", dXds=" + dXds + ", d2Xds2="
				+ d2Xds2 + ", y=" + y + ", dYds=" + dYds + ", d2Yds2=" + d2Yds2 + "]";
	}
	public double s;
	public double curvature;
	public double x;
	public double dXds;
	public double d2Xds2;
	public double y;
	public double dYds;
	public double d2Yds2;	
	
}
