package org.usfirst.frc.team6325.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6325.robot.ButtonSelection;
import org.usfirst.frc.team6325.robot.Robot;


public class TankJoystickDrive extends Command 
{
	public TankJoystickDrive() 
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		ButtonSelection btns = new ButtonSelection();

		//double sliderRawAxisNumber = Robot.oi.joystickLeft.getRawAxis(ids.joystickLeftIDs.get("SliderAxis"));
		double sliderModifier = 1 - ((Robot.oi.sliderRawAxisNumber + 1)/2);
		System.err.println("SliderModifer is " + sliderModifier);
		double left = Robot.oi.drivetrainLeft * sliderModifier;
		//System.err.println("left joystick output number is " + left);
		double right = Robot.oi.drivetrainRight * sliderModifier;
		//System.err.println("right joystick output number is " + right);
		Robot.drivetrain.drive(left, right);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() 
	{
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() 
	{
		Robot.drivetrain.killMotors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() 
	{
		Robot.drivetrain.killMotors();
	}
}
