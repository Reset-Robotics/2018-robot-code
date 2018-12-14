package org.usfirst.frc.team6325.robot.subsystems;

import java.io.File;

import org.usfirst.frc.team6325.robot.Robot;
import org.usfirst.frc.team6325.robot.RobotMap;
import org.usfirst.frc.team6325.robot.commands.Drive.ArcadeJoystickDrive;
import org.usfirst.frc.team6325.robot.commands.Drive.TankJoystickDrive;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput
{
	
    public WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.frontLeft);
	public WPI_TalonSRX leftDriveMaster = new WPI_TalonSRX(RobotMap.masterLeft);
	public WPI_VictorSPX backLeft = new WPI_VictorSPX (RobotMap.backLeft);
	public WPI_VictorSPX frontRight = new WPI_VictorSPX(RobotMap.frontRight);
	public WPI_TalonSRX rightDriveMaster = new WPI_TalonSRX(RobotMap.masterRight);
	public WPI_VictorSPX backRight = new WPI_VictorSPX(RobotMap.backRight);
	public AHRS navx = new AHRS(SPI.Port.kMXP);
	DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_PORTS[0], RobotMap.SHIFTER_PORTS[1]);
	boolean isHighGear;
	public Timer timer = new Timer();
	boolean isProfileFinished = false;
	
	// the PID values used for turning to a specific angle
	private final double turnP = 0.006;
	private final double turnI = 0.0;
	private final double turnD = 0.0;
	private final double turnF = 0.0;
	// how close the robot will try to get to the target angle before stopping
	private final double turnThreshold = 5.0;
	private double turnRate = 0.0;
	
	private PIDController turnController = new PIDController(turnP, turnI, turnD, turnF, navx, this);
	 
	
    public Drivetrain() 
    {
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
		this.frontRight.setInverted(true);
		this.leftDriveMaster.setInverted(false);
		this.backLeft.setInverted(false);
		this.frontLeft.setInverted(true);
		this.rightDriveMaster.setInverted(false);
		//leftDriveMaster.setSensorPhase(true);

		// Set Talon Mode
		this.leftDriveMaster.setNeutralMode(NeutralMode.Brake);
        this.rightDriveMaster.setNeutralMode(NeutralMode.Brake);
		// Current Limiting
		this.leftDriveMaster.configContinuousCurrentLimit(40,0); // desired current after limit
		this.leftDriveMaster.configPeakCurrentLimit(35, 0); // max current
		this.leftDriveMaster.configPeakCurrentDuration(100, 0); // how long after max current to be limited (ms)
		this.leftDriveMaster.enableCurrentLimit(true);
		this.rightDriveMaster.configContinuousCurrentLimit(40,0); // desired current after limit
		this.rightDriveMaster.configPeakCurrentLimit(35, 0); // max current
		this.rightDriveMaster.configPeakCurrentDuration(100, 0); // how long after max current to be limited (ms)
		this.rightDriveMaster.enableCurrentLimit(true);
		navx.reset();
		navx.zeroYaw();
		resetEncoders();
	}
	
    public void drive(double leftVal, double rightVal) 
    {
    	leftDriveMaster.set(leftVal);
    	rightDriveMaster.set(rightVal);
    }

    public void killMotors() 
    {
    	rightDriveMaster.set(0);
    	leftDriveMaster.set(0);
    }

    public void shiftIn() 
    {
    	shifter.set(Value.kForward);
    }

    public void shiftOut() 
    {
    	shifter.set(Value.kReverse);
    }

    public void shift() 
    {
        if(shifter.get()==Value.kForward) 
        {
			shifter.set(Value.kReverse);
			isHighGear = false;
		}
        else 
        {
			shifter.set(Value.kForward);
			isHighGear = true;
		}
			
    }
    
    public boolean isHighGear() 
    {
		return isHighGear;
	}

    public void resetGyro() 
    {
    	navx.zeroYaw();
    }
    
    public double getLeftVelocity() 
    {
        return (leftDriveMaster.getSelectedSensorVelocity(0) * Math.PI * MotionProfiling.wheel_diameter) / (MotionProfiling.ticks_per_rev)  * 10;
        //(ticks/4096)*(6pi) inches
    }

    public double getRightVelocity() 
    {
        return (rightDriveMaster.getSelectedSensorVelocity(0) * Math.PI * MotionProfiling.wheel_diameter) / (MotionProfiling.ticks_per_rev) * 10;
    }
    
    public void resetEncoders() 
    {
        this.leftDriveMaster.setSelectedSensorPosition(0, 0, 0);
        this.rightDriveMaster.setSelectedSensorPosition(0, 0, 0);
    }

    public double getEncoderDistanceMetersRight() 
    {
        return (rightDriveMaster.getSelectedSensorPosition(0) * Math.PI * MotionProfiling.wheel_diameter) / MotionProfiling.ticks_per_rev;
        // return (rightDriveMaster.getSelectedSensorPosition(0) / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference;
    }

    public double getEncoderDistanceMetersLeft() 
    {
        return (leftDriveMaster.getSelectedSensorPosition(0) * Math.PI * MotionProfiling.wheel_diameter) / MotionProfiling.ticks_per_rev;
        //return (leftDriveMaster.getSelectedSensorPosition(0) / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference;
    }
    public float getAngle() 
    {
    	return navx.getYaw();
    }
    public double getEncoderRawLeft() 
    {
        return leftDriveMaster.getSelectedSensorPosition(0);
    }

    public double getEncoderRawRight() 
    {
        return rightDriveMaster.getSelectedSensorPosition(0);
    }
    
    public boolean turnToAngle(double angle) 
    {
        double error = angle - navx.getAngle();
        double turn = 1.5 * (-1.0/80.0) * error;
        this.leftDriveMaster.set(ControlMode.PercentOutput, turn);
        this.rightDriveMaster.set(ControlMode.PercentOutput, -turn);
        SmartDashboard.putNumber("turn to angle error", error);
        return Math.abs(error) <= 5;
    }
    public double generateHashCode(Waypoint[] path) 
    {
        double hash = 1.0;
        for(int i = 0; i < path.length; i ++)
            hash =  ((path[i].x * 6) + (path[i].y * 3) + (path[i].angle * 25));
        return (int)Math.abs(hash * 100);
    }
    
  
    public EncoderFollower[] initPath(String leftCSV, String rightCSV) 
    {
    	File leftMotionProfile = new File(leftCSV);
        File rightMotionProfile = new File(rightCSV);
        System.err.println("File about to read");
        Trajectory leftTrajectory = Pathfinder.readFromCSV(leftMotionProfile);
        Trajectory rightTrajectory = Pathfinder.readFromCSV(rightMotionProfile);
        System.err.println("File read");
        EncoderFollower left = new EncoderFollower(leftTrajectory);
        EncoderFollower right = new EncoderFollower(rightTrajectory);
        left.configureEncoder(leftDriveMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        right.configureEncoder(rightDriveMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        left.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        right.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        navx.zeroYaw();
        return new EncoderFollower[] 
        {
            left, // 0
            right, // 1
        };
    	
    }
    public EncoderFollower[] initPath(Waypoint[] path) 
    {

        EncoderFollower left = new EncoderFollower();
        EncoderFollower right = new EncoderFollower();
        Trajectory.Config cfg = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH,
            Drivetrain.MotionProfiling.dt, Drivetrain.MotionProfiling.max_velocity, 
            Drivetrain.MotionProfiling.max_acceleration, Drivetrain.MotionProfiling.max_jerk);
        String pathHash = String.valueOf(generateHashCode(path));
        SmartDashboard.putString("Path Hash", pathHash);
        Trajectory toFollow;
        File trajectory = new File("/home/lvuser/paths/" + pathHash + ".csv");
        if (!trajectory.exists()) 
        {
            toFollow = Pathfinder.generate(path, cfg);
            Pathfinder.writeToCSV(trajectory, toFollow);
            System.out.println(pathHash + ".csv not found, wrote to file");
        } 
        else 
        {
            System.out.println(pathHash + ".csv read from file");
            toFollow = Pathfinder.readFromCSV(trajectory);
        }

        TankModifier modifier = new TankModifier(toFollow).modify((Drivetrain.MotionProfiling.wheel_base_width));
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
        left.configureEncoder(leftDriveMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        right.configureEncoder(rightDriveMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        left.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        right.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        return new EncoderFollower[]
        {
            left, // 0
            right, // 1
        };
    }
    public void executePath(EncoderFollower[] followers,boolean reverse) 
    {
    	EncoderFollower left = followers[0];
        EncoderFollower right = followers[1];
        double l,r;
        if (!reverse) 
        {
            l = left.calculate(leftDriveMaster.getSelectedSensorPosition(0));
            r = right.calculate(rightDriveMaster.getSelectedSensorPosition(0));
        } 
        else 
        {
            l = left.calculate(-leftDriveMaster.getSelectedSensorPosition(0));
            r = right.calculate(-rightDriveMaster.getSelectedSensorPosition(0));
        }
        double gyro_heading = navx.getYaw();
        double angle_setpoint = Pathfinder.r2d(left.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(angle_setpoint - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        if(!reverse)
            drive(l + turn, r - turn);
        else
            drive(-l + turn, -r - turn);

        if(left.isFinished() && right.isFinished())
        	isProfileFinished = true;
    }

    public boolean getIsProfileFinished() 
    {
        return isProfileFinished;
    }
    public void resetForPath() 
    {
        isProfileFinished = false;
        resetEncoders();
        resetGyro();
    } 
    
    public void initDefaultCommand() 
    {
        if (Robot.oi.selectedDrivetrainCommand != null)
            setDefaultCommand(Robot.oi.selectedDrivetrainCommand);
        else
            setDefaultCommand(new TankJoystickDrive());
	}
	
    public void autoTurn() 
    {
		drive(turnRate, -turnRate);
	}
	
    public void autoTurnInit(double angle) 
    {
		turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(turnThreshold);
        turnController.setContinuous(true);
        turnController.setSetpoint(angle);
        turnController.enable();
	}
	
    public void autoTurnStop() 
    {
		turnController.disable();
	}
	
    public void pidWrite(double output) 
    {
        turnRate = output;
    }
	
    public static class MotionProfiling 
    {
        public static double kp = 1;
        public static double ki = 0.0; // not used
        public static double kd = 0.05;

        public static final double max_velocity = 7.0;
        public static final double kv = 1 / max_velocity;
        public static final double max_acceleration = 3.0;
        public static final double max_jerk = 60.0;
        public static final double ka = 0.0; // to get to higher or lower speed quicker
        public static final double wheel_diameter = 0.5;
        public static final double wheel_base_width = 27.11/12.0;
        public static final double wheel_circumference = 6*Math.PI;
        public static final int ticks_per_rev = 4096*7; // CTRE Mag Encoder
        public static final double distancePerPulse = (wheel_diameter*Math.PI)/ticks_per_rev;
        public static final double dt = 0.05;
	}
}
