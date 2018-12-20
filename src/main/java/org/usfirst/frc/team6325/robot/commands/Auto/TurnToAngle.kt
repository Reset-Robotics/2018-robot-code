package org.usfirst.frc.team6325.robot.commands.Auto

import org.usfirst.frc.team6325.robot.Polybius
import org.sertain.command.Command


public class TurnToAngle : Command
{
    var ang: Double = 0.0; var diff: Double = 0.0; var rate: = Double = 0.3
    var done: Boolean = false

    public fun TurnToAngle(angle: Double)
    {
        requires(Polybius.drivetrain)
        ang = angle
    }

    init()
    {
        diff = ang - Polybius.drivetrain.getAngle()
        
        if(diff > 0)
            Polybius.drivetrain.drive(rate, -rate)
        else
            Polybius.drivetrain.drive(-rate, rate)
    }

    override fun execute()
    {
        if (diff > 0)
        {
            if (Polybius.drivetrain.getAngle() > ang)
                done = true
        }
        else
        {
            if (ang > Polybius.drivetrain.getAngle())
                done = true
        }
    }
    
    override fun isCompleted(): Boolean = return done
    override fun isCanceled() = onDestroy()

    override fun onDestroy()
    {
        Polybius.drivetrain.autoTurnStop()
        Polybius.drivetrain.killMotors()
    }
}