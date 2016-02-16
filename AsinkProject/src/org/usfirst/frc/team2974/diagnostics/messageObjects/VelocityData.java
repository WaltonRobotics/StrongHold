package org.usfirst.frc.team2974.diagnostics.messageObjects;

public class VelocityData {
	double predictedLeftVelocity;
	double predictedRightVelocity;
	double trueLeftVelocity;
	double trueRightVelocity;

	public VelocityData(double predictedLeftVelocity, double predictedRightVelocity, double trueLeftVelocity, double trueRightVelocity) {
		this.predictedLeftVelocity = predictedLeftVelocity;
		this.predictedRightVelocity = predictedRightVelocity;
		this.trueLeftVelocity = trueLeftVelocity;
		this.trueRightVelocity = trueRightVelocity;
	}

	public double getPredictedLeftVelocity() {
		return this.predictedLeftVelocity;
	}
	public double getPredictedRightVelocity() {
		return this.predictedRightVelocity;
	}
	public double getTrueLeftVelocity() {
		return this.trueLeftVelocity;
	}
	public double getTrueRightVelocity() {
		return this.trueRightVelocity;
	}
	public void setPredictedLeftVelocity(double predictedLeftVelocity) {
		this.predictedLeftVelocity = predictedLeftVelocity;
	}
	public void setPredictedRightVelocity(double predictedRightVelocity) {
		this.predictedRightVelocity = predictedRightVelocity;
	}
	public void setTrueLeftVelocity(double trueLeftVelocity) {
		this.trueLeftVelocity = trueLeftVelocity;
	}
	public void setTrueRightVelocity(double trueRightVelocity) {
		this.trueRightVelocity = trueRightVelocity;
	}
}