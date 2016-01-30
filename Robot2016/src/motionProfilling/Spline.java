package motionProfilling;

public class Spline {
	private State start;
	private State end;
	private Coordinate point1;
	private Coordinate point2;
	private TrajectorySpline trajectory;
	
	public Spline(State start, State end) {
		this.start = start;
		this.end = end;
		choosePoints();
		dumpValues();
		trajectory = new TrajectorySpline();
		generatePositions();
		dumpPositions();
	}
	public TrajectorySpline getTrajectory()
	{
		return trajectory;
	}
	public void dumpValues()
	{
		System.out.println("line1 "+new Line(start)+", line2 "+new Line(end));
		System.out.println(toString());
		
	}
	private void generatePositions() {
		double deltaS = 1.0/(MotionControl.numPoints-1);
		for (int i=0; i<MotionControl.numPoints; i++) {
			double s = deltaS*i;
			MathPosition p = new MathPosition();
			p.s = s;
			p.curvature =getK(s);
			p.dYds = getdYds(s);
			p.dXds = getdXds(s);
			p.d2Yds2 = getAY(s);
			p.d2Xds2 = getAX(s) ;
			p.x = getX(s);
			p.y = getY(s);
			trajectory.add(p);
		}
	}
	public void dumpPositions()
	{
		for(MathPosition mp: trajectory)
		{
			System.out.println(mp);
		}
	}
	@Override
	public String toString() {
		return "Spline [start=" + start + ", end=" + end + ", point1=" + point1 + ", point2=" + point2 + "]";
	}
	private void choosePoints() {
		Line line1 = new Line(start);
		Line line2 = new Line(end);
		Coordinate intersection = getIntersection(line1, line2);

		double x1 = (intersection.getX() - start.getX()) * 2.0 / 3 + start.getX();// x
																					// point
																					// 2/3
																					// of
																					// way
																					// from
																					// start
																					// to
																					// intersection
		point1 = new Coordinate(x1, line1.getY(x1));

		double x2 = (end.getX() - intersection.getX()) * 2.0 / 3 + intersection.getX();// x
																						// point
																						// 2/3
																						// of
																						// way
																						// from
																						// intersection
																						// to
																						// end
		point2 = new Coordinate(x2, line2.getY(x2));

	}

	private Coordinate getIntersection(Line line1, Line line2) {
		double x = (line2.getB() - line1.getB()) / (line1.getM() - line2.getM());
		double y = line1.getM() * x + line1.getB();
		return new Coordinate(x, y);
	}
	private double A(double s, double x)
	{
		return (1 - s) * (1 - s) * (1 - s) * x;
	}
	private double B(double s, double x)
	{
		return 3 * (1 - s) * (1 - s) * s * x;
	}
	private double C(double s, double x)
	{
		return 3 * (1 - s) * s * s * x;
	}
	private double D(double s, double x)
	{
		return s * s * s * x;
	}
	private double getX(double s) {// bezier curve stuffs
		double a = A(s,start.getX());
		double b = B(s,point1.getX());
		double c = C(s, point2.getX());
		double d = D(s,end.getX());
		return a + b + c + d;
	}

	private double getY(double s) {// bezier curve stuffs
		double a = A(s,start.getY());
		double b = B(s,point1.getY());
		double c = C(s, point2.getY());
		double d = D(s,end.getY());
		return a + b + c + d;
	}
	private double dAds(double s, double x)
	{
		return -3 * (1 - s) * (1 - s) * x;
	}
	private double dBds(double s, double x)
	{
		return (3 * (1 - s) * (1 - s) - 6 * s * (1 - s)) * x;
	}
	private double dCds(double s, double x)
	{
		return (6 * (1 - s) * s - 3 * s * s) * x;
	}
	private double dDds(double s, double x)
	{
		return 3 * s * s * x;
	}
	private double getdXds(double s) {
		double a = dAds(s, start.getX());
		double b = dBds(s, point1.getX());
		double c = dCds(s, point2.getX());
		double d = dDds(s, end.getX());
		return a + b + c + d;
	}

	private double getdYds(double s) {
		double a = dAds(s, start.getX());
		double b = dBds(s, point1.getX());
		double c = dCds(s, point2.getX());
		double d = dDds(s, end.getX());
		return a + b + c + d;
	}
	private double d2Ads2(double s, double x)
	{
		return 6 * (1 - s) * x;
	}
	private double d2Bds2(double s, double x)
	{
		return (-6 * (1 - s) + 6 * s - 6 * (1 - s)) * x;
	}
	private double d2Cds2(double s, double x)
	{
		return (-6 * s + 6 * (1 - s) - 6 * s) *x;
	}
	private double d2Dds2(double s, double x)
	{
		return 6 * s * x;
	}
	private double getAX(double s) {
		double a = d2Ads2(s, start.getX());
		double b = d2Bds2(s, point1.getX());
		double c = d2Cds2(s, point2.getX());
		double d = d2Dds2(s, end.getX());
		return a + b + c + d;
	}

	private double getAY(double s) {
		double a = d2Ads2(s, start.getX());
		double b = d2Bds2(s, point1.getX());
		double c = d2Cds2(s, point2.getX());
		double d = d2Dds2(s, end.getX());
		return a + b + c + d;
	}

	private double getK(double s) {
		double numerator = getdXds(s) * getAY(s) - getdYds(s) * getAX(s);
		double denominator = getdXds(s) * getdXds(s) + getdYds(s) * getdYds(s);
		return numerator / Math.pow(denominator, 1.5);
	}

}

class Line {
	/*
	 * An equation of a line is like:
	 * 
	 * m*x + n = y m can be calculated by angle; m = tan(angle) And if you know
	 * a start point then you can find n.
	 * 
	 * tan(angle) * startPoint_X + n = startPoint_Y So b = startPoint_Y - (tan (
	 * angle) * startPoint_X )
	 */
	private double m;
	private double b;

	public Line(State state1) {
		m = Math.tan(Math.toRadians(state1.getDirection()));
		b = state1.getY() - m * state1.getX();
	}

	public double getM() {
		return m;
	}

	public double getB() {
		return b;
	}

	public double getY(double x) {
		return m * x + b;
	}

	@Override
	public String toString() {
		return "Line [m=" + m + ", b=" + b + "]";
	}
}
