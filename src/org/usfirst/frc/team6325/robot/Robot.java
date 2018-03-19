
package org.usfirst.frc.team6325.robot;

import edu.wpi.cscore.UsbCamera;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6325.robot.Paths.Center;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.AutoPosition;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.AutoPreference;
import org.usfirst.frc.team6325.robot.commands.Auto.MidSwitch;
import org.usfirst.frc.team6325.robot.commands.Auto.SimpleAutoSwitch;
import org.usfirst.frc.team6325.robot.commands.Drive.ArcadeJoystickDrive;
import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollower;
import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollowerUpdate;
import org.usfirst.frc.team6325.robot.commands.Drive.WaypointFollower;
import org.usfirst.frc.team6325.robot.subsystems.BackBelts;
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
	public static final BackBelts backBelts = new BackBelts();
	public static OI oi;
	public double start;
	//PowerDistributionPanel pdp = new PowerDistributionPanel(0);
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<AutoPosition> positionChooser = new SendableChooser<>();
	SendableChooser<AutoPreference> preferenceChooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		chooser.addDefault("Default Auto", new ArcadeJoystickDrive()); 
		//chooser.addObject("SwitchTest", new MidSwitch('L'));
		chooser.addObject("Baseline", new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/Baseline/Baseline_left_detailed.csv", 
				                   "/home/lvuser/MotionProfiles/Baseline/Baseline_right_detailed.csv"));
		//chooser.addObject("Waypoint Test", new WaypointFollower(Center.toLeftSwitch));
		positionChooser.addDefault(AutoPosition.MIDDLE.getName(), AutoPosition.MIDDLE);
		positionChooser.addObject(AutoPosition.LEFT.getName(), AutoPosition.LEFT);
		positionChooser.addObject(AutoPosition.RIGHT.getName(), AutoPosition.RIGHT);
		preferenceChooser.addDefault(AutoPreference.SWITCH.getName(), AutoPreference.SWITCH);
		preferenceChooser.addObject(AutoPreference.SCALE.getName(), AutoPreference.SCALE);
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
	       System.err.println("Switch side = "+ switchSide);
	        } catch (IndexOutOfBoundsException ex) {
	         System.out.println("No Game Data");
	        } 
	     AutoPosition position = positionChooser.getSelected();
	     System.err.println("position = " + position);
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
		 if(preference.getName() == "Simple") {
        	 autonomousCommand = new SimpleAutoSwitch();
         }	 

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		start = System.currentTimeMillis();
		Robot.drivetrain.resetEncoders();
		Robot.intake.clampIn();
		//Robot.drivetrain.shiftIn();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Enc value left drive", Robot.drivetrain.getEncoderRawLeft());
		SmartDashboard.putNumber("Enc value right drive", Robot.drivetrain.getEncoderRawRight());
		
		/*
		double time = System.currentTimeMillis();
		if(time>=start+5000 && time <start+9000) {
			Robot.drivetrain.drive(0.5, 0.5);
		}
		if (time>= start +9000) {
			Robot.drivetrain.drive(0, 0);
		}
		*/
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		Robot.drivetrain.navx.zeroYaw();
		Robot.drivetrain.resetEncoders();
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
		SmartDashboard.putNumber("Enc value left drive", Robot.drivetrain.getEncoderRawLeft());
		SmartDashboard.putNumber("Enc value right drive", Robot.drivetrain.getEncoderRawRight());
		SmartDashboard.putNumber("Enc value left lift", Robot.lift.getQuadPos(0));
		SmartDashboard.putNumber("Enc value right right", Robot.lift.getQuadPos(1));
		SmartDashboard.putNumber("Gyro Yaw", Robot.drivetrain.navx.getYaw());	
		
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
