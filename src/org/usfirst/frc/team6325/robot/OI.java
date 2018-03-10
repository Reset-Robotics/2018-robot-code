package org.usfirst.frc.team6325.robot;

import org.usfirst.frc.team6325.robot.commands.Drive.ShiftIn;
import org.usfirst.frc.team6325.robot.commands.Drive.ShiftOut;
import org.usfirst.frc.team6325.robot.commands.Drive.ShiftTransmission;
import org.usfirst.frc.team6325.robot.commands.Intake.SpinIntakeWheelsBack;
import org.usfirst.frc.team6325.robot.commands.Intake.SpinIntakeWheelsForward;
import org.usfirst.frc.team6325.robot.commands.Intake.StopIntakeWheels;
import org.usfirst.frc.team6325.robot.commands.Intake.ToggleClamp;
import org.usfirst.frc.team6325.robot.commands.Lift.BackIntakeForward;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntake;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftOuttake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick joyDriver = new Joystick(0);
	public Joystick joySecondary = new Joystick(1);
	public Joystick operator = new Joystick(2);
	public Button driverTrigger = new JoystickButton(joyDriver, 1);
	public Button shift2 = new JoystickButton(joyDriver,12);
	public Button toggleClamp = new JoystickButton(operator, 1); // R Bumper
	public Button intake = new JoystickButton(operator, 6); // L Bumper
	public Button intakeReverse = new JoystickButton(operator, 5); // R Bumper
	//public Button liftOuttake = new JoystickButton(operator, 2); // L Trigger
	public Button stopIntake = new JoystickButton(operator, 7); //
	public Button liftOuttake = new JoystickButton(operator, 2);
	public Button liftIntake = new JoystickButton(operator, 3);
	
	
	
	
	public OI () {
		driverTrigger.whenPressed(new ShiftTransmission());
		shift2.whenPressed(new BackIntakeForward(0));
		toggleClamp.whenPressed(new ToggleClamp());
		intake.whenPressed(new SpinIntakeWheelsBack());
		intake.whenPressed(new LiftIntake(1));
		liftOuttake.whenPressed(new LiftOuttake());
		stopIntake.whenPressed(new StopIntakeWheels());
		stopIntake.whenPressed(new LiftIntake(0));
		intakeReverse.whenPressed(new SpinIntakeWheelsForward());
		liftIntake.whenPressed(new LiftIntake(-1));
		//backBelt.whileHeld(new BackIntakeForward(-0.5));
		
	}
}
