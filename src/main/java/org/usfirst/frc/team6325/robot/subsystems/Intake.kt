package org.usfirst.frc.team6325.robot.subsystems

import org.sertain.command.*
import org.usfirst.frc.team6325.robot.IDs
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;

object Intake : Subsystem()
{
    val leftIntakeWheel by lazy { Spark(IDs.pwmMotorIDs.get("Left-Intake-Wheel")) }
    val rightIntakeWheel by lazy { Spark(IDs.pwmMotorIDs.get("Right-Intake-Wheel")) }
    private val intakePiston by lazy { DoubleSolenoid(IDs.intakeSolenoidPorts.get("Left-Port"), IDs.intakeSolenoidPorts.get("Right-Port")) }

    fun setIntakePower(power: Double)
    {
        leftIntakeWheel.set(power)
        rightIntakeWheel.set(power)
    }
    
    fun clampIn() = intakePiston.set(Value.kForward)
    fun clampOut() = intakePiston.set(Value.kReverse)

    fun toggleClamp()
    {
        if(intakePiston.get() == Value.kForward)
            intakePiston.set(Value.kReverse)
        else
            intakePiston.set(Value.kForward)
    }

    override fun initDefaultCommand() { }
}