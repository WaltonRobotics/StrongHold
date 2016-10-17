package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.AutonPossibleLocation;
import org.usfirst.frc.team2974.robot.ObjBool;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.autonomousCommands.AimOnboard;
import org.usfirst.frc.team2974.robot.autonomousCommands.AimOnboardPrec;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraight;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMC;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeIn;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.MoveToObstacle;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.Turn180DeadRecon;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnLeft;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;
import org.usfirst.frc.team2974.robot.autonomousCommands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ActivateAuton extends CommandGroup {
	CommandGroup obstacleCommand;
	AutonLocator location;
	ObjBool doShoot;
	//double back;
	public ActivateAuton(CommandGroup obstacleCommand, AutonLocator location, ObjBool doShoot) {
		Robot.compass.zeroRobot(180);
		this.obstacleCommand = obstacleCommand;
		this.location = location;
		this.doShoot = doShoot;
		runAuton();
	}
	public void runAuton(){
		double distanceToOuterWorks = -1.1;
		double speed = 0.7;
		double acceleration = 0.7;
		
		Robot.compass.zeroRobot(180);
		
		addSequential(new IntakeIn());
		addSequential(new DriveStraightMC(distanceToOuterWorks, speed, acceleration));//testing instead of move to obstical
		
		
		if (location.getAutonPossibleLocation().equals(AutonPossibleLocation.A)){
			addSequential(new ArmDown());
			addSequential(new LowBar());
			
		}else{
			addSequential(obstacleCommand);
		}
		addSequential(new Turn180DeadRecon());
		
		addSequential(new MoveToShoot(location));
		if (!location.getAutonPossibleLocation().equals(AutonPossibleLocation.A)){
			addSequential(new ArmDown());//back = Robot.compass.getYaw();
		}
		addSequential(new IntakeOut());	
		addParallel(new FlapDownTime(6));
		AimOnboard.centerX = location.getCenter();
		AimOnboardPrec.centerX = location.getCenter();
		if(doShoot.getValue()){
			addSequential(new AimOnboard(0));
			addSequential(new AimOnboard(0));
			addSequential(new AimOnboard(0));
			addSequential(new AimOnboard(0));
		//	addSequential(new AimOnboardPrec(0));
		//	addSequential(new AimOnboardPrec(0));
			addSequential(new Wait(.5));
			addSequential(new Shoot());
			addSequential(new Wait(2));
		}
		//addSequential(new TurnToAngleAbsolute(back));
		//addSequential(new DriveStraightMC(-location.getLocationDistance().getY(), location.getLocationDistance().getX(), 0.3));
		//addSequential(obstacleCommand);
	}
}
