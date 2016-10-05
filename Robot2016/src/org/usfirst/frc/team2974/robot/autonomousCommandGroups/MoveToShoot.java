package org.usfirst.frc.team2974.robot.autonomousCommandGroups;

import org.usfirst.frc.team2974.robot.AutonLocator;
//import org.usfirst.frc.team2974.robot.autonomousCommands.ArmDown;
//import org.usfirst.frc.team2974.robot.autonomousCommands.DriveDistance;
//import org.usfirst.frc.team2974.robot.autonomousCommands.DriveLocate;
import org.usfirst.frc.team2974.robot.autonomousCommands.DriveStraightMC;
//import org.usfirst.frc.team2974.robot.autonomousCommands.FlapDownTime;
//import org.usfirst.frc.team2974.robot.autonomousCommands.IntakeOut;
import org.usfirst.frc.team2974.robot.autonomousCommands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveToShoot extends CommandGroup {
	public MoveToShoot(AutonLocator location){
		//addSequential(new DriveLocate());
		//addSequential(new TurnToAngle(location.getAngle(location.getLocation(), location.getLocationShoot())));
		//addSequential(new DriveDistance(speed,location.getDistance(location.getLocation(), location.getLoca0tionShoot())));
		addSequential(new DriveStraightMC(location.getLocationDistance().getY(), location.getLocationDistance().getX(), 0.3));
		addSequential(new TurnToAngle(location.getAngleDead()));
	}
}
