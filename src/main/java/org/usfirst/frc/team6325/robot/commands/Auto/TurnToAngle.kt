package org.usfirst.frc.team6325.robot.commands.Auto

import org.usfirst.frc.team6325.robot.Polybius
import org.sertain.command.Command


public class TurnToAngle : Command
{
    var ang: Double = 0.0; var diff: Double = 0.0
    var done: Boolean = false

    public fun TurnToAngle(angle: Double)
    {
        requires(Polybius.drivetrain)
        ang = angle
    }

    init()
    {
        diff = ang - Polybius.drivetrain.getAngle()
        
    }
}