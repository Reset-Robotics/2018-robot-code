package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	WPI_VictorSPX leftIntakeWheel = new WPI_VictorSPX(RobotMap.leftIntakeWheel);
	WPI_VictorSPX rightIntakeWheel = new WPI_VictorSPX(RobotMap.rightIntakeWheel);
	

	public void setIntakePower(double power) {
		leftIntakeWheel.set(power);
		rightIntakeWheel.set(-power);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
