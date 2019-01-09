package org.usfirst.frc.team6831.robot.commands;

import org.usfirst.frc.team6831.robot.Robot;
import org.usfirst.frc.team6831.robot.subsystems.State;

public abstract class command{
	
	public State functionState;
	public abstract boolean init(Robot robot);
	public abstract boolean update(Robot robot);
	public abstract boolean resolve(Robot robot);

}
