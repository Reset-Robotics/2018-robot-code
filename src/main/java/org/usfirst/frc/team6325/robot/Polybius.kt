package org.usfirst.frc.team6325.robot

import org.sertain.*
import org.sertain.command.Command
import org.sertain.util.SendableChooser
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.livewindow.LiveWindow
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team6325.robot.commands.Auto.AutoChooser.*
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
	// Autonomous Command/Chooser Initialization
    public lateinit var autonomousCommand: Command
    public var positionChooser = SendableChooser(
		AutoPosition.MIDDLE.getName() to AutoPosition.MIDDLE,
		AutoPosition.LEFT.getName() to AutoPosition.LEFT,
		AutoPosition.RIGHT.getName() to AutoPosition.RIGHT
	)
    public var preferenceChooser = SendableChooser(
		AutoPreference.SWITCH.getName() to AutoPreference.SWITCH,
		AutoPreference.SCALE.getName() to AutoPreference.SCALE,
		AutoPreference.SIMPLE.getName() to AutoPreference.SIMPLE,
		AutoPreference.BASELINE.getName() to AutoPreference.BASELINE
	)
    public var cubesChooser = SendableChooser(
		AutoCubes.ONE.getName() to AutoCubes.ONE,
		AutoCubes.TWO.getName() to AutoCubes.TWO,
		AutoCubes.THREE.getName() to AutoCubes.THREE,
		AutoCubes.ONEONE.getName() to AutoCubes.ONEONE,
		AutoCubes.ONETWO.getName() to AutoCubes.ONETWO
	)

	// OI Initialization
    public var oi: OI = OI()

    // WPILib robotInit()
    override fun onCreate() 
    {
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