package org.usfirst.frc.team6325.robot.commands.Lift

import org.sertain.command.*
import org.usfirst.frc.team6325.robot.Polybius


class JoystickLiftIntake : Command()
{
    fun JoystickBackIntake() = requires(Polybius.liftIntake)

    override fun execute() = Polybius.liftIntake.spinLiftIntake(Polybius.oi.xboxController.getRawAxis(IDs.xboxIDs.get("Right-Joystick-Y-Axis")))

    override fun isCompleted() = return false
}