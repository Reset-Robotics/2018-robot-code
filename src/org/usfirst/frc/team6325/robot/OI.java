package org.usfirst.frc.team6325.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6325.robot.commands.Drive.*;
import org.usfirst.frc.team6325.robot.commands.Intake.*;
import org.usfirst.frc.team6325.robot.commands.Lift.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;

import org.usfirst.frc.team6325.robot.IDConfig;
import org.usfirst.frc.team6325.robot.ButtonSelection;
import org.usfirst.frc.team6325.robot.ButtonSelection.ButtonMapMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	IDConfig ids = new IDConfig();
	ButtonSelection btns = new ButtonSelection();
	public String selectedDrivetrain = "TankJoystickDrive";
	public Command selectedDrivetrainCommand;
	
	public Joystick joystickLeft = new Joystick(ids.joystickLeftIDs.get("USB-ID"));
	public Joystick joystickRight = new Joystick(ids.joystickRightIDs.get("USB-ID"));
	public XboxController xboxController = new XboxController(ids.xboxIDs.get("USB-ID"));
	public Joystick xboxJoystickLeft = new Joystick(ids.xboxIDs.get("Left-Joystick-Y-Axis"));
	public Joystick xboxJoystickRight = new Joystick(ids.xboxIDs.get("Right-Joystick-Y-Axis"));
	public double sliderRawAxisNumber = Robot.oi.joystickLeft.getRawAxis(ids.joystickLeftIDs.get("SliderAxis"));
	public double drivetrainLeft;
	public double drivetrainRight;
	public Button shiftDrivetrain;
	public Button backBeltsIn;
	public Button backBeltsOut;
	public Button toggleIntakePistons;
	public Button intakeWheelsForward;
	public Button intakeWheelsReverse;
	public Button stopIntakes;
	public Button liftWheelsOut;
	public Button liftWheelsIn;
	public Button drivetrainTank;
	public Button drivetrainArcade;

	
	
	public OI () 
	{
		SendableChooser<ButtonMapMode> buttonMapModeChooser = new SendableChooser<>();
		buttonMapModeChooser.addDefault(ButtonMapMode.COMPETITION.getName(), ButtonMapMode.COMPETITION);
		buttonMapModeChooser.addObject(ButtonMapMode.PARADE.getName(), ButtonMapMode.PARADE);
		buttonMapModeChooser.addObject(ButtonMapMode.OUTREACH.getName(), ButtonMapMode.OUTREACH);
		ButtonMapMode mapMode = buttonMapModeChooser.getSelected();
		SmartDashboard.putData("Button Mapping Mode", buttonMapModeChooser);



		if (mapMode.getName() == "competition")
		{
			shiftDrivetrain = btns.competitionButtons.get("shiftDrivetrain");
			backBeltsIn = btns.competitionButtons.get("backBeltsIn");
			backBeltsOut = btns.competitionButtons.get("backBeltsOut");
			toggleIntakePistons = btns.competitionButtons.get("toggleIntakePistons");
			intakeWheelsForward = btns.competitionButtons.get("intakeWheelsForward");
			intakeWheelsReverse = btns.competitionButtons.get("IntakeWheelsReverse");
			stopIntakes = btns.competitionButtons.get("stopIntakes");
			liftWheelsOut = btns.competitionButtons.get("liftWheelsOut");
			liftWheelsIn = btns.competitionButtons.get("liftWheelsIn");

			if (selectedDrivetrain == "TankJoystickDrive")
			{
				selectedDrivetrainCommand = new TankJoystickDrive();
				drivetrainLeft = btns.competitionDrivetrainAxes.get("Tank-Left-Joystick-Drive");
				drivetrainRight = btns.competitionDrivetrainAxes.get("Tank-Right-Joystick-Drive");
			}
			if (selectedDrivetrain == "ArcadeJoystickDrive")
			{	
				selectedDrivetrainCommand = new ArcadeJoystickDrive();
				drivetrainLeft = btns.competitionDrivetrainAxes.get("Arcade-Joystick-Drive-Y") + btns.competitionDrivetrainAxes.get("Arcade-Joystick-Drive-X");
				drivetrainRight = btns.competitionDrivetrainAxes.get("Arcade-Joystick-Drive-Y") - btns.competitionDrivetrainAxes.get("Arcade-Joystick-Drive-X");
			}
		}
		if (mapMode.getName() == "parade")
		{
			shiftDrivetrain = btns.paradeButtons.get("shiftDrivetrain");
			drivetrainTank = new JoystickButton(Robot.oi.xboxController, ids.xboxIDs.get("X-Button"));
			drivetrainArcade = new JoystickButton(Robot.oi.xboxController, ids.xboxIDs.get("B-Button")); 


			if (selectedDrivetrain == "TankJoystickDrive")
			{
				selectedDrivetrainCommand = new TankJoystickDrive();
				drivetrainLeft = btns.paradeDrivetrainAxes.get("Tank-Left-Joystick-Drive");
				drivetrainRight = btns.paradeDrivetrainAxes.get("Tank-Right-Joystick-Drive");
			}
			if (selectedDrivetrain == "ArcadeJoystickDrive")
			{	
				selectedDrivetrainCommand = new ArcadeJoystickDrive();
				drivetrainLeft = btns.paradeDrivetrainAxes.get("Arcade-Joystick-Drive-Y") + btns.paradeDrivetrainAxes.get("Arcade-Joystick-Drive-X");
				drivetrainRight = btns.paradeDrivetrainAxes.get("Arcade-Joystick-Drive-Y") - btns.paradeDrivetrainAxes.get("Arcade-Joystick-Drive-X");
			}
		}
		
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

		drivetrainTank.whenPressed(new TypeToggleTank());
		drivetrainArcade.whenReleased(new TypeToggleArcade());
	}
}
