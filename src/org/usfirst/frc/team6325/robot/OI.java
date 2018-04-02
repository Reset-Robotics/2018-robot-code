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
import org.usfirst.frc.team6325.robot.commands.Lift.RunLiftMotionMagic;
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
	public Joystick joyDriver = new Joystick(0);
	public Joystick joySecondary = new Joystick(1);
	public XboxController operator = new XboxController(2);
	public Button driverTrigger = new JoystickButton(joyDriver, 1);
	public Button shift2 = new JoystickButton(joyDriver,12);
	public Button toggleClamp = new JoystickButton(operator, 1); // R Bumper
	public Button intake = new JoystickButton(operator, 6); // L Bumper
	public Button intakeReverse = new JoystickButton(operator, 5); // R Bumper
	//public Button liftOuttake = new JoystickButton(operator, 2); // L Trigger
	public Button stopIntake = new JoystickButton(operator, 7); //
	public Button liftOuttake = new JoystickButton(operator, 2);
	public Button liftIntake = new JoystickButton(operator, 3);
	public Button yButton = new JoystickButton(operator, 4);
	
	
	
	
	public OI () {
		driverTrigger.whenPressed(new ShiftTransmission());
		shift2.whenPressed(new BackIntakeForward(0));
		toggleClamp.whenPressed(new ToggleClamp());
		intake.whenPressed(new SpinIntakeWheelsBack());
		intake.whenPressed(new LiftIntake(1));
		
		stopIntake.whenPressed(new StopIntakeWheels());
		stopIntake.whenPressed(new LiftIntake(0));
		
		intakeReverse.whenPressed(new SpinIntakeWheelsForward());
		liftOuttake.whileHeld(new LiftOuttake());
		liftOuttake.whenReleased(new LiftIntake(0));
		
		liftIntake.whileHeld(new LiftIntake(-1));
		liftIntake.whenReleased(new LiftIntake(0));
		yButton.whenPressed(new RunLiftMotionMagic(Positions.ScoreScale));
		
		//backBelt.whileHeld(new BackIntakeForward(-0.5));
	}
}
