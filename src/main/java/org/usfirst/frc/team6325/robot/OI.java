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
//import org.usfirst.frc.team6325.robot.ButtonSelection;
//import org.usfirst.frc.team6325.robot.ButtonSelection.ButtonMapMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	IDConfig ids = new IDConfig();
	//ButtonSelection btns = new ButtonSelection();
	//public String selectedDrivetrain = "TankJoystickDrive";
	//public Command selectedDrivetrainCommand;
	
	public Joystick joystickLeft = new Joystick(ids.joystickLeftIDs.get("USB-ID"));
	public Joystick joystickRight = new Joystick(ids.joystickRightIDs.get("USB-ID"));
	public XboxController xboxController = new XboxController(ids.xboxIDs.get("USB-ID"));
	public Joystick xboxJoystickLeft = new Joystick(ids.xboxIDs.get("Left-Joystick-Y-Axis"));
	public Joystick xboxJoystickRight = new Joystick(ids.xboxIDs.get("Right-Joystick-Y-Axis"));
	public double sliderRawAxisNumber = joystickLeft.getRawAxis(ids.joystickLeftIDs.get("SliderAxis"));
	public Button shiftDrivetrain = new JoystickButton(joystickLeft, ids.joystickLeftIDs.get("Trigger"));
	public Button backBeltsIn = new JoystickButton(xboxController, ids.xboxIDs.get("B-Button"));
	public Button backBeltsOut = new JoystickButton(xboxController, ids.xboxIDs.get("X-Button"));
	public Button toggleIntakePistons = new JoystickButton(joystickRight, ids.joystickRightIDs.get("Trigger"));
	public Button intakeWheelsForward = new JoystickButton(joystickLeft, ids.joystickLeftIDs.get("Top-Button-Top-Right"));
	public Button intakeWheelsReverse = new JoystickButton(joystickRight, ids.joystickRightIDs.get("Top-Button-Top-Left"));
	public Button stopIntakes = new JoystickButton(joystickRight, ids.joystickRightIDs.get("Side-Thumb"));
	public Button liftWheelsOut = new JoystickButton(xboxController, ids.xboxIDs.get("A-Button"));
	public Button liftWheelsIn = new JoystickButton(xboxController, ids.xboxIDs.get("Y-Button"));
	public double drivetrainLeft = -joystickLeft.getY();
	public double drivetrainRight = -joystickRight.getY();
	//public Button drivetrainTank, drivetrainArcade;

	
	
	public OI () 
	{
		//buttonMapModeChooser.addDefault(ButtonMapMode.COMPETITION.getName(), ButtonMapMode.COMPETITION);
		//buttonMapModeChooser.addObject(ButtonMapMode.PARADE.getName(), ButtonMapMode.PARADE);
		//buttonMapModeChooser.addObject(ButtonMapMode.OUTREACH.getName(), ButtonMapMode.OUTREACH);
		//ButtonMapMode mapMode = buttonMapModeChooser.getSelected();
		//SmartDashboard.putData("Button Mapping Mode", buttonMapModeChooser);
		

				//selectedDrivetrainCommand = new TankJoystickDrive();
				//drivetrainLeft = -joystickLeft.getY();
				//drivetrainRight = -joystickRight.getY();
			/*if (selectedDrivetrain == "ArcadeJoystickDrive")
			{	
				selectedDrivetrainCommand = new ArcadeJoystickDrive();
				drivetrainLeft = joystickLeft.getY() + joystickLeft.getX();
				drivetrainRight = joystickLeft.getY() - joystickLeft.getX();
			}*/
		/*if (mapMode.getName() == "parade")
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
		}*/
		
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

		/*drivetrainTank.whenPressed(new TypeToggleTank());
		drivetrainArcade.whenReleased(new TypeToggleArcade());*/
	}
}
