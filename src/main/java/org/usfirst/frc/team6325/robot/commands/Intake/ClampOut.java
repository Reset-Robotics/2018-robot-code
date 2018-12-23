package org.usfirst.frc.team6325.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6325.robot.Robot;


/**
 *
 */
public class ClampOut extends Command 
{
	public ClampOut() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intake);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		
		Robot.intake.clampOut();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return true;
	}
}
