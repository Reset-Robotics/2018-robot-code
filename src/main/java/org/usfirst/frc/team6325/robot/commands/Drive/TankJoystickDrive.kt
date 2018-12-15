package org.usfirst.frc.team6325.robot.commands.Drive

import org.sertain.command.*
import org.usfirst.frc.team6325.robot.Polybius


class TankJoystickDrive : Command()
{
    init { requires Polybius.drivetrain }

    override fun execute()
    {
        val sliderModifier = 1 - ((Polybius.oi.sliderRawAxisNumber + 1)/2)
        System.err.println("SliderModifer is " + sliderModifier);
		val left = Robot.oi.drivetrainLeft * sliderModifier;
		val right = Robot.oi.drivetrainRight * sliderModifier;
        Polybius.drivetrain.drive(left, right);
    }

    override fun isCanceled() = Polybius.drivetrain.killMotors()
    override fun onDestroy() = Polybius.drivetrain.killMotors()
}