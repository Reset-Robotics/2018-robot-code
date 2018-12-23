
package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngleStupid extends Command 
{
	double ang = 0.0;
	double diff = 0.0;
	boolean done = false;
	
	public TurnToAngleStupid(double angle) 
	{
		requires(Robot.drivetrain);
		ang = angle;
	}
	
	protected void initialize() 
	{
		diff = ang - Robot.drivetrain.getAngle();
		
		if(diff > 0)
			Robot.drivetrain.drive(0.3, -0.3);
		else
			Robot.drivetrain.drive(-0.3, 0.3);
    }

    // Called repeatedly when this Command is scheduled to run
	protected void execute() 
	{
		if(diff > 0) 
		{
    		if(Robot.drivetrain.getAngle() > ang)
    			done = true;
		} 
		else 
		{
    		if(Robot.drivetrain.getAngle() < ang)
    			done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
        return done;
    }

    // Called once after isFinished returns true
	protected void end() 
	{
    	Robot.drivetrain.autoTurnStop();
    	Robot.drivetrain.killMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
	protected void interrupted() 
	{
    	end();
    }
}
