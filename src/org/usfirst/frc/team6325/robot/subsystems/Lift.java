package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;
import org.usfirst.frc.team6325.robot.commands.JoystickLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
		this.leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		this.rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		// Set Talon mode
		this.leftMaster.setNeutralMode(NeutralMode.Brake);
		this.rightMaster.setNeutralMode(NeutralMode.Brake);
		configPIDF(0,0,0,0); // TUNE VALUES
	}
	
	public void moveLift(double rightSide, double leftSide) {
		rightMaster.set(rightSide);
		leftMaster.set(leftSide);
	}
	public void moveToPos(int pos) {
		leftMaster.set(ControlMode.Position, pos);
		rightMaster.set(ControlMode.Position, pos);
		
	}
	public void moveToPositionMotionMagic(int pos) {
        leftMaster.set(ControlMode.MotionMagic, pos);
        rightMaster.set(ControlMode.MotionMagic, pos);
    }
	public void configMotionMagic(int cruiseVelocity, int acceleration) {
		
        leftMaster.configMotionCruiseVelocity(cruiseVelocity, 0);
        leftMaster.configMotionAcceleration(acceleration, 0);
        rightMaster.configMotionCruiseVelocity(cruiseVelocity, 0);
        rightMaster.configMotionAcceleration(acceleration, 0);
    }
	 public void configPIDF(double kP, double kI, double kD, double kF) {
	        leftMaster.config_kP(0, kP, 0);
	        leftMaster.config_kI(0, kI, 0);
	        leftMaster.config_kD(0, kD, 0);
	        leftMaster.config_kF(0, kF, 0);
	        rightMaster.config_kP(0, kP, 0);
	        rightMaster.config_kI(0, kI, 0);
	        rightMaster.config_kD(0, kD, 0);
	        rightMaster.config_kF(0, kF, 0);
	    }
	public int getLeftEncPos() {
	        return leftMaster.getSelectedSensorPosition(0);
	    }
	public int getRightEncPos() {
	        return rightMaster.getSelectedSensorPosition(0);
	    }
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickLift());
		
	}

}
