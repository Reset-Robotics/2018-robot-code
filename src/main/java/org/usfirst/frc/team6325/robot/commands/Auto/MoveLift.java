package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class MoveLift extends Command 
{
	int height;
	boolean done = false;
	
	public MoveLift(int tallness) 
	{
		requires(Robot.lift);
		height = tallness;
	}
	
	protected void initialize() 
	{
		//set lift encoders to 0
		Robot.lift.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
	protected void execute() 
	{
    	System.err.println(Robot.lift.getQuadPos(0));
		Robot.lift.moveToPos(height);
		if (Robot.lift.getQuadPos(0) >= height - 50 && Robot.lift.getQuadPos(0) <= height + 50)
			done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
        return done;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
	protected void interrupted() 
	{
    	end();
    }
}
