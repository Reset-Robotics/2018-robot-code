package org.usfirst.frc.team6325.robot.commands.Auto

import org.usfirst.frc.team6325.robot.Polybius
import org.sertain.command.Command


public class MoveDistance : Command
{
    val dist: Double = null; val spd: Double = null
    val done: Boolean = false
    var start: Double = null; var time: Double = null; var timeout: Double = null

    public fun MoveDistance(meters: Double, speed: Double, timeout: Double)
    {
        dist = meters
        spd = speed
        this.timeout = timeout
        requires(Polybius.drivetrain)
    }

    init()
    {
        Polybius.drivetrain.resetEncoders()
        Polybius.drivetrain.drive(spd, spd)
        start = System.currentTimeMillis()
    }

    override fun execute()
    {
        time = System.currentTimeMillis()
        if (Polybius.drivetrain.getEncoderDistanceMetersLeft() >= dist || time-start >= timeout)
        {
            done = true
            Polybius.drivetrain.killMotors()
        }
    }

    override fun onDestroy()
    {
        Polybius.drivetrain.killMotors()
        Polybius.drivetrain.resetEncoders()
    }

    override fun isCanceled() = onDestroy()
}