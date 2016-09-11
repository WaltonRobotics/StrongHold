package org.usfirst.frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Shoot;

/**
 *
 */
public class Shooter extends Subsystem {
    private final Solenoid latch = RobotMap.latch;
    private final CANTalon tensioner = RobotMap.tensioner;
    private final double maxTensionerPower = 0.450;
    private final DigitalInput forwardLimitSwitch = RobotMap.forwardLimit;

    // private final double ForwardThreshold = 1000;
    // private final double ReverseThreshold = 300;
    // private final double ForwardLimit = 135000;
    // private final double ReverseLimit = 1300;
    private final DigitalInput reverseLimitSwitch = RobotMap.backwardLimit;
    private final DigitalInput shooterLimitSwitch = RobotMap.shooterLimit;
    private TensionerState state;

    public Shooter() {
        state = TensionerState.untensioned;
        tensioner.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        tensioner.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

        tensioner.reset();
        tensioner.enable();
    }

    public void dumpSmartDashboardValues() {
        SmartDashboard.putNumber("Tensioner Encoder", Robot.shooter.getTensionerValue());
        SmartDashboard.putNumber("Raw tensioner encoder value", tensioner.getAnalogInPosition());
    }

    public TensionerState getState() {
        // if (getTensionerValue() > ForwardLimit - ForwardThreshold ||
        // isForwardLimit())
        // state = TensionerState.tensioned;
        // else if (getTensionerValue() < ReverseLimit + ReverseThreshold ||
        // isReverseLimit())
        // state = TensionerState.untensioned;
        if (isForwardLimit())
            state = TensionerState.tensioned;
        else if (isReverseLimit())
            state = TensionerState.untensioned;
        return state;
    }

    private double getTensionerValue() {
        return tensioner.getEncPosition();

    }

    public void holdTension() {
        double holdTensionerPower = .030;
        setTensionerPower(holdTensionerPower);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Shoot());
    }

    private boolean isForwardLimit() {
        return forwardLimitSwitch.get();
    }

    private boolean isReverseLimit() {
        return reverseLimitSwitch.get();
    }

    public boolean isShooterDown() {
        return !shooterLimitSwitch.get();
    }

    public void latch() {
        latch.set(false);
    }

    private void setTensionerPower(double power) {
        if (power < 0 && isReverseLimit()) {
            setZero();
            // tensioner.setEncPosition(1000);
            boolean isInit = true;
        } else if (power > 0 && isForwardLimit()) {
            setZero();

        } else {
            tensioner.set(power);
            SmartDashboard.putString("im going to power", "" + power);
        }

        // if(isForwardLimit())
        // tensioner.setEncPosition(130000);
        // else if(isReverseLimit())
        // tensioner.setEncPosition(1000);

    }

    public void setZero() {
        tensioner.set(0);
    }

    public void tension() {
        setTensionerPower(maxTensionerPower);
        state = TensionerState.tensioning;
    }

    public void unlatch() {
        latch.set(true);
    }

    public void unTension() {
        setTensionerPower(-maxTensionerPower);
        state = TensionerState.untensioning;
    }

    public enum TensionerState {
        tensioned, untensioned, tensioning, untensioning
    }

}
