package motionProfilling;

/**
 * @author piyush
 * An xy coordinate
 */
public class Coordinate {

	/**
	 * X coordinate on a Cartesian plate
	 */
	private double x;
	/**
	 * X coordinate on a Cartesian plate
	 */
	private double y;

	/**
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x x coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}


}
