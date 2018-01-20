package org.usfirst.frc.team6325.robot.subsystems;

import org.usfirst.frc.team6325.robot.RobotMap;
import org.usfirst.frc.team6325.robot.commands.TankJoystickDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	
	WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.frontLeft);
	WPI_TalonSRX midLeft = new WPI_TalonSRX(RobotMap.midLeft);
	WPI_TalonSRX backLeft = new WPI_TalonSRX (RobotMap.backLeft);
	WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.frontRight);
	WPI_TalonSRX midRight = new WPI_TalonSRX(RobotMap.midRight);
	WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.backRight);
	
	SpeedController leftMotors = new SpeedController(frontLeft,midLeft,backLeft);
	SpeedController rightMotors = new SpeedController(frontRight,midRight,backRight);
	
	public Drivetrain() {
	
	}
	
	public void drive(double leftVal, double rightVal) {
    	leftMotors.set(leftVal);
    	rightMotors.set(rightVal);
    }
    
   
    public void killMotors(){
		leftMotors.stopMotor();
		rightMotors.stopMotor();
	}
    
  
	public void initDefaultCommand() {
		setDefaultCommand(new TankJoystickDrive());
	}
}
