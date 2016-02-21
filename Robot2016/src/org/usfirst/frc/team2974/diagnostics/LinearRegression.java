package org.usfirst.frc.team2974.diagnostics;

import java.util.ArrayList;

public class LinearRegression {
	double a;
	double b;
	double r;
	double Qa;
	double Qb;
	double Qc;
	
	/**
	 * The math didn't work, but here's a new formula that might:
	 * m = r * standard deviation of y / standard deviation of x
	 * where r is the correlation coeficcient
	 * 
	 * the intercept (b)
	 * b = avgY - m(avgX)
	 * 
	 * standard deviation = (sum(x-avgX)^2)/n
	 * 
	 * correlation coefficient = sum(errorX * errorY)/(sqrt(sum(errorX^2) * sum(errorY^2)))
	 * where errorX = x - avgX
	 * and errorY = y - avgY
	 */

	ArrayList<PointValue> dataPoints = new ArrayList<PointValue>();

	public void addPoint(double x, double y) {
		dataPoints.add(new PointValue(x, y));
	}

	public void removePoint(double x, double y) {
		dataPoints.remove(new PointValue(x, y));
	}

	public void computeRegesion() {
		double sumX = 0, sumY = 0, avgX, avgY,
				sumErrorXY = 0, sumErrorXX = 0, sumErrorYY = 0, sx, sy;
		for (PointValue currentValue : dataPoints) {
			sumX += currentValue.getX();
			sumY += currentValue.getY();
		}
		avgX = sumX / dataPoints.size();
		avgY = sumY / dataPoints.size();
		for (PointValue currentValue : dataPoints) {
			sumErrorXY += ((avgX - currentValue.getX()) * (avgY - currentValue.getY()));
			sumErrorXX += ((avgX - currentValue.getX()) * (avgX - currentValue.getX()));
			sumErrorYY += ((avgY - currentValue.getY()) * (avgY - currentValue.getY()));
		}
		r = sumErrorXY / Math.sqrt(sumErrorXX * sumErrorYY);
		sx = Math.sqrt(sumErrorXX/ dataPoints.size());
		sy = Math.sqrt(sumErrorYY/ dataPoints.size());
		a = r * sy / sx;
		b = avgY - (a * avgX);
//		Qa = a * a;
//		Qb = 2 * a * b;
//		Qc = b * b;
		
		//System.out.println("sumX = " + sumX + ", sumY = " + sumY+ ", avgX = " +avgX+ ", avgY = " +avgY+ ", sumErrorXY = " +sumErrorXY+ ", sumErrorXX = " +sumErrorXX+ ", sumErrorYY = " +sumErrorYY+ ", sx = " +sx+ ", sy =" +sy);
		
/*
 * 		double X = 0, XX = 0, Y = 0, XY = 0, avgX = 0, avgY = 0;
		for (PointValue currentValue : dataPoints) {
			X += currentValue.getX();
			Y += currentValue.getY();
			XX += currentValue.getX() * currentValue.getX();
			XY += currentValue.getY() * currentValue.getX();
		}

		b = (dataPoints.size() * XY - (X * Y)) / ((dataPoints.size() * XX) - (X * X));
		a = Y - b * X / dataPoints.size();
		avgY = Y / dataPoints.size();
		avgX = X / dataPoints.size();

		double deltaXX = 0, deltaYY = 0, deltaXY = 0;
		for (PointValue currentValue : dataPoints) {
			double localX, localY;
			localX = currentValue.getX() - avgX;
			localY = currentValue.getY() - avgY;
			deltaXX += localX * localX;
			deltaYY += localY * localY;
			deltaXY += localX * localY;
		}
		double r = deltaXY / Math.sqrt(deltaXX * deltaYY);
		rsquared = r * r;
 */
		
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getRsquared() {
		return r;
	}

	public void setRsquared(double r) {
		this.r = r;
	}
}
