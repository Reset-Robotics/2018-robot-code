package org.usfirst.frc.team6325.robot;


//import edu.wpi.cscore.UsbCamera;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.*;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoPathSelector;
import org.usfirst.frc.team6325.robot.commands.Auto.GamedataFetcher;
import org.usfirst.frc.team6325.robot.commands.Drive.ArcadeJoystickDrive;
import org.usfirst.frc.team6325.robot.commands.Drive.ResetGyro;
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

	public static final Lift lift = new Lift();
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Intake intake = new Intake();
	public static final LiftIntake liftIntake = new LiftIntake();
	public static final BackBelts backBelts = new BackBelts();
	public static OI oi;
	public double start, time;
	Command autonomousCommand; // this command holds which autonomous command is selected
	SendableChooser<Command> chooser = new SendableChooser<>(); // creates a sendable chooser for part of our autonomous command
	SendableChooser<AutoPosition> positionChooser = new SendableChooser<>(); // creates a sendable chooser for the starting position of our autonomous command
	SendableChooser<AutoPreference> preferenceChooser = new SendableChooser<>(); // creates a sendable chooser for the scoring objective of our autonomous command
	SendableChooser<AutoCubes> cubesChooser = new SendableChooser<>(); // creates a sendable chooser for the amount of cubes to be scored by our autonomous command
	
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


	@Override
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
		SmartDashboard.putData("Auto Mode", chooser);
		SmartDashboard.putData("Auto Position", positionChooser);
		SmartDashboard.putData("Auto Preference", preferenceChooser);
		SmartDashboard.putData("Auto Cubes", cubesChooser);
		SmartDashboard.putData("Reset Gyro", new ResetGyro());
	}


	@Override
	public void autonomousInit() 
	{
	    AutoPosition position = positionChooser.getSelected();
	    System.err.println("position = " + position);
	    AutoPreference preference = preferenceChooser.getSelected();
	    System.err.println("preference = " + preference);
	    AutoCubes cubes = cubesChooser.getSelected();
	    System.err.println("cubes = " + cubes);
	    String classNameString = ("AutoPathSelector." + position.getName() + preference.getName() + "." + cubes.getName());
	    System.err.println(classNameString);
		GamedataFetcher gamedata = new GamedataFetcher();

		if (position.getName() == "Middle")
		{
		    if (preference.getName() == "Switch")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide);
		    }
		    if (preference.getName() == "Scale")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = new AutoPathSelector.MiddleScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = new AutoPathSelector.MiddleScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = new AutoPathSelector.MiddleScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = new AutoPathSelector.MiddleScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = new AutoPathSelector.MiddleScale.One(gamedata.scaleSide);
		    }
		 }
		if (position.getName() == "Left")
		{
		    if (preference.getName() == "Switch")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = new AutoPathSelector.LeftSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = new AutoPathSelector.LeftSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = new AutoPathSelector.LeftSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = new AutoPathSelector.LeftSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "OneTwo")
					autonomousCommand = new AutoPathSelector.LeftSwitch.One(gamedata.switchSide);
			}
		    if (preference.getName() == "Scale")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = new AutoPathSelector.LeftScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = new AutoPathSelector.LeftScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = new AutoPathSelector.LeftScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = new AutoPathSelector.LeftScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "OneTwo")
					autonomousCommand = new AutoPathSelector.LeftScale.One(gamedata.scaleSide);
		    }
		}
		if (position.getName() == "Right")
		{
			if (preference.getName() == "Switch")
			{
		    	if (cubes.getName() == "One")
		    		autonomousCommand = new AutoPathSelector.RightSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = new AutoPathSelector.RightSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = new AutoPathSelector.RightSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = new AutoPathSelector.RightSwitch.One(gamedata.switchSide);
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = new AutoPathSelector.RightSwitch.One(gamedata.switchSide);
			}
			if (preference.getName() == "Scale")
			{
		    	if (cubes.getName() == "One")
		    		autonomousCommand = new AutoPathSelector.RightScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = new AutoPathSelector.RightScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = new AutoPathSelector.RightScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = new AutoPathSelector.RightScale.One(gamedata.scaleSide);
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = new AutoPathSelector.RightScale.One(gamedata.scaleSide);
			}
		}

		 
		if (preference.getName() == "Baseline") 
			autonomousCommand = new AutoPathSelector.Baseline();
		
		Robot.drivetrain.resetEncoders();
		Robot.intake.clampIn();
		Robot.drivetrain.navx.zeroYaw();
		Robot.drivetrain.shiftIn();
		if (autonomousCommand != null)
			autonomousCommand.start();
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
		/**
		* This makes sure that the autonomous stops running when
		* teleop starts running. If you want the autonomous to
		* continue until interrupted by another command, remove
		*/
		Robot.drivetrain.navx.zeroYaw();
		Robot.drivetrain.resetEncoders();
		Robot.lift.resetEncoders();
		if (autonomousCommand != null)   
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Enc value left drive", Robot.drivetrain.getEncoderRawLeft());
		SmartDashboard.putNumber("Enc value right drive", Robot.drivetrain.getEncoderRawRight());
		SmartDashboard.putNumber("Enc value left lift", Robot.lift.leftMaster.getSelectedSensorPosition());
		SmartDashboard.putNumber("Enc value right Lift", Robot.lift.rightMaster.getSelectedSensorPosition());
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
