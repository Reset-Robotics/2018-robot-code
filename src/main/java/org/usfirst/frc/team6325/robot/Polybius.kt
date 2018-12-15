package org.usfirst.frc.team6325.robot

import edu.wpi.first.wpilibj.CameraServer
import org.sertain;
import org.usfirst.frc.team6325.robot.subsystems.Drivetrain
import org.usfirst.frc.team6325.robot.Polybius

class Polybius : Robot() 
{
    public final Drivetrain drivetrain = new Drivetrain()
    //public final Lift lift = new Lift()
    //public final LiftIntake liftIntake = new LiftIntake()
    //public final Intake intake = new Intake()
    //public final BackBelts backBelts = new BackBelts()
    //public OI oi
    //public double start, time


    // WPILib robotInit()
    override fun onCreate() 
    {
        //oi = new OI();

    }

    // WPILib disabledPeriodic()
    override fun executeDisabled()
    {

    }

    // WPILib autonomousInit()
    override fun onAutoStart()
    {

    }

    // WPILib autonomousPeriodic()
    override fun executeAuto()
    {

    }

    // WPILib telopInit()
    override fun onStart()
    {

    }

    // WPILib telopPeriodic()
    override fun executeTeleop()
    {

    }
}