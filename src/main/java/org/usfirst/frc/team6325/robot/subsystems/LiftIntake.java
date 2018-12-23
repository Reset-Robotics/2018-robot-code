package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;
import org.usfirst.frc.team6325.robot.commands.Lift.JoystickLiftIntake;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftIntake extends Subsystem 
{
	Spark liftWheel = new Spark(RobotMap.liftWheel);
	
	public void spinLiftIntake (double pow) 
	{
		liftWheel.set(pow);
	}

	@Override
	protected void initDefaultCommand() 
	{
		setDefaultCommand(new JoystickLiftIntake());
	}

}
