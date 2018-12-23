package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.subsystems.Drivetrain.MotionProfiling;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;


public class DriveForward extends Command 
{
	public static double turnSpeed;
    private double moveLeftSpeed, moveRightSpeed, distance, angle;
    private PIDController moveLeftController, moveRightController, angleController;
    
    public DriveForward(double dist) 
    {
        distance = dist;
    }

    private double ticksToInches(double ticks) 
    {
        return (ticks / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference;
    }

    private double inchesToTicks(double inches) 
    {
        return (inches / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference;
    }
    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	double targetTicks = inchesToTicks(distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }
}
