package org.usfirst.frc.team6325.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6325.robot.Robot;


/**
 *
 */
public class BangBang extends Command {
	double setpoint;
	public BangBang(int ticks) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
		this.setpoint = ticks;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(Robot.lift.getQuadPos(0) < setpoint) {
			Robot.lift.moveLift(0.7);
		}
		else 
			Robot.lift.moveLift(-0.7);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(Math.abs(setpoint - Robot.lift.getQuadPos(0)) < 1024) {
            Robot.lift.moveLift(0);
            return true;
        }
        return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.lift.moveLift(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		
	}
}
