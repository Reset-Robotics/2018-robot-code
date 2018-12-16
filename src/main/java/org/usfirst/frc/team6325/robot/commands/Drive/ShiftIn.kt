package org.usfirst.frc.team6325.commands.Drive

import org.sertain
import org.usfirst.frc.team6325.robot.Polybius


public class ShiftIn : Command()
{
    fun ShiftIn() = requires(Polybius.drivetrain)

    override fun execute() = Polybius.drivetrain.ShiftIn()
    override fun isCompleted() = return true
}