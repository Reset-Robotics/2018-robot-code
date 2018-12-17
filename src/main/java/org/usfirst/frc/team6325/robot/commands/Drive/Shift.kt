package org.usfirst.frc.team6325.commands.Drive

import org.sertain
import org.usfirst.frc.team6325.robot.Polybius


public class Shift : Command(state: String)
{
    fun Shift() = requires(Polybius.drivetrain)

    override fun execute() = Polybius.drivetrain.shift(state)
    override fun isCompleted() = return true
}