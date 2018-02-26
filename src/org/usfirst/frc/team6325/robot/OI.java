package org.usfirst.frc.team6325.robot;

import org.usfirst.frc.team6325.robot.commands.Drive.ShiftIn;
import org.usfirst.frc.team6325.robot.commands.Drive.ShiftOut;
import org.usfirst.frc.team6325.robot.commands.Drive.ShiftTransmission;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick joyDriver = new Joystick(0);
	public Joystick joySecondary = new Joystick(1);
	public Button trigger = new JoystickButton(joyDriver, 1);
	public Button shift1 = new JoystickButton(joyDriver,11);
	public Button shift2 = new JoystickButton(joyDriver,12);
	
	public OI () {
		trigger.whenPressed(new ShiftTransmission());
		shift1.whenPressed(new ShiftIn());
		shift2.whenPressed(new ShiftOut());
	}
}
