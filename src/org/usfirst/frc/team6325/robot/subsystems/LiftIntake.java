package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftIntake extends Subsystem {
	Spark liftWheel = new Spark(RobotMap.liftWheel);
	Spark backBelts = new Spark(RobotMap.backBelts);
	
	public LiftIntake () {
	
	}
	
	public void spinLiftIntake (double pow) {
		liftWheel.set(pow);
	}
	
	public void moveBackBelts (double pow) {
		liftWheel.set(pow);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
