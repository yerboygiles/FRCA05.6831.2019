package org.usfirst.frc.team6831.robot.commands;

import org.usfirst.frc.team6831.robot.Robot;

public abstract class command{
	
	public abstract boolean init(Robot robot);
	public abstract boolean update(Robot robot);
	public abstract boolean resolve(Robot robot);

}
