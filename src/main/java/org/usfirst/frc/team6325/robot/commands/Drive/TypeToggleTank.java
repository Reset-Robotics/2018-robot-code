package org.usfirst.frc.team6325.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6325.robot.Robot;


public class TypeToggleTank extends Command 
{
	public TypeToggleTank() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		Robot.oi.selectedDrivetrain = "TankJoystickDrive";
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return true;
	}
}