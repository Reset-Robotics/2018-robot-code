package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
	WPI_TalonSRX left1 = new WPI_TalonSRX(RobotMap.leftFrontWheel);
	WPI_TalonSRX left2 = new WPI_TalonSRX(RobotMap.leftBackWheel);
	WPI_TalonSRX right1 = new WPI_TalonSRX(RobotMap.rightFrontWheel);
	WPI_TalonSRX right2 = new WPI_TalonSRX(RobotMap.rightBackWheel);
	SpeedControllerGroup leftLift = new SpeedControllerGroup(left1, left2);
	SpeedControllerGroup rightLift = new SpeedControllerGroup(right1, right2);
	
	public void moveLift(double rightSide, double leftSide) {
		rightLift.set(rightSide);
		leftLift.set(leftSide);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
