package org.usfirst.frc.team6325.robot.subsystems

import org.sertain.command.Subsystem
import org.usfirst.frc.team6325.robot.commands.Lift.JoystickBackIntake
import org.usfirst.frc.team6325.robot.IDs
import edu.wpi.first.wpilibj.Spark


object BackBelts : Subsystem()
{
    val backBelts by lazy { Spark(IDs.pwmMotorIDs.get("Back-Belts")) }

    fun moveBackBelts(pow: Double) = backBelts.set(pow)

    override fun initDefaultCommand() = setDefaultCommand by lazy { JoystickBackIntake() }
}