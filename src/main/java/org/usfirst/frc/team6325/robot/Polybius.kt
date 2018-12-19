package org.usfirst.frc.team6325.robot

import org.sertain.*
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.livewindow.LiveWindow
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser
import org.usfirst.frc.team6325.robot.commands.Auto.AutoPathSelector
import org.usfirst.frc.team6325.robot.commands.Auto.GamedataFetcher
import org.usfirst.frc.team6325.robot.commands.Drive.ArcadeJoystickDrive
import org.usfirst.frc.team6325.robot.commands.Drive.ResetGyro
import org.usfirst.frc.team6325.robot.subsystems.*


class Polybius : Robot() 
{
	// Subsystem Initialization
	public val backBelts: BackBelts = BackBelts()
    public val drivetrain: Drivetrain = Drivetrain()
    public val intake: Intake = Intake()
    public val lift: Lift = Lift()
    public val liftIntake: LiftIntake = LiftIntake()
	// Miscellaneous Variables
    public var start: Double
    public var time: Double
	// Autonomous Command/Chooser Initialization
    public var autonomousCommand: Command
    public var chooser: SendableChooser<Command> = SendableChooser<Command>()
    public var positionChooser: SendableChooser<Command> = SendableChooser<Command>()
    public var preferenceChooser: SendableChooser<Command> = SendableChooser<Command>()
    public var cubesChooser: SendableChooser<Command> = SendableChooser<Command>()

	// OI Initialization
    public var oi: OI = OI()

    // WPILib robotInit()
    override fun onCreate() 
    {
		chooser.addDefault("Default Auto", ArcadeJoystickDrive())
		positionChooser.addDefault(AutoPosition.MIDDLE.getName(), AutoPosition.MIDDLE)
		positionChooser.addObject(AutoPosition.LEFT.getName(), AutoPosition.LEFT)
		positionChooser.addObject(AutoPosition.RIGHT.getName(), AutoPosition.RIGHT)
		preferenceChooser.addDefault(AutoPreference.SWITCH.getName(), AutoPreference.SWITCH)
		preferenceChooser.addObject(AutoPreference.SCALE.getName(), AutoPreference.SCALE)
		preferenceChooser.addObject(AutoPreference.SIMPLE.getName(), AutoPreference.SIMPLE)
		preferenceChooser.addObject(AutoPreference.BASELINE.getName(), AutoPreference.BASELINE)
		cubesChooser.addDefault(AutoCubes.ONE.getName(), AutoCubes.ONE)
		cubesChooser.addObject(AutoCubes.TWO.getName(), AutoCubes.TWO)
		cubesChooser.addObject(AutoCubes.THREE.getName(), AutoCubes.THREE)
		cubesChooser.addObject(AutoCubes.ONEONE.getName(), AutoCubes.ONEONE)
		cubesChooser.addObject(AutoCubes.ONETWO.getName(), AutoCubes.ONETWO)

		SmartDashboard.putData("Auto Mode", chooser)
		SmartDashboard.putData("Auto Position", positionChooser)
		SmartDashboard.putData("Auto Preference", preferenceChooser)
		SmartDashboard.putData("Auto Cubes", cubesChooser)
        SmartDashboard.putData("Reset Gyro", ResetGyro())
    }

    // WPILib disabledPeriodic()
    override fun executeDisabled()
    {
        Scheduler.getInstance().run()
        SmartDashboard.putData("Auto Mode", chooser)
		SmartDashboard.putData("Auto Position", positionChooser)
		SmartDashboard.putData("Auto Preference", preferenceChooser)
		SmartDashboard.putData("Auto Cubes", cubesChooser)
        SmartDashboard.putData("Reset Gyro", by lazy { ResetGyro() })
    }

    // WPILib autonomousInit()
    override fun onAutoStart()
    {
        var position: AutoPosition = positionChooser.getSelected()
        System.err.println("position = " + position)
        var preference: AutoPosition = preferenceChooser.getSelected()
	    System.err.println("preference = " + preference)
        var cubes: AutoPosition = cubesChooser.getSelected()
	    System.err.println("cubes = " + cubes);
	    var classNameString: String = ("AutoPathSelector." + position.getName() + preference.getName() + "." + cubes.getName())
	    System.err.println(classNameString)
		val gamedata by lazy { GamedataFetcher() }

        if (position.getName() == "Middle")
		{
		    if (preference.getName() == "Switch")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleSwitch.One(gamedata.switchSide) }
		    }
		    if (preference.getName() == "Scale")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = by lazy { AutoPathSelector.MiddleScale.One(gamedata.scaleSide) }
		    }
		 }
		if (position.getName() == "Left")
		{
		    if (preference.getName() == "Switch")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "OneTwo")
					autonomousCommand = by lazy { AutoPathSelector.LeftSwitch.One(gamedata.switchSide) }
			}
		    if (preference.getName() == "Scale")
		    {
		    	if (cubes.getName() == "One")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = by lazy { AutoPathSelector.LeftScale.One(gamedata.scaleSide) }
		    	if (cubes.getName() == "OneTwo")
					autonomousCommand = by lazy { AutoPathSelector.LeftScale.One(gamedata.scaleSide) }
		    }
		}
		if (position.getName() == "Right")
		{
			if (preference.getName() == "Switch")
			{
		    	if (cubes.getName() == "One")
		    		autonomousCommand = by lazy { AutoPathSelector.RightSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = by lazy { AutoPathSelector.RightSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = by lazy { AutoPathSelector.RightSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = by lazy { AutoPathSelector.RightSwitch.One(gamedata.switchSide) }
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = by lazy { AutoPathSelector.RightSwitch.One(gamedata.switchSide) }
			}
			if (preference.getName() == "Scale")
			{
		    	if (cubes.getName() == "One")
		    		autonomousCommand = AutoPathSelector.RightScale.One(gamedata.scaleSide)
		    	if (cubes.getName() == "Two")
		    		autonomousCommand = AutoPathSelector.RightScale.One(gamedata.scaleSide)
		    	if (cubes.getName() == "Three")
		    		autonomousCommand = AutoPathSelector.RightScale.One(gamedata.scaleSide)
		    	if (cubes.getName() == "OneOne")
		    		autonomousCommand = AutoPathSelector.RightScale.One(gamedata.scaleSide)
		    	if (cubes.getName() == "OneTwo")
		    		autonomousCommand = AutoPathSelector.RightScale.One(gamedata.scaleSide)
            }
        }
        if (preference.getName() == "Baseline") 
			autonomousCommand = AutoPathSelector.Baseline()
		
		Polybius.drivetrain.resetEncoders()
		Polybius.intake.clampIn()
		Polybius.drivetrain.navx.zeroYaw()
		Polybius.drivetrain.shiftIn()
		if (autonomousCommand != null)
            autonomousCommand.start()
    }

    // WPILib autonomousPeriodic()
    override fun executeAuto()
    {
        Scheduler.getInstance().run()
        SmartDashboard.putNumber("Enc value left drive", Polybius.drivetrain.getEncoderRawLeft())
		SmartDashboard.putNumber("Enc value right drive", Polybius.drivetrain.getEncoderRawRight())
        SmartDashboard.putNumber("Gyro Yaw", Polybius.drivetrain.navx.getYaw())
    }

    // WPILib telopInit()
    override fun onStart()
    {
		Polybius.drivetrain.navx.zeroYaw()
		Polybius.drivetrain.resetEncoders()
		Polybius.lift.resetEncoders()
		if (autonomousCommand != null)   
            autonomousCommand.cancel()
    }

    // WPILib telopPeriodic()
    override fun executeTeleop()
    {
		Scheduler.getInstance().run()
		SmartDashboard.putNumber("Enc value left drive", Polybius.drivetrain.getEncoderRawLeft())
		SmartDashboard.putNumber("Enc value right drive", Polybius.drivetrain.getEncoderRawRight())
		SmartDashboard.putNumber("Enc value left lift", Polybius.lift.leftMaster.getSelectedSensorPosition())
		SmartDashboard.putNumber("Enc value right Lift", Polybius.lift.rightMaster.getSelectedSensorPosition())
		SmartDashboard.putNumber("Gyro Yaw", Polybius.drivetrain.navx.getYaw())
        SmartDashboard.putNumber("Gyro Angle", Polybius.drivetrain.navx.getAngle())
    }
}