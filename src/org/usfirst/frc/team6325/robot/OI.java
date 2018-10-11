package org.usfirst.frc.team6325.robot;

import org.usfirst.frc.team6325.robot.commands.Drive.*;
import org.usfirst.frc.team6325.robot.commands.Intake.*;
import org.usfirst.frc.team6325.robot.commands.Lift.*;
import org.usfirst.frc.team6325.robot.subsystems.Lift.Positions;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;

import org.usfirst.frc.team6325.robot.IDConfig;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	IDConfig ids = new IDConfig();

	public Joystick joystickLeft = new Joystick(ids.joystickLeftIDs.get("USB-ID"));
	public Joystick joystickRight = new Joystick(ids.joystickRightIDs.get("USB-ID"));
	public XboxController xboxController = new XboxController(ids.xboxIDs.get("USB-ID"));
	public Button shiftDrivetrain = new JoystickButton(joystickLeft, ids.joystickLeftIDs.get("Trigger"));
	public Button backBeltsIn = new JoystickButton(xboxController, ids.xboxIDs.get("X-Button"));
	public Button backBeltsOut = new JoystickButton(xboxController, ids.xboxIDs.get("B-Button"));
	public Button toggleIntakePistons = new JoystickButton(joystickRight, ids.joystickRightIDs.get("Trigger"));
	public Button intakeWheelsForward = new JoystickButton(joystickLeft, ids.joystickLeftIDs.get("Top-Button-Top-Right"));
	public Button intakeWheelsReverse = new JoystickButton(joystickRight, ids.joystickRightIDs.get("Top-Button-Top-Left"));
	public Button stopIntakes = new JoystickButton(joystickRight, ids.joystickRightIDs.get("Side-Thumb"));
	public Button liftWheelsOut = new JoystickButton(xboxController, ids.xboxIDs.get("Y-Button"));
	public Button liftWheelsIn = new JoystickButton(xboxController, ids.xboxIDs.get("A-Button"));
	
	
	
	public OI () {
		shiftDrivetrain.whenPressed(new ShiftTransmission());
		backBeltsIn.whenPressed(new BackIntakeForward(0));
		toggleIntakePistons.whenPressed(new ToggleClamp());
		
		intakeWheelsReverse.whileHeld(new SpinIntakeWheels(-1));
		intakeWheelsReverse.whileHeld(new LiftIntake(1));
		intakeWheelsReverse.whenReleased(new LiftIntake(0));
		
		
		stopIntakes.whenPressed(new StopIntakeWheels());
		stopIntakes.whenPressed(new LiftIntake(0));
		
		intakeWheelsForward.whileHeld(new SpinIntakeWheels(1));
		intakeWheelsForward.whileHeld(new LiftOuttake());
		
		liftWheelsOut.whileHeld(new LiftOuttake());
		liftWheelsOut.whenReleased(new LiftIntake(0));
		
		liftWheelsIn.whileHeld(new LiftIntake(-1));
		liftWheelsIn.whenReleased(new LiftIntake(0));
		
		backBeltsIn.whileHeld(new BackIntakeForward(0.5));
		backBeltsOut.whileHeld(new BackIntakeForward(-0.5));
	}
}
