package org.usfirst.frc.team6325.robot;

import org.usfirst.frc.team6325.robot.commands.Drive.*;
import org.usfirst.frc.team6325.robot.commands.Intake.*;
import org.usfirst.frc.team6325.robot.commands.Lift.*;
import org.usfirst.frc.team6325.robot.subsystems.Lift.Positions;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Constants; Assigns IDs to names
	public static final int joystickLeftUSB = 0;
	public static final int joystickRightUSB = 1;
	public static final int xboxControllerUSB = 2;
	public static final int xboxAButton = 1;
	public static final int xboxBButton = 2;
	public static final int xboxXButton = 3;
	public static final int xboxYButton = 4;
	public static final int xboxLeftBumper = 5;
	public static final int xboxRightBumper = 6;
	public static final int xboxBackButton = 7;
	public static final int xboxStartButton = 8; 
	public static final int xboxLeftJoystickButton = 9; 
	public static final int xboxRightJoystickButton = 10; 
	public static final int joystickLeftXAxis = 0;
	public static final int joystickLeftYAxis = 1;
	public static final int joystickLeftZAxis = 2;
	public static final int joystickLeftTrigger = 1;
	public static final int joystickLeftSideThumb = 2;
	public static final int joystickLeftTopButtonBottomRight = 3;
	public static final int joystickLeftTopButtonBottomLeft = 4;
	public static final int joystickLeftTopButtonTopLeft = 5;
	public static final int joystickLeftTopButtonTopRight = 6;
	public static final int joystickRightXAxis = 0;
	public static final int joystickRightYAxis = 1;
	public static final int joystickRightZAxis = 2;
	public static final int joystickRightTrigger = 1;
	public static final int joystickRightSideThumb = 2;
	public static final int joystickRightTopButtonBottomRight = 3;
	public static final int joystickRightTopButtonBottomLeft = 4;
	public static final int joystickRightTopButtonTopLeft = 5;
	public static final int joystickRightTopButtonTopRight = 6;
		
	public Joystick joystickLeft = new Joystick(joystickLeftUSB);
	public Joystick joystickRight = new Joystick(joystickRightUSB);
	public XboxController xboxController = new XboxController(xboxControllerUSB);
	public Button shiftDrivetrain = new JoystickButton(joystickLeft, joystickLeftTrigger);
	public Button backBeltsIn = new JoystickButton(xboxController, xboxXButton);
	public Button backBeltsOut = new JoystickButton(xboxController, xboxBButton);
	public Button toggleIntakePistons = new JoystickButton(joystickRight, joystickRightTrigger);
	public Button intakeWheelsForward = new JoystickButton(joystickLeft, joystickLeftTopButtonTopRight);
	public Button intakeWheelsReverse = new JoystickButton(joystickRight, joystickRightTopButtonTopLeft);
	public Button stopIntakes = new JoystickButton(joystickRight, joystickRightSideThumb); //
	public Button liftWheelsOut = new JoystickButton(xboxController, xboxYButton);
	public Button liftWheelsIn = new JoystickButton(xboxController, xboxAButton);
	
	
	
	public OI () {
		shiftDrivetrain.whenPressed(new ShiftTransmission());
		backBeltsIn.whenPressed(new BackIntakeForward(0));
		toggleIntakePistons.whenPressed(new ToggleClamp());
		
		intakeWheelsReverse.whileHeld(new SpinIntakeWheelsBack(-1));
		intakeWheelsReverse.whileHeld(new LiftIntake(1));
		intakeWheelsReverse.whenReleased(new LiftIntake(0));
		
		
		stopIntakes.whenPressed(new StopIntakeWheels());
		stopIntakes.whenPressed(new LiftIntake(0));
		
		intakeWheelsForward.whileHeld(new SpinIntakeWheelsForward(1));
		intakeWheelsForward.whileHeld(new LiftOuttake());
		
		liftWheelsOut.whileHeld(new LiftOuttake());
		liftWheelsOut.whenReleased(new LiftIntake(0));
		
		liftWheelsIn.whileHeld(new LiftIntake(-1));
		liftWheelsIn.whenReleased(new LiftIntake(0));
		
		backBeltsIn.whileHeld(new BackIntakeForward(0.5));
		backBeltsOut.whileHeld(new BackIntakeForward(-0.5));
	}
}
