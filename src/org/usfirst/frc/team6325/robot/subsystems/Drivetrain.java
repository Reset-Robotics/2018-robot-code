package org.usfirst.frc.team6325.robot.subsystems;

import java.io.File;


import org.usfirst.frc.team6325.robot.RobotMap;

import org.usfirst.frc.team6325.robot.commands.Drive.TankJoystickDrive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;


/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	
	WPI_VictorSPX frontLeft = new WPI_VictorSPX(RobotMap.frontLeft);
	WPI_TalonSRX leftDriveMaster = new WPI_TalonSRX(RobotMap.midLeft);
	WPI_VictorSPX backLeft = new WPI_VictorSPX (RobotMap.backLeft);
	WPI_VictorSPX frontRight = new WPI_VictorSPX(RobotMap.frontRight);
	WPI_TalonSRX rightDriveMaster = new WPI_TalonSRX(RobotMap.midRight);
	WPI_VictorSPX backRight = new WPI_VictorSPX(RobotMap.backRight);
	private AHRS navx = new AHRS(SerialPort.Port.kMXP);
	DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_PORTS[0], RobotMap.SHIFTER_PORTS[1]);
	boolean isHighGear;
	public Timer timer = new Timer();
	EncoderFollower left;
	EncoderFollower right;
	Trajectory leftTrajectory;
	Trajectory rightTrajectory;
	File leftMotionProfile;
	File rightMotionProfile;
	boolean isProfileFinished;
	 
	
	public Drivetrain() {
		// Set Followers
		this.backLeft.follow(leftDriveMaster);
		this.frontLeft.follow(leftDriveMaster);
		this.backRight.follow(rightDriveMaster);
		this.frontRight.follow(rightDriveMaster);
		// Set up Encoders
		this.leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		this.rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		// Inverse motors
		this.rightDriveMaster.setInverted(true);
		this.backRight.setInverted(true);
		this.frontRight.setInverted(false);
		this.backLeft.setInverted(true);
		this.leftDriveMaster.setInverted(false);
		this.frontLeft.setInverted(false);
		// Set Talon Mode
		this.leftDriveMaster.setNeutralMode(NeutralMode.Brake);
        this.rightDriveMaster.setNeutralMode(NeutralMode.Brake);
		// Current Limiting
		this.leftDriveMaster.configContinuousCurrentLimit(30,0); // desired current after limit
		this.leftDriveMaster.configPeakCurrentLimit(35, 0); // max current
		this.leftDriveMaster.configPeakCurrentDuration(100, 0); // how long after max current to be limited (ms)
		this.leftDriveMaster.enableCurrentLimit(true);
		this.rightDriveMaster.configContinuousCurrentLimit(30,0); // desired current after limit
		this.rightDriveMaster.configPeakCurrentLimit(35, 0); // max current
		this.rightDriveMaster.configPeakCurrentDuration(100, 0); // how long after max current to be limited (ms)
		this.rightDriveMaster.enableCurrentLimit(true);
		navx.reset();
		navx.zeroYaw();
		resetEncoders();
	
	}
	
	public void drive(double leftVal, double rightVal) {
    	leftDriveMaster.set(leftVal);
    	rightDriveMaster.set(rightVal);
    }
    
	public void shift() {
		if(shifter.get()==Value.kForward) {
			shifter.set(Value.kReverse);
			isHighGear = false;
		}
		else {
			shifter.set(Value.kForward);
			isHighGear = true;
		}
			
	}
	public boolean isHighGear() {
		return isHighGear;
	}
    public void killMotors(){
		leftDriveMaster.set(0);
		rightDriveMaster.set(0);
	}
    
    public double getLeftVelocity() {
        return (leftDriveMaster.getSelectedSensorVelocity(0) * Math.PI * MotionProfiling.wheel_diameter) / (MotionProfiling.ticks_per_rev)  * 10;
    }

    public double getRightVelocity() {
        return (rightDriveMaster.getSelectedSensorVelocity(0) * Math.PI * MotionProfiling.wheel_diameter) / (MotionProfiling.ticks_per_rev) * 10;
    }

    public double getLeftAcceleration(double lastTime, double lastVelocity) {
        double deltaTime = timer.get() - lastTime;
        double deltaVelocity = getLeftVelocity() - lastVelocity;

        return deltaVelocity / deltaTime;
    }

    public double getRightAcceleration(double lastTime, double lastVelocity) {
        double deltaTime = timer.get() - lastTime;
        double deltaVelocity = getRightVelocity() - lastVelocity;

        return deltaVelocity / deltaTime;
    }

    
    public void resetEncoders() {
        this.leftDriveMaster.setSelectedSensorPosition(0, 0, 0);
        this.rightDriveMaster.setSelectedSensorPosition(0, 0, 0);
    }

    public double getEncoderDistanceMetersRight() {
        return (rightDriveMaster.getSelectedSensorPosition(0) * Math.PI * MotionProfiling.wheel_diameter) / MotionProfiling.ticks_per_rev;
        // return (rightDriveMaster.getSelectedSensorPosition(0) / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference;
    }

    public double getEncoderDistanceMetersLeft() {
        return (leftDriveMaster.getSelectedSensorPosition(0) * Math.PI * MotionProfiling.wheel_diameter) / MotionProfiling.ticks_per_rev;
        //return (leftDriveMaster.getSelectedSensorPosition(0) / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference;
    }
    
    public double getEncoderRawLeft() {
        return leftDriveMaster.getSelectedSensorPosition(0);
    }

    public double getEncoderRawRight() {
        return rightDriveMaster.getSelectedSensorPosition(0);
    }
  
    public void initPath(String leftCSV, String rightCSV) {
    	leftMotionProfile = new File(leftCSV);
        rightMotionProfile = new File(rightCSV);
        leftTrajectory = Pathfinder.readFromCSV(leftMotionProfile);
        rightTrajectory = Pathfinder.readFromCSV(rightMotionProfile);
        left = new EncoderFollower(leftTrajectory);
        right = new EncoderFollower(leftTrajectory);
        left.configureEncoder(leftDriveMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        right.configureEncoder(rightDriveMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        left.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        right.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        navx.zeroYaw();
    	
    }
    public void executePath() {
    	double l = left.calculate(leftDriveMaster.getSelectedSensorPosition(0));
        double r = right.calculate(rightDriveMaster.getSelectedSensorPosition(0));
        double gyro_heading = navx.getYaw();
        double angle_setpoint = Pathfinder.r2d(left.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(angle_setpoint - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        drive(l + turn, r - turn);
        if(left.isFinished() && right.isFinished()) {
        	isProfileFinished = true;
        }
    }
    public boolean getIsProfileFinished() {
        return isProfileFinished;
    }
	public void initDefaultCommand() {
		setDefaultCommand(new TankJoystickDrive());
	}
	
	public static class MotionProfiling {
		 //TODO: TUNE CONSTANTS
        public static double kp = 0.0;
        public static double ki = 0.0; // not used
        public static double kd = 0.0;

        //hard constants TODO: UPDATE FOR 2018
        public static final double max_velocity = 0.0;
        public static final double kv = 1 / max_velocity;
        public static final double max_acceleration = 0.0;
        public static final double ka = 0.0; // to get to higher or lower speed quicker
        public static final double wheel_diameter = 6;
        public static final double wheel_base_width = 27.11/12.0;
        public static final double wheel_circumference = 6*Math.PI;
        public static final int ticks_per_rev = 4096; // CTRE Mag Encoder
        public static final double dt = 0.02; // 
        public static final double distancePerPulse = (wheel_diameter*Math.PI)/ticks_per_rev;
	}
}
