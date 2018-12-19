package org.usfirst.frc.team6325.robot.commands.Drive

import java.io.File;
import org.usfirst.frc.team6325.robot.Polybius
import com.ctre.phoenix.motorcontrol.can.*
import org.sertain.command.Command
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.followers.EncoderFollower


public class ProfileFollowerReverse : Command
{
    public fun ProfileFollowerReverse(leftCSV: String, rightCSV: String)
    {
        requires(Polybius.drivetrain)
        File leftMotionProfile = File(leftCSV)
        File rightMotionProfile = File(rightCSV)

        var leftMotor: WPI_TalonSRX = Polybius.drivetrain.driveLeftMaster
        var rightMotor: WPI_TalonSRX = Polybius.drivetrain.driveRightmaster
        var leftTra: Trajectory = Pathfinder.readFromCSV(leftMotionProfile)
        var rightTra: Trajectory = Pathfinder.readFromCSV(rightMotionProfile)
    }

    init
    {
        super.init()
        Polybius.drivetrain.navx.zeroYaw()
        Polybius.drivetrain.resetEncoders()
        Polybius.drivetrain.shift("low")
        var left: EncoderFollower = EncoderFollower(leftTra)
        var right: EncoderFollower = EncoderFollower(rightTra)

        left.configureEncoder(leftEncPosRounded, 4096*7, 0.5)
        right.configureEncoder(rightEncPosRounded, 4096*7, 0.5)

        val MAX_VELOCITY: Double = 1.0 / 4.0
        left.configurePIDVA(0.9, 0.0, 0.07, MAX_VELOCITY, 0)
        right.configurePIDVA(0.9, 0.0, 0.07, MAX_VELOCITY, 0)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Polybius.drivetrain.drive(0, 0)
    }

    override fun isCanceled()
    {
        super.isCanceled()
        onCompleted()
    }

    override fun execute()
    {
        super.execute()
        val l: Double = left.calculate((int) Math.round(-leftMotor.getSelectedSensorPosition()))
        val r: Double = right.calculate((int) Math.round(-rightMotor.getSelectedSensorPosition()))
        val GYRO_HEADING: Double = Polybius.drivetrain.navx.getAngle()
        val DESIRED_HEADING: Double = Pathfinder.r2d(left.getHeading())
        val ANGLE_DIFFERENCE: Double = Pathfinder.boundHalfDegrees(DESIRED_HEADING - GYRO_HEADING)
        println("Desired angle  " + DESIRED_HEADING + "Current heading " + GYRO_HEADING + "Angle difference " + ANGLE_DIFFERENCE)
        val turn: Double = -1 * 0.8 * (-1.0/80.0) * ANGLE_DIFFERENCE
        println("Left: " + (l))
        println("Right: " + (r))
        println("Left + turn: " + (l+turn))
        println("Right + turn: " + (r-turn))
        Polybius.drivetrain.drive(l+turn, r-turn)
    }

    override fun onCompleted() = return left.onCompleted() && right.onCompleted()
}