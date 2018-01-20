package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftIntake extends Subsystem {
	WPI_TalonSRX leftFrontWheel = new WPI_TalonSRX(RobotMap.leftFrontWheel);
	WPI_TalonSRX leftBackWheel = new WPI_TalonSRX(RobotMap.leftBackWheel);
	WPI_TalonSRX rightFrontWheel = new WPI_TalonSRX(RobotMap.rightFrontWheel);
	WPI_TalonSRX rightBackWheel = new WPI_TalonSRX(RobotMap.rightBackWheel);
	SpeedControllerGroup leftWheels = new SpeedControllerGroup(leftFrontWheel, leftBackWheel);
	SpeedControllerGroup rightWheels = new SpeedControllerGroup (rightFrontWheel, rightBackWheel);
	
	public void spinLiftIntake (double leftSide, double rightSide) {
		leftWheels.set(leftSide);
		rightWheels.set(rightSide);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
