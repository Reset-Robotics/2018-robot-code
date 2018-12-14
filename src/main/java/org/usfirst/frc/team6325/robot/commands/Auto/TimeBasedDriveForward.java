package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;

public class TimeBasedDriveForward extends Command 
{
	double time = 0.0;
	double start = 0.0, currentTime = 0.0, leftPower = 0.0, rightPower = 0.0;

    public TimeBasedDriveForward(double leftPower, double rightPower, double time) 
    {
		requires(Robot.drivetrain);
		this.leftPower = leftPower;
		this.rightPower = rightPower;
		this.time = time;
	}
	
    protected void initialize() 
    {
		start = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	 currentTime = System.currentTimeMillis();
    	 Robot.drivetrain.drive(leftPower, rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return (currentTime>=start+time);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.drivetrain.killMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
