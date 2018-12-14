package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;

import org.usfirst.frc.team6325.robot.commands.Lift.JoystickLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem 
{
	public WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.leftMaster);
	public WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rightMaster);
	private static final int CRUISE_VELOCITY = 19000; // 1024
	private static final int CRUISE_ACCELERATION = 11000; // 1024
	private static final int CRUISE_VELOCITY_DOWN = (int) (CRUISE_VELOCITY * 0.7); // 1024
	private static final int CRUISE_ACCELERATION_DOWN = (int) (CRUISE_ACCELERATION * 0.6); // 1024
	
	public enum Positions 
	{
        Intake(300),
        Driving(25000),
        ScoreSwitch(14000),
        ScoreScale(20000),
        ScoreScaleLow(60000),
        ScoreScaleHigh(72000),
        ClimbingBar(67500),
		Top(73000);
        private int position;

		Positions(int encPos) 
		{
            this.position = encPos;
        }

		public int getPosition() 
		{
            return this.position;
        }
    }
	public Lift() 
	{
		this.leftMaster.setInverted(false);
	 	this.rightMaster.setInverted(true);
		this.leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		this.rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		// Set Talon mode
		this.leftMaster.setNeutralMode(NeutralMode.Brake);
		this.rightMaster.setNeutralMode(NeutralMode.Brake);
		configPIDF(0.06,0,0,0.38); // TUNE VALUES
		configMotionMagic(CRUISE_VELOCITY, CRUISE_ACCELERATION);
	}
	public int getQuadPos(int side) 
	{
		int[] arr = {leftMaster.getSelectedSensorPosition(0), rightMaster.getSelectedSensorPosition(0)};
		return arr[side];	
	} 
	
	public void moveLift(double pow) 
	{
		rightMaster.set(ControlMode.PercentOutput, pow);
		leftMaster.set(ControlMode.PercentOutput, pow);
	}
	public void resetEncoders() 
	{
		leftMaster.setSelectedSensorPosition(0, 0, 0);
		rightMaster.setSelectedSensorPosition(0, 0, 0);
	}
	public void moveToPos(int pos) 
	{
		leftMaster.set(ControlMode.Position, pos);
		rightMaster.set(ControlMode.Position, pos);
		
	}
	public void moveToPositionMotionMagic(int pos) 
	{
        leftMaster.set(ControlMode.MotionMagic, pos);
        rightMaster.set(ControlMode.MotionMagic, pos);
    }
	public void configMotionMagic(int cruiseVelocity, int acceleration) 
	{
        leftMaster.configMotionCruiseVelocity(cruiseVelocity, 0);
        leftMaster.configMotionAcceleration(acceleration, 0);
        rightMaster.configMotionCruiseVelocity(cruiseVelocity, 0);
        rightMaster.configMotionAcceleration(acceleration, 0);
    }
	public void configPIDF(double kP, double kI, double kD, double kF) 
	{
	    leftMaster.config_kP(0, kP, 0);
	    leftMaster.config_kI(0, kI, 0);
	    leftMaster.config_kD(0, kD, 0);
	    leftMaster.config_kF(0, kF, 0);
	    rightMaster.config_kP(0, kP, 0);
	    rightMaster.config_kI(0, kI, 0);
	    rightMaster.config_kD(0, kD, 0);
	    rightMaster.config_kF(0, kF, 0);
	}
	
	@Override
	protected void initDefaultCommand() 
	{
		setDefaultCommand(new JoystickLift());
	}

}
