package org.usfirst.frc.team2974.robot.commands;



/**
 *
 */
//public class IntakeBall extends Command {
//private IntakeRollerState currentState;
//    public IntakeBall() {
//        requires(Robot.intake);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	currentState = IntakeRollerState.stop;
//    }
//    public enum IntakeRollerState{
//    	in, out, stop
//    }
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
////    	if(Robot.oi.outtake.get()){
////			currentState = IntakeRollerState.out;
////		}
////		else if(Robot.oi.stoptake.get()){
////			currentState = IntakeRollerState.stop;
////		}
////		else if(Robot.oi.intake.get()){
////			currentState = IntakeRollerState.in;
////		}
//    	
////    	switch(currentState){
////    	case in:
////    		Robot.intake.input();
////    		break;
////    	case out:
////    		Robot.intake.output();
////    		break;
////    	case stop:
////    		Robot.intake.stop();
////    		break;
////    	}
//
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//    }
