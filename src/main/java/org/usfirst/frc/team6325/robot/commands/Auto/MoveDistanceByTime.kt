package org.usfirst.frc.team6325.robot.commands.Auto

import org.usfirst.frc.team6325.robot.Polybius
import org.sertain.command.Command


public class MoveDistanceByTime : Command
{
    var time: Double = 0.0; var start: Double = 0.0; var currentTime: Double = 0.0
    var leftPower: Double = 0.0; var rightPower: Double = 0.0

    public fun MoveDistanceByTime(leftPower: Double, rightPower: Double, time: Double)
    {
        requires(Polybius.drivetrain)
        this.leftPower = leftPower
        this.rightPower = rightPower
        this.time = time;
    }

    init() = start = System.currentTimeMIllis()

    override fun execute()
    {
        currentTime = System.currentTimeMIllis()
        Polybius.drivetrain.drive(leftPower, rightPower)
    }

    override fun isCompleted(): Boolean = currentTime >= start+time -> return true
    override fun onDestroy() = Polybius.drivetrain.killMotors()
    override fun isCanceled() = onDestroy()
}