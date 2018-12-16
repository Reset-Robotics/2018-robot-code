package org.usfirst.frc.team6325.robot.subsystems

import org.sertain.command.Subsystem
import org.usfirst.frc.team6325.robot.Polybius
import org.usfirst.frc.team6325.robot.IDs
import org.usfirst.frc.team6325.robot.commands.Drive.TankJoystickDrive
import com.ctre.phoenix.motorcontrol.*
import com.kanuailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


class Drivetrain : Subsystem()
{
    fun Drivetrain()
    {
        val driveLeftMaster by lazy { WPI_TalonSRX(IDs.driveMotorsIDs.get("Left-Master")) }
        val driveLeftBack by lazy { WPI_TalonSRX(IDs.driveMotorsIDs.get("Left-Back")) }
        val driveLeftFront by lazy { WPI_TalonSRX(IDs.driveMotorsIDs.get("Left-Front")) }
        val driveRightMaster by lazy { WPI_TalonSRX(IDs.driveMotorsIDs.get("Right-Master")) }
        val driveRightBack by lazy { WPI_TalonSRX(IDs.driveMotorsIDs.get("Right-Back")) }
        val driveRightFront by lazy { WPI_TalonSRX(IDs.driveMotorsIDs.get("Right-Front")) }
        val navx by lazy { AHRS(SPI.Port.kMXP) }
        val shifter by lazy { DoubleSolenoid(IDs.shifterSolenoidPorts.get("Left-Port"), IDs.shifterSolenoidPorts.get("Right-Port")) }
        var isHighGear: Boolean
        val timer by lazy { Timer() }
        var isProfileFinished: Boolean = false

        // PID values for turning to angles
        val turnP: Double = 0.006
        val turnI: Double = 0.0
        val turnD: Double = 0.0
        val turnF: Double = 0.0
        val turnThreshold: Double = 5.0 // how many degrees the robot has to be within for it to stop looking for the required angle
        val turnRate: Double = 0.0

        val turnController by lazy { PIDController(turnP, turnI, turnD, turnF, navx, this) }


        // Set slave motors to follow masters
        this.driveLeftBack.follow(driveLeftMaster)
        this.driveLeftFront.follow(driveLeftMaster)
        this.driveRightBack.follow(driveRightMaster)
        this.driveRightFront.follow(driveRightMaster)
        
        // Set up encoders
        this.driveLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        this.driveRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        
        // Inverted motors
        this.driveRightMaster.setInverted(true);
		this.driveRightBack.setInverted(true);
		this.driveRightFront.setInverted(true);
		this.driveLeftMaster.setInverted(false);
		this.driveLeftBack.setInverted(false);
		this.driveLeftFront.setInverted(true);
        this.driveRightMaster.setInverted(false);

        // Set Talon Mode
        this.driveLeftMaster.setNeutralMode(NeutralMode.Brake);
        this.driveRightMaster.setNeutralMode(NeutralMode.Brake);
		// Current Limiting
		this.driveLeftMaster.configContinuousCurrentLimit(40,0); // desired current after limit
		this.driveLeftMaster.configPeakCurrentLimit(35, 0); // max current
		this.driveLeftMaster.configPeakCurrentDuration(100, 0); // how long after max current to be limited (ms)
		this.driveLeftMaster.enableCurrentLimit(true);
		this.driveRightMaster.configContinuousCurrentLimit(40,0); // desired current after limit
		this.driveRightMaster.configPeakCurrentLimit(35, 0); // max current
		this.driveRightMaster.configPeakCurrentDuration(100, 0); // how long after max current to be limited (ms)
		this.driveRightMaster.enableCurrentLimit(true);
		navx.reset();
		navx.zeroYaw();
        resetEncoders();
    }

    fun drive(leftVal: Double, rightVal: Double)
    {
        driveLeftMaster.set(leftVal)
        driveRightMaster.set(rightVal)
    }

    fun killMotors()
    {
        driveLeftMaster.set(0)
        driveRightMaster.set(0)
    }

    fun shiftIn() = shifter.set(Value.kForward)

    fun shiftOut() = shifter.set(Value.kReverse)

    fun shift()
    {
        if(shifter.get()==Value.kForward)
        {
            shifter.set(Value.kReverse)
            isHighGear = false
        }
        else
        {
            shifter.set(Value.kForward)
            isHighGear = true;
        }
    }

    fun isHighGear(): Boolean = return isHighGear

    fun resetGyro() = navx.zeroYaw()

    fun getLeftVelocity(): Double =  return (driveLeftMaster.getSelectedSensorVelocity(0) * Math.PI * MotionProfiling.wheel_diameter) / (MotionProfiling.ticks_per_rev) * 10
    fun getLeftVelocity(): Double =  return (driveRightMaster.getSelectedSensorVelocity(0) * Math.PI * MotionProfiling.wheel_diameter) / (MotionProfiling.ticks_per_rev) * 10

    fun resetEncoders()
    {
        this.driveLeftMaster.setSelectedSensorPosition(0, 0, 0);
        this.driveRightMaster.setSelectedSensorPosition(0, 0, 0);
    }

    fun getEncoderDistanceMetersLeft(): Double =  return (driveLeftMaster.getSelectedSensorPosition(0) * Math.PI * MotionProfiling.wheel_diameter) / MotionProfiling.ticks_per_rev;
    fun getEncoderDistanceMetersRight(): Double =  return (driveRightMaster.getSelectedSensorPosition(0) * Math.PI * MotionProfiling.wheel_diameter) / MotionProfiling.ticks_per_rev;

    fun getAngle(): Float = return navx.getYaw();

    fun getEncoderRawLeft(): Double = return driveLeftMaster.getSelectedSensorPosition(0)
    fun getEncoderRawRight(): Double = return driveRightMaster.getSelectedSensorPosition(0)

    fun turnToAngle(angle: Double): Double
    {
        val error = angle - navx.getAngle()
        val turn = 1.5 * (-1.0/80.0) * error
        this.driveLeftMaster.set(ControlMode.PercentOutput, turn)
        this.driveRightMaster.set(ControlMode.PercentOutput, -turn)
        SmartDashboard.putNumber("turn to angle error", error)
        Math.abs(error) <= 5 -> return Math.abs(error)
    }

    fun generateHashCode(path: Waypoint[]): Double
    {
        var hash: Double = 1.0
        for (i in 1..path.length)
            hash = ((path[i].x * 6) + (path[i].y * 3) + (path[i].angle * 25))
        return Math.abs(hash * 100)
    }

    fun initPath(leftCSV: String, rightCSV: String): EncoderFollower[]
    {
        val leftMotionProfile by lazy { File(leftCSV) }
        val rightMotionProfile by lazy { File(rightCSV) }
        System.err.println("Path file about to read")
        var leftTrajectory: Trajectory = Pathfinder.readFromCSV(leftMotionProfile)
        var rightTrajectory: Trajectory = Pathfinder.readFromCSV(rightMotionProfile)
        System.err.println("Path file successfully read")
        private val left by lazy { EncoderFollower(leftTrajectory) }
        private val right by lazy { EncoderFollower(rightTrajectory) }
        left.configureEncoder(driveLeftMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter)
        right.configureEncoder(driveRightMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        left.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        right.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        navx.zeroYaw()
        return by lazy { EncoderFollower[]{left, right,}; }
    }

    fun initPath(path: Waypoint[]): EncoderFollower[]
    {
        private var left: EncoderFollower by lazy { EncoderFollower() }
        private var right: EncoderFollower by lazy { EncoderFollower() }
        private val cfg by lazy { Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, Drivetrain.MotionProfiling.dt, 
        Drivetrain.MotionProfiling.max_velocity, Drivetrain.MotionProfiling.max_acceleration, Drivetrain.MotionProfiling.max_jerk); }
        var pathHash: String = String.valueOf(generateHashCode(path))
        SmartDashboard.putString("Path Hash", pathHash)
        var toFollow: Trajectory
        val trajectory by lazy { File("/home/lvuser/paths/" + pathHash + ".csv") }

        if (!trajectory.exists())
        {
            toFollow = Pathfinder.generate(path, cfg)
            Pathfinder.writeToCSV(trajectory, toFollow)
            System.out.println(pathHash + ".csv not found, wrote to file")
        }
        else
        {
            System.out.println(pathHash + ".csv read from file")
            toFollow = Pathfinder.readFromCSV(trajectory)
        }

        val modifier by lazy { TankModifier(toFollow).modify(Drivetrain.MotionProfiling.wheel_base_width) }
        left by lazy EncoderFollower(modifier.getLeftTrajectory())
        right by lazy EncoderFollower(modifier.getRightTrajectory())
        left.configureEncoder(driveLeftMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        right.configureEncoder(driveRightMaster.getSelectedSensorPosition(0), MotionProfiling.ticks_per_rev, MotionProfiling.wheel_diameter);
        left.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        right.configurePIDVA(MotionProfiling.kp, MotionProfiling.ki, MotionProfiling.kd, MotionProfiling.kv, MotionProfiling.ka);
        return by lazy { EncoderFollower[]{left, right,}; }
    }

    fun executePath(followers: EncoderFollower[], reverse: boolean)
    {
        private var left: EncoderFollower = followers[0]
        private var right: EncoderFollower = followers[1]
        private var l: Double
        private var r: Double
        
        if (!reverse)
        {
            l = left.calculate(driveLeftMaster.getSelectedSensorPosition(0))
            r = right.calculate(driveRightMaster.getSelectedSensorPosition(0))
        }
        else
        {
            l = left.calculate(-driveLeftMaster.getSelectedSensorPosition(0))
            r = right.calculate(-driveRightMaster.getSelectedSensorPosition(0))
        }

        val gyro_heading: Double = navx.getYaw()
        val angle_setpoint: Double = Pathfinder.r2d(left.getHeading())
        val angleDifference: Double = Pathfinder.boundHalfDegrees(angle_setpoint - gyro_heading)
        val turn: Double = 0.8 * (-1.0/80.0) * angleDifference
        if(!reverse)
            drive(l + turn, r - turn)
        else
            drive(-l + turn, -r - turn)

        if(left.isFinished() && right.isFinished())
            isProfileFinished = true
    }

    fun getIsProfileFinished(): Boolean = return isProfileFinished

    fun resetForPath()
    {
        isProfileFinished = false
        resetEncoders()
        resetGyro()
    }

    override fun initDefaultCommand()
    {
        if (Robot.oi.selectedDrivetrainCommand != null)
            setDefaultCommand(Robot.oi.selectedDrivetrainCommand)
        else
            setDefaultCommand by lazy { TankJoystickDrive() }
    }

    fun autoTurn() = drive(turnRate, -turnRate)

    fun autoTurnInit(angle: Double)
    {
        turnController.setInputRange(-180.0f, 180.0f)
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(turnThreshold);
        turnController.setContinuous(true);
        turnController.setSetpoint(angle);
        turnController.enable();
    }

    fun autoTurnStop() = turnController.disable()

    fun pidWrite(output: Double) = turnRate = output

    class MotionProfiling
    {
        val kp: Double = 1
        val ki: Double = 0.0
        val kd: Double = 0.05

        val max_velocity: Double = 7.0
        val kv: Double = 1 / max_velocity
        val max_acceleration: Double = 3.0
        val max_jerk: Double = 60.0
        val ka: Double = 0.0
        val wheel_diameter: Double = 0.5
        val wheel_base_width: Double = 27.11/12.0
        val wheel_circumference: Double = 6*Math.PI
        val ticks_per_rev: Double = 4096*7
        val distancePerPulse: Double = (wheel_diameter*Math.PI)/ticks_per_rev
        val dt: Double = 0.05
    }
}