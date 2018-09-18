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

import java.lang.reflect.Method;

import org.usfirst.frc.team6325.robot.Paths.Center;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.AutoPosition;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.AutoPreference;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.AutoCubes;

//import org.usfirst.frc.team6325.robot.commands.Auto.Baseline;
import org.usfirst.frc.team6325.robot.commands.Auto.MidSwitch;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoPathSelector;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoPathSelector.Baseline;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoPathSelector.MiddleSwitch.One;
import org.usfirst.frc.team6325.robot.commands.Auto.SimpleAutoSwitch;
import org.usfirst.frc.team6325.robot.commands.Drive.ArcadeJoystickDrive;
import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollower;
import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollowerUpdate;
import org.usfirst.frc.team6325.robot.commands.Drive.ResetGyro;
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
public class Robot extends IterativeRobot 
{

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Lift lift = new Lift();
	public static final Intake intake = new Intake();
	public static final LiftIntake liftIntake = new LiftIntake();
	public static final BackBelts backBelts = new BackBelts();
	public static OI oi;
	public double start, time;
	//PowerDistributionPanel pdp = new PowerDistributionPanel(0);
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<AutoPosition> positionChooser = new SendableChooser<>();
	SendableChooser<AutoPreference> preferenceChooser = new SendableChooser<>();
	SendableChooser<AutoCubes> cubesChooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		oi = new OI();
		chooser.addDefault("Default Auto", new ArcadeJoystickDrive()); 
		positionChooser.addDefault(AutoPosition.MIDDLE.getName(), AutoPosition.MIDDLE);
		positionChooser.addObject(AutoPosition.LEFT.getName(), AutoPosition.LEFT);
		positionChooser.addObject(AutoPosition.RIGHT.getName(), AutoPosition.RIGHT);
		preferenceChooser.addDefault(AutoPreference.SWITCH.getName(), AutoPreference.SWITCH);
		preferenceChooser.addObject(AutoPreference.SCALE.getName(), AutoPreference.SCALE);
		preferenceChooser.addObject(AutoPreference.SIMPLE.getName(), AutoPreference.SIMPLE);
		preferenceChooser.addObject(AutoPreference.BASELINE.getName(), AutoPreference.BASELINE);
		cubesChooser.addDefault(AutoCubes.ONE.getName(), AutoCubes.ONE);
		cubesChooser.addObject(AutoCubes.TWO.getName(), AutoCubes.TWO);
		cubesChooser.addObject(AutoCubes.THREE.getName(), AutoCubes.THREE);
		cubesChooser.addObject(AutoCubes.ONEONE.getName(), AutoCubes.ONEONE);
		cubesChooser.addObject(AutoCubes.ONETWO.getName(), AutoCubes.ONETWO);

		SmartDashboard.putData("Auto Mode", chooser);
		SmartDashboard.putData("Auto Position", positionChooser);
		SmartDashboard.putData("Auto Preference", preferenceChooser);
		SmartDashboard.putData("Auto Cubes", cubesChooser);
		SmartDashboard.putData("Reset Gyro", new ResetGyro());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() 
	{

	}

	@Override
	public void disabledPeriodic() 
	{
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
	public void autonomousInit() 
	{
		 String gameData;
	     gameData = DriverStation.getInstance().getGameSpecificMessage();
	     char switchSide = ' ';
	     char scaleSide = ' ';
	     try 
	     {
	       switchSide = gameData.charAt(0);
	       System.err.println("Switch Side = "+ switchSide);
	       scaleSide = gameData.charAt(1);
	       System.err.println("Scale Side = "+ scaleSide);
	     } 
	     catch (IndexOutOfBoundsException ex) 
	     {
	         System.out.println("No Game Data");
	     } 
	     AutoPosition position = positionChooser.getSelected();
	     System.err.println("position = " + position);
	     AutoPreference preference = preferenceChooser.getSelected();
	     System.err.println("preference = " + preference);
	     AutoCubes cubes = cubesChooser.getSelected();
	     System.err.println("cubes = " + cubes);
	     // Lines 149-152 basically do the same thing our 100 line switch/case statement did....
	     String classNameString = ("AutoPathSelector." + position.getName() + preference.getName() + "." + cubes.getName() + "(" + switchSide + ")");
	     System.err.println(classNameString);
	     Class autoPathRunner;
	     //Method method = AutoPathSelector.class.getDeclaredMethod(classNameString);
		 if (preference.getName() == "Switch")
		 {
			 //AutoPathSelector autoSwitchCommand = AutoPathSelector.class.getDeclaredMethod(classNameString);
			 //autonomousCommand = new AutoPathSelector.MiddleSwitch.One(switchSide);
			 //autonomousCommand = method.invoke(switchSide);
			 try
			 {
			     autoPathRunner = Class.forName(classNameString);
			     //AutoPathSelector.MiddleSwitch.One(switchSide);
			 }
		     catch (ClassNotFoundException ex) 
			 {
		         System.out.println("No AutoPathSelector subclass of specified class name");

			 }
		 }
		 else if(preference.getName() == "Scale")
		 {
			 try
			 {
			     autoPathRunner = Class.forName(classNameString);
			 }
		     catch (ClassNotFoundException ex) 
			 {
		         System.out.println("No AutoPathSelector subclass of specified class name");

			 }
		 }
	     else if(preference.getName() == "Baseline") 
		 {
	    	 autonomousCommand = new AutoPathSelector.Baseline();
         }	 

		
		//start = System.currentTimeMillis();
		Robot.drivetrain.resetEncoders();
		Robot.intake.clampIn();
		Robot.drivetrain.navx.zeroYaw();
		//Robot.drivetrain.shiftIn();
		if (autonomousCommand != null)
			autonomousCommand.start();
		//if (autoPathRunner != null)
			//autoPathRunner(switchSide).start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Enc value left drive", Robot.drivetrain.getEncoderRawLeft());
		SmartDashboard.putNumber("Enc value right drive", Robot.drivetrain.getEncoderRawRight());
		SmartDashboard.putNumber("Gyro Yaw", Robot.drivetrain.navx.getYaw());	
	}

	@Override
	public void teleopInit() 
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		Robot.drivetrain.navx.zeroYaw();
		Robot.drivetrain.resetEncoders();
		Robot.lift.resetEncoders();
		// this line or comment it out.
		if (autonomousCommand != null)   
			autonomousCommand.cancel();
	}

	/**...
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Enc value left drive", Robot.drivetrain.getEncoderRawLeft());
		SmartDashboard.putNumber("Enc value right drive", Robot.drivetrain.getEncoderRawRight());
		SmartDashboard.putNumber("Enc value left lift", Robot.lift.leftMaster.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Enc value right Lift", Robot.lift.rightMaster.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Gyro Yaw", Robot.drivetrain.navx.getYaw());	
		SmartDashboard.putNumber("Gyro Angle", Robot.drivetrain.navx.getAngle());	
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
		LiveWindow.run();
	}
}
