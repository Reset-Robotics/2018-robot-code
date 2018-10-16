package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;
import org.usfirst.frc.team6325.robot.commands.Lift.JoystickBackIntake;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BackBelts extends Subsystem {
	Spark backBelts = new Spark(RobotMap.backBelts);

	public void moveBackBelts (double pow) {
		backBelts.set(pow);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new JoystickBackIntake());
		
	}
}
