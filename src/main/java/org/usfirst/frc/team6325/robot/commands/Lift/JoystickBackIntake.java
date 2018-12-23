package org.usfirst.frc.team6325.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6325.robot.Robot;


public class JoystickBackIntake extends Command 
{
	
	public JoystickBackIntake() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.backBelts);
	
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.backBelts.moveBackBelts(-Robot.oi.xboxController.getRawAxis(5));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return false;
	}
}
