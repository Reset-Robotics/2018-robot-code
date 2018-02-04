package org.usfirst.frc.team6325.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int frontRight = 5;
	public static int frontLeft = 2;
	public static int midLeft = 1;
	public static int midRight = 6;
	public static int backRight = 4;
	public static int backLeft = 3;
	public static int rightIntakeWheel = 99;
	public static int leftIntakeWheel = 99;
	public static int leftFrontWheel = 99;
	public static int leftBackWheel = 98;
	public static int rightFrontWheel = 99;
	public static int rightBackWheel = 99;
	
	public static final int[] SHIFTER_PORTS = {4,5};
	public static final int [] LEFT_INTAKE = {6,7};
	public static final int [] RIGHT_INTAKE= {8,9};
}
