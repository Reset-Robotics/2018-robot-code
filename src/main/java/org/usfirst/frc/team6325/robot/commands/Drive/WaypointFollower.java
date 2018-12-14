package org.usfirst.frc.team6325.robot.commands.Drive;

import org.usfirst.frc.team6325.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;


public class WaypointFollower extends Command 
{
	Waypoint[] path;
	EncoderFollower[] followers;
	public WaypointFollower(Waypoint[] path) 
	{
		 this.path = path;
	     setInterruptible(false);
	     followers = Robot.drivetrain.initPath(path);
	}
	@Override
	protected void initialize() 
	{
		Robot.drivetrain.resetForPath();
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