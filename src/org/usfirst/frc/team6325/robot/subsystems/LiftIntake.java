package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftIntake extends Subsystem {
	WPI_TalonSRX leftMasterWheel = new WPI_TalonSRX(RobotMap.leftFrontWheel);
	WPI_TalonSRX leftBackWheel = new WPI_TalonSRX(RobotMap.leftBackWheel);
	WPI_TalonSRX rightMasterWheel = new WPI_TalonSRX(RobotMap.rightFrontWheel);
	WPI_TalonSRX rightBackWheel = new WPI_TalonSRX(RobotMap.rightBackWheel);
	public LiftIntake () {
		this.leftBackWheel.follow(leftMasterWheel);
		this.rightBackWheel.follow(rightMasterWheel);
		this.rightMasterWheel.setInverted(true);
		this.rightBackWheel.setInverted(true);
	}
	
	public void spinLiftIntake (double leftSide, double rightSide) {
		leftMasterWheel.set(leftSide);
		rightMasterWheel.set(rightSide);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
