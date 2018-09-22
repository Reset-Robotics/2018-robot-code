package org.usfirst.frc.team6325.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Left Drive Motors
	public static int masterRight = 2;
	public static int frontRight = 7;
	public static int backRight = 9;
	// Right Drive Motors
	public static int masterLeft = 3 ;
	public static int frontLeft = 4;
	public static int backLeft = 8;
	// Lift Motors
	public static int leftMaster = 0; 
	public static int rightMaster = 1; 
	// PWM Motors
	public static int backBelts = 0;
	public static int rightIntakeWheel = 1;
	public static int leftIntakeWheel = 2;
	public static int liftWheel = 3;
	
	public static final int[] SHIFTER_PORTS = {0,1};
	public static final int [] INTAKE = {2,3};
}
