package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
	WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.leftFrontWheel);
	WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.leftBackWheel);
	WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rightFrontWheel);
	WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.rightBackWheel);
	
	public Lift() {
		this.leftSlave.follow(leftMaster);
		this.rightSlave.follow(rightMaster);
		this.rightMaster.setInverted(true);
		this.rightSlave.setInverted(true);
	}
	
	public void moveLift(double rightSide, double leftSide) {
		rightMaster.set(rightSide);
		leftMaster.set(leftSide);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
