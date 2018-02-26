package org.usfirst.frc.team6325.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int masterRight = 2;
	public static int frontRight = 7;
	public static int backRight = 9;
	
	public static int masterLeft = 3 ;
	public static int frontLeft =4;
	public static int backLeft = 8;
	// 6 = right lift motor
	// 7 = RIGHT drive motor
	public static int leftMaster = 0; 
	public static int leftBackWheel = 5;
	public static int rightMaster = 1; 
	public static int rightBackWheel = 6;
	// PWM
	public static int backBelts = 2;
	public static int rightIntakeWheel = 0;
	public static int leftIntakeWheel = 1;
	public static int liftWheel = 4;
	
	public static final int[] SHIFTER_PORTS = {0,1};
	public static final int [] INTAKE = {2,3};
}
