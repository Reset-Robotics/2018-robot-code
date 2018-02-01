package org.usfirst.frc.team6325.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6325.robot.Robot;


/**
 *
 */
public class TankJoystickDrive extends Command {
	public TankJoystickDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double left = Robot.oi.joyDriver.getX();
		double right = Robot.oi.joyDriver.getY();
		Robot.drivetrain.drive(left, right);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.killMotors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	Robot.drivetrain.killMotors();
	}
}
