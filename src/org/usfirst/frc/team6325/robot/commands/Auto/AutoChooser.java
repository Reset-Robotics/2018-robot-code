package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoChooser 
{
	public static GamedataFetcher gamedata;
	public char switchGamedata = gamedata.switchSide;
	public char scaleGamedata = gamedata.scaleSide;
	
	
	
	public enum AutoPosition 
	{
	    LEFT("Left"),
	    MIDDLE("Middle"),
	    RIGHT("Right");

	    private final String name;

	    AutoPosition(String name) 
	    {
	    	this.name = name; 
	    }

	    public String getName() 
	    {
	        return name;
	    }
	}

	public enum AutoPreference 
	{
	    SCALE("Scale"),
	    SWITCH("Switch"),
		SIMPLE("Simple"),
		BASELINE("Baseline");

	    private final String name;

	    AutoPreference(String name)
	    {
	        this.name = name;
	    }

	    public String getName() 
	    {
	        return name;
	    }
	}
	
	public enum AutoCubes 
	{
	    ONE("One", new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide), new AutoPathSelector.LeftSwitch.One(gamedata.switchSide), new AutoPathSelector.RightSwitch.One(gamedata.switchSide), new AutoPathSelector.MiddleScale.One(gamedata.scaleSide), new AutoPathSelector.LeftScale.One(gamedata.scaleSide), new AutoPathSelector.RightScale.One(gamedata.scaleSide)),
	    TWO("Two", new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide), new AutoPathSelector.LeftSwitch.One(gamedata.switchSide), new AutoPathSelector.RightSwitch.One(gamedata.switchSide), new AutoPathSelector.MiddleScale.One(gamedata.scaleSide), new AutoPathSelector.LeftScale.One(gamedata.scaleSide), new AutoPathSelector.RightScale.One(gamedata.scaleSide)),
		THREE("Three", new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide), new AutoPathSelector.LeftSwitch.One(gamedata.switchSide), new AutoPathSelector.RightSwitch.One(gamedata.switchSide), new AutoPathSelector.MiddleScale.One(gamedata.scaleSide), new AutoPathSelector.LeftScale.One(gamedata.scaleSide), new AutoPathSelector.RightScale.One(gamedata.scaleSide)),
		ONEONE("OneOne", new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide), new AutoPathSelector.LeftSwitch.One(gamedata.switchSide), new AutoPathSelector.RightSwitch.One(gamedata.switchSide), new AutoPathSelector.MiddleScale.One(gamedata.scaleSide), new AutoPathSelector.LeftScale.One(gamedata.scaleSide), new AutoPathSelector.RightScale.One(gamedata.scaleSide)),
		ONETWO("OneTwo", new AutoPathSelector.MiddleSwitch.One(gamedata.switchSide), new AutoPathSelector.LeftSwitch.One(gamedata.switchSide), new AutoPathSelector.RightSwitch.One(gamedata.switchSide), new AutoPathSelector.MiddleScale.One(gamedata.scaleSide), new AutoPathSelector.LeftScale.One(gamedata.scaleSide), new AutoPathSelector.RightScale.One(gamedata.scaleSide));
		
	    private Command commandMiddleSwitch;	
	    private Command commandLeftSwitch;
	    private Command commandRightSwitch;
	    private Command commandMiddleScale;
	    private Command commandLeftScale;
	    private Command commandRightScale;
	    
	    private final String name;

	    AutoCubes(String name, Command commandMiddleSwitch, Command commandLeftSwitch, Command commandRightSwitch, Command commandMiddleScale, Command commandLeftScale, Command commandRightScale)
	    {
	        this.name = name;
	        this.commandMiddleSwitch = commandMiddleSwitch;
	        this.commandLeftSwitch = commandLeftSwitch;
	        this.commandRightSwitch = commandRightSwitch;
	        this.commandMiddleScale = commandMiddleScale;
	        this.commandLeftScale = commandLeftScale;
	        this.commandRightScale = commandRightScale;

	    }

	    public String getName() 
	    {
	        return name;
	    }
	    	    
	    public Command getMiddleSwitchCommand(){return commandMiddleSwitch;}
	    public Command getLeftSwitchCommand(){return commandLeftSwitch;}
	    public Command getRightSwitchCommand(){return commandRightSwitch;}
	    public Command getMiddleScaleCommand(){return commandMiddleScale;}
	    public Command getLeftScaleCommand(){return commandLeftScale;}
	    public Command getRightScaleCommand(){return commandRightScale;}

	    public char getGamedata()
	    {
	    	return gamedata.switchSide;
	    }
	}
}
