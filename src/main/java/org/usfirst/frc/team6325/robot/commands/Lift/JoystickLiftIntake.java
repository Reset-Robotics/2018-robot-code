package org.usfirst.frc.team6325.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6325.robot.Robot;


public class JoystickLiftIntake extends Command 
{
	public JoystickLiftIntake() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.liftIntake);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		Robot.liftIntake.spinLiftIntake(Robot.oi.xboxController.getRawAxis(5));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return false;
	}
}
