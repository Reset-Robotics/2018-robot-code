package org.usfirst.frc.team6325.robot.commands.Drive

import org.usfirst.frc.team6325.robot.Polybius
import org.sertain.command.Command
import jaci.pathfinder.followers.EncoderFollower


public class ProfileFollower : Command
{
    var leftCSV: String = null
    var rightCSV: String = null
    var followers[]: EncoderFollower[] = null

    public fun ProfileFollower(leftCSV: String, rightCSV: String)
    {
        leftCSV = this.leftCSV
        rightCSV = this.rightCSV
        setInterruptible(false)
        followers = Polybius.drivetrain.initPath(leftCSV, rightCSV)
    }

    init { Polybius.drivetrain.executePath(followers, false) }

    override fun execute() = Polybius.drivetrain.executePath(followers, true)
    override fun onDestroy() = Polybius.drivetrain.drive(0, 0)
    override fun isCanceled() = onDestroy()
    override fun isCompleted() = return Polybius.drivetrain.getIsProfileFinished()
}