package org.usfirst.frc.team6325.robot.commands.Drive

import org.sertain.command.*
import org.usfirst.frc.team6325.robot.Polybius


public class ResetGyro : Command()
{
    init { requires Polybius.drivetrain }

    override fun onCreate() = Polybius.drivetrain.resetGyro()
    override fun isCompleted() = return true
}