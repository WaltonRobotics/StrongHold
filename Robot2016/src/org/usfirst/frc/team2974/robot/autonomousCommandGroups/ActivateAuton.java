package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.AutonLocator;
import org.usfirst.frc.team2974.robot.AutonPossibleLocation;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.autonomousCommands.AimOnboard;
import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMC;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDown;
import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.MoveToObstacle;
import org.usfirst.frc.team2974.robot.autonomousCommands.Shoot;
import org.usfirst.frc.team2974.robot.autonomousCommands.Turn180DeadRecon;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ActivateAuton extends CommandGroup {
	CommandGroup obstacleCommand;
	AutonLocator location;
	//double back;
	public ActivateAuton(CommandGroup obstacleCommand, AutonLocator location) {
		Robot.compass.zeroRobot();
		this.obstacleCommand = obstacleCommand;
		this.location = location;
		runAuton();
	}
	public void runAuton(){
		double distanceToOuterWorks = -1.1;
		double speed = 0.7;
		double acceleration = 0.7;
		
		addSequential(new DriveStraightMC(distanceToOuterWorks, speed, acceleration));//testing instead of move to obstical
		
		addSequential(new ArmDown());
		
		if (location.getAutonPossibleLocation().equals(AutonPossibleLocation.A)){
			addSequential(new LowBar());
			addSequential(new ArmDown());
			addSequential(new Turn180DeadRecon());
		}else{
			addSequential(obstacleCommand);
			addSequential(new TurnToAngle(180));
			addSequential(new ArmDown());
		}
		//back = Robot.compass.getYaw();
		addSequential(new MoveToShoot(location));
		addSequential(new IntakeOut());	
		addParallel(new FlapDownTime(4));
		addSequential(new ArmDown());
		addSequential(new AimOnboard(0));
		addSequential(new AimOnboard(0));
		addSequential(new AimOnboard(0));
		addSequential(new Shoot());
		//addSequential(new Back(back));
	}
}
