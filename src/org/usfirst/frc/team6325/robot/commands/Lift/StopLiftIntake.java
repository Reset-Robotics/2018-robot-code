package org.usfirst.frc.team6325.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6325.robot.Robot;


/**
 *
 */
public class StopLiftIntake extends Command {
	public StopLiftIntake() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.liftIntake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.liftIntake.spinLiftIntake(0, 0); 
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		
	}
}
