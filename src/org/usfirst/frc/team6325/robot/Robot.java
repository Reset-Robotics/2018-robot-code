
package org.usfirst.frc.team6325.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.AutoPosition;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.AutoPreference;
import org.usfirst.frc.team6325.robot.commands.Auto.MidSwitch;
import org.usfirst.frc.team6325.robot.commands.Drive.ArcadeJoystickDrive;
import org.usfirst.frc.team6325.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6325.robot.subsystems.Intake;
import org.usfirst.frc.team6325.robot.subsystems.Lift;
import org.usfirst.frc.team6325.robot.subsystems.LiftIntake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Lift lift = new Lift();
	public static final Intake intake = new Intake();
	public static final LiftIntake liftIntake = new LiftIntake();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
<<<<<<< HEAD
	SendableChooser<Command> positionChooser = new SendableChooser<>();
	SendableChooser<Command> preferenceChooser = new SendableChooser<>();
=======
	SendableChooser<AutoPosition> positionChooser;
	SendableChooser<AutoPreference> preferenceChooser;
>>>>>>> 9a0ef8c75a14aef27cffed2d1f5c8d7653ad26ac

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
<<<<<<< HEAD
		// chooser.addObject("My Auto", new MyAutoCommand());
=======
		chooser.addDefault("Default Auto", new ArcadeJoystickDrive());
		chooser.addObject("My Auto", new MidSwitch('L'));
>>>>>>> 9a0ef8c75a14aef27cffed2d1f5c8d7653ad26ac
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("Auto Position", positionChooser);
		SmartDashboard.putData("Auto Preference", preferenceChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		 String gameData;
	     gameData = DriverStation.getInstance().getGameSpecificMessage();
	     char switchSide = ' ';
	     try {
	       switchSide = gameData.charAt(0);
	        } catch (IndexOutOfBoundsException ex) {
	         System.out.println("No Game Data");
	        }
	     AutoPosition position = positionChooser.getSelected();
	     AutoPreference preference = preferenceChooser.getSelected();
		 autonomousCommand = chooser.getSelected();
		 switch (position.getName() + '-' + preference.getName()) {
         case "Left-Scale":
            // autonomousCommand = new LeftScale();
             break;
         case "Left-Switch":
            // autonomousCommand = new LeftSwitch();
             break;
         case "Middle-Switch":
             autonomousCommand = new MidSwitch(switchSide);
             break;
         case "Right-Scale":
            // autonomousCommand = new RightScale();
             break;
         case "Right-Switch":
           //  autonomousCommand = new RightSwitch();
             break;
         default: break;
     }

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
