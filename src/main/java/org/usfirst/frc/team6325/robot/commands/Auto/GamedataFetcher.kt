package org.usfirst.frc.team6325.robot.commands.Auto

import edu.wpi.first.wpilibj.DriverStation


public class GamedataFetcher
{
    public var switchSide: Char = ' '
    public var scaleSide: Char = ' ' 
    public var gameData: String = DriverStation.getInstance().getGameSpecificMessage()

    public fun GamedataFetcher()
    {
        try 
        {
            this.switchSide = gameData.charAt(0)
           	System.err.println("Switch Side = "+ switchSide)
           	this.scaleSide = gameData.charAt(1)
            System.err.println("Scale Side = "+ scaleSide)
        }
        catch(IndexOutOfBoundsException ex)
        { 
            System.out.println("No Game Data")
        }
    }
}