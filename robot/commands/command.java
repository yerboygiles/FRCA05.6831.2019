package org.usfirst.frc.team6831.robot.commands;

import org.usfirst.frc.team6831.robot.Robot;
import org.usfirst.frc.team6831.robot.subsystems.State;

public abstract class command{

	public static State functionState = State.IDLE;
	public abstract void init(Robot robot);
	public abstract void update(Robot robot);
	public abstract void resolve(Robot robot);

}
