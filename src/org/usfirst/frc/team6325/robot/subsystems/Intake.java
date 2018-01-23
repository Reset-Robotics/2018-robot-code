package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	WPI_VictorSPX leftIntakeWheel = new WPI_VictorSPX(RobotMap.leftIntakeWheel);
	WPI_VictorSPX rightIntakeWheel = new WPI_VictorSPX(RobotMap.rightIntakeWheel);
	private DoubleSolenoid intakePistonL = new DoubleSolenoid(RobotMap.LEFT_INTAKE[0], RobotMap.LEFT_INTAKE[1]);
	private DoubleSolenoid intakePistonR = new DoubleSolenoid(RobotMap.RIGHT_INTAKE[0], RobotMap.RIGHT_INTAKE[1]);

	public void setIntakePower(double power) {
		leftIntakeWheel.set(power);
		rightIntakeWheel.set(-power);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
