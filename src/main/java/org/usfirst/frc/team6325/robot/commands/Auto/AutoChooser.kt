package org.usfirst.frc.team6325.robot.commands.Auto


public class AutoChooser
{
    public enum class AutoPosition
    {
        LEFT("Left"),
        MIDDLE("Middle"),
        RIGHT("Right")

        var name: String = null

        fun AutoPosition(name: String) = this.name = name

        public fun getName(): String = return name
    }

    public enum class AutoPreference
    {
        SCALE("Scale"),
        SWITCH("Switch"),
        SIMPLE("Simple"),
        BASELINE("Baseline")

        var name: String = null
        
        fun AutoPreference(name: String) = this.name = name

        public fun getName(): String = return name
    }

    public enum class AutoCubes
    {
	    ONE("One"), 
		TWO("Two"), 
		THREE("Three"),
		ONEONE("OneOne"), 
        ONETWO("OneTwo")

        var name: String = null

        fun AutoCubes(name: String) = this.name = name

        public fun getName(): String = return name
    }
}