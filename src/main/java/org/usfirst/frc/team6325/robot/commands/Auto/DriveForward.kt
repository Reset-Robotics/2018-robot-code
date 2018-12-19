package org.usfirst.frc.team6325.robot.commands.Auto

import org.usfirst.frc.team6325.robot.subsystems.Drivetrain.MotionProfiling
import edu.wpi.first.wpilibj.PIDController
import org.sertain.command.Command


public class DriveForward : Command
{
    public val TURN_SPEED: Double = null
    var moveLeftSpeed: Double = null; var moveRightSpeed: Double = null
    var distance: Double = null; var angle: Double = null
    var moveLeftController: PIDController; var moveRightController: PIDController; var angleController: PIDController

    public fun DriveForward(dist: Double) = distance = dist

    fun ticksToInches(ticks: Double): Double = return (ticks / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference
    fun inchesToTicks(inches: Double): Double = return (inches / MotionProfiling.ticks_per_rev) * MotionProfiling.wheel_circumference
    init() = var targetTicks: Double = inchesToTicks(distance)
    override fun isCompleted() = return false
}