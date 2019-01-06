package org.usfirst.frc.team6325.robot.commands.Drive

import org.sertain.command.*
import org.usfirst.frc.team6325.robot.Polybius


public class ResetGyro : Command(), SendableBase
{
    init { requires Polybius.drivetrain }

    override fun execute() = Polybius.drivetrain.resetGyro()
    override fun onDestroy() = return true
}