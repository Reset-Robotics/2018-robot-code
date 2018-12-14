package org.usfirst.frc.team6325.robot.commands.Drive;

import java.io.File;
import org.usfirst.frc.team6325.robot.Robot;
import org.usfirst.frc.team6325.robot.subsystems.Drivetrain;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;


public class ProfileFollowerUpdate extends Command 
{
    private final PWMTalonSRX leftMotor, rightMotor;
    private EncoderFollower left, right;
    private final Trajectory leftTra, rightTra;


    public ProfileFollowerUpdate(String leftCSV, String rightCSV) 
    {
        requires(Robot.drivetrain);
        File leftMotionProfile = new File(leftCSV);
        File rightMotionProfile = new File(rightCSV);

        leftMotor = Robot.drivetrain.leftDriveMaster;
        rightMotor = Robot.drivetrain.rightDriveMaster;
        leftTra = Pathfinder.readFromCSV(leftMotionProfile);
        rightTra = Pathfinder.readFromCSV(rightMotionProfile);
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() 
    {
        super.initialize();
        Robot.drivetrain.navx.zeroYaw();
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.shiftOut();
        left = new EncoderFollower(leftTra);
        right = new EncoderFollower(rightTra);

        left.configureEncoder((int) Math.round(leftMotor.getPosition()), 30000, 0.5);
        right.configureEncoder((int) Math.round(rightMotor.getPosition()), 30000, 0.5);

        double max_velocity = 1.0 / 4.0;
        left.configurePIDVA(0.4, 0.0, 0.07, max_velocity, 0);
        right.configurePIDVA(0.4, 0.0, 0.07, max_velocity, 0);
    }

    /**
     * Called when the command ended peacefully. This is where you may want to wrap up loose ends,
     * like shutting off a motor that was being used in the command.
     */
    @Override
    protected void end() 
    {
        super.end();
        Robot.drivetrain.drive(0, 0);
    }

    /**
     * Called when the command ends because somebody called {@link Command#cancel() cancel()} or
     * another command shared the same requirements as this one, and booted it out.
     * <p>
     * <p>This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * <p>
     * <p>Generally, it is useful to simply call the {@link Command#end() end()} method within this
     * method, as done here.
     */
    @Override
    protected void interrupted() 
    {
        super.interrupted();
        end();
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() 
    {
        super.execute();
        //System.err.println("Execute ProfileFollower.");
       // DriveTrain._leftMain.configOpenloopRamp(0, 500);
        //DriveTrain._rightMain.configOpenloopRamp(0, 500);
        double l = left.calculate((int) Math.round(leftMotor.getPosition()));
        double r = right.calculate((int) Math.round(rightMotor.getPosition()));
        double gyro_heading = Robot.drivetrain.navx.getAngle();
        double desired_heading = Pathfinder.r2d(left.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        System.out.println("Desired angle  " + desired_heading + "Current heading " + gyro_heading + "Angle difference " + angleDifference);
        double turn = 1.2  * (-1.0/80.0) * angleDifference;
        System.out.println("Left: " + (l));
        System.out.println("Right: " + (r));
        System.out.println("Left + turn: " + (l+turn));
        System.out.println("Right + turn: " + (r-turn));
        Robot.drivetrain.drive(l+turn, r-turn);
    }

    @Override
    protected boolean isFinished() 
    {
        return left.isFinished() && right.isFinished();
    }
}