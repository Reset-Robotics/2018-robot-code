package org.usfirst.frc.team6325.robot.subsystems

import org.sertain.command.Subsystem
import org.usfirst.frc.team6325.robot.IDs
import org.usfirst.frc.team6325.robot.commands.Lift.JoystickLiftIntake
import com.ctre.phoenix.motorcontrol.can.Spark;


object LiftIntake : Subsystem()
{
    val liftIntakeWheels by lazy { Spark(IDs.pwmMotorIDs.get("Lift-Intake-Wheels")) }

    fun spinLiftIntake(pow: Double) = liftIntakeWheels.set(pow)

    override fun initDefaultCommand() = setDefaultCommand by lazy { JoystickLiftIntake() }
}