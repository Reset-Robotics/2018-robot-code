package org.usfirst.frc.team6325.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6325.robot.Robot;


public class LiftIntake extends Command 
{
	double pow;
	public LiftIntake(double pow) 
	{
		// Use requires() here to declare subsystem dependencies
		this.pow = pow;
		requires(Robot.liftIntake);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		Robot.liftIntake.spinLiftIntake(pow); 
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() 
	{
		Robot.liftIntake.spinLiftIntake(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() 
	{
		end();
	}
}