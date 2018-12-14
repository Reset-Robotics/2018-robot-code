package org.usfirst.frc.team6325.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6325.robot.Robot;


public class JoystickLift extends Command 
{
	public JoystickLift() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		Robot.lift.moveLift(-Robot.oi.xboxController.getRawAxis(1));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return false;
	}
}
