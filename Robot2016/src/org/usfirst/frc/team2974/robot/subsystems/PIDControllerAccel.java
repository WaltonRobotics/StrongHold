package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class PIDControllerAccel extends PIDController {
    private double kV;
    private double kA;
    private double currentVelocity;

    private double currentAcceleration;

    public PIDControllerAccel(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output, double kV,
                              double kA) {
        super(Kp, Ki, Kd, Kf, source, output, .01);

        this.kV = kV;
        this.kA = kA;

    }

    @Override
    protected double calculateFeedForward() {
        return currentVelocity * kV + currentAcceleration * kA;
    }

    public synchronized void setkA(double kA) {
        this.kA = kA;
    }

    public synchronized void setkV(double kV) {
        this.kV = kV;
    }

    public synchronized void setSetpoint(double distance, double velocity, double acceleration) {
        setSetpoint(distance);
        currentVelocity = velocity;
        currentAcceleration = acceleration;
    }
}
