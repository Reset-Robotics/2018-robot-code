package org.usfirst.frc.team6325.robot.commands.Drive;

import org.usfirst.frc.team6325.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.followers.EncoderFollower;


public class ProfileFollower extends Command 
{
	String leftCSV, rightCSV;
	EncoderFollower[] followers;

	public ProfileFollower(String leftCSV, String rightCSV) 
	{
		leftCSV = this.leftCSV;
		rightCSV = this.rightCSV;
		setInterruptible(false);
		followers = Robot.drivetrain.initPath(leftCSV, rightCSV);
	}

	@Override
	protected void initialize() 
	{
		Robot.drivetrain.executePath(followers, false);
	}

	@Override
	protected void execute() 
	{
		Robot.drivetrain.executePath(followers, false);
	}

	@Override
	protected void end() 
	{
        Robot.drivetrain.drive(0, 0);
    }
	
	@Override
	protected void interrupted() 
	{
	    end();
	}

	@Override
	protected boolean isFinished() 
	{
		return Robot.drivetrain.getIsProfileFinished();
	}
}
