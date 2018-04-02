package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	Spark leftIntakeWheel = new Spark(RobotMap.leftIntakeWheel);
	Spark rightIntakeWheel = new Spark (RobotMap.rightIntakeWheel);
	private DoubleSolenoid intakePiston = new DoubleSolenoid(RobotMap.INTAKE[0], RobotMap.INTAKE[1]);
	
	public Intake() {
		//intakePiston.set(Value.kForward);
	}

	public void setIntakePower(double power) {
		leftIntakeWheel.set(power);
		rightIntakeWheel.set(power);
	}
	public void clampIn() {
		intakePiston.set(Value.kForward);
	}
	public void clampOut() {
		intakePiston.set(Value.kReverse);
	}
	public void toggleClamp() {
		if(intakePiston.get()==Value.kForward) {
			intakePiston.set(Value.kReverse);
		}
		else {
			intakePiston.set(Value.kForward);
		}
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
