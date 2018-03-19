package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveDistance extends Command {

	private double dist;
	private double spd;
	private boolean done = false;
	
	public MoveDistance(double meters, double speed) {
		dist = meters;
		spd = speed;
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.drive(spd, spd);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.drivetrain.getEncoderDistanceMetersLeft() >= dist) {
    		done = true;
    		Robot.drivetrain.killMotors();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.killMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.killMotors();
    }

}
