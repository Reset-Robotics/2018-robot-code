package org.usfirst.frc.team6325.robot.commands.Drive

import org.usfirst.frc.team6325.robot.Polybius
import org.sertain.command.Command
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower


public class WaypointFollower : Command 
{
	var path: Waypoint[] = null
	var followers: EncoderFollower[] = null

	public fun WaypointFollower(path: Waypoint[]) 
	{
		 this.path = path
	     setInterruptible(false)
	     followers = Polybius.drivetrain.initPath(path)
	}

	init 
	{
		Polybius.drivetrain.resetForPath()
		Polybius.drivetrain.executePath(followers, false)
	}

	override fun execute() = Polybius.drivetrain.executePath(followers, false)
	override fun onDestroy() = Polybius.drivetrain.drive(0, 0)
	override fun isCanceled() = onDestroy()
    override fun isCompleted(): Boolean = return Polybius.drivetrain.getIsProfileFinished()
}