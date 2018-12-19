package org.usfirst.frc.team6325.robot.subsystems

import org.sertain.command.Subsystem
import org.usfirst.frc.team6325.robot.IDs
import org.usfirst.frc.team6325.robot.commands.Lift.JoystickLift
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode

class Lift : Subsystem()
{
    fun Lift()
    {
        val ids: IDs = IDs()

        val left: WPI_TalonSRX = WPI_TalonSRX((ids.liftMotorIDs.get("Left-Master"))!!)
        val right: WPI_TalonSRX = WPI_TalonSRX((ids.liftMotorIDs.get("Right-Master"))!!)
        private val CRUISE_VELOCITY: Int = 19000
        private val CRUISE_ACCELERATION: Int = 11000
        private val CRUISE_VELOCITY_DOWN: Double = CRUISE_VELOCITY * 0.7
        private val CRUISE_ACCELERATION_DOWN: Double = CRUISE_ACCELERATION * 0.6 
    }
}