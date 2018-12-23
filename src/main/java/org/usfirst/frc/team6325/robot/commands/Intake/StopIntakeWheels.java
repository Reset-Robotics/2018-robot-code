package org.usfirst.frc.team6325.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6325.robot.Robot;


public class StopIntakeWheels extends Command 
{
	public StopIntakeWheels() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intake);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		Robot.intake.setIntakePower(0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return false;
	}
}
