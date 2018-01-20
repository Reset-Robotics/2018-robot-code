package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	WPI_TalonSRX leftIntakeWheel = new WPI_TalonSRX(RobotMap.leftIntakeWheel);
	WPI_TalonSRX rightIntakeWheel = new WPI_TalonSRX(RobotMap.rightIntakeWheel);
	

	public void setIntakePower(double power) {
		leftIntakeWheel.set(power);
		rightIntakeWheel.set(-power);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
