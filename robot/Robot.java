package org.usfirst.frc.team6831.robot;

import java.util.ArrayList;

import org.usfirst.frc.team6831.robot.commands.*;
import org.usfirst.frc.team6831.robot.subsystems.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
	
	
	Timer time = new Timer();
	//]AHRS ahrs = new AHRS(Port.kMXP);
	
	public static Joystick stick1 = new Joystick(0);
	driveTrain dt = new driveTrain(time, stick1, 1, 2, 3, 8, 7, 6, 0, 1);
	sweeper sweeper = new sweeper(stick1, 4);
	ArrayList<command> commands = new ArrayList<command>();
	State functionState = State.IDLE;

	@Override
	public void robotInit() {
		dt.init();
		sweeper.init();
		
		CameraServer.getInstance().startAutomaticCapture(0);
		CameraServer.getInstance().startAutomaticCapture(1);
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		SmartDashboard.putString("DB/String 0", "Disabled");
		SmartDashboard.putString("DB/String 1", " ");
		SmartDashboard.putString("DB/String 2", " ");
		SmartDashboard.putString("DB/String 3", " ");
		SmartDashboard.putString("DB/String 4", " ");
		SmartDashboard.putString("DB/String 5", " ");
		SmartDashboard.putString("DB/String 6", " ");
	}


	@Override
	public void autonomousInit() {
		SmartDashboard.putString("DB/String 0", "Initializing Autonomous");
		commands.add(new EncoderDrive());
	}
	
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putString("DB/String 0", "Autonomous");
		
		for(int i = 0; i < commands.size();) {
			switch(functionState) {
			case INITIALIZING:
				if(commands.get(i).init(this)) {
					functionState = State.UPDATING;
				}
				break;
			case UPDATING:
				if(commands.get(i).update(this)) {
					functionState = State.RESOLVING;
				}
				break;
			case RESOLVING:
				if(commands.get(i).resolve(this)){
					functionState = State.IDLE;
				};
				break;
			case IDLE:
				i++;
				functionState = State.INITIALIZING;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void teleopInit() {
		SmartDashboard.putString("DB/String 0", "Initializing Teleop");
		time.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putString("DB/String 0", "Teleoperated");		
		sweeper.update();
		dt.update();
		SmartDashboard.putString("DB/String 1", Double.toString(time.get()));
	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
	
/*	public void driveStraight(double DSspeed, double DStime) {
		SmartDashboard.putString("DB/String 3", "Drive Straight");
		switch(functionControl) {
		case 0:
			FunctionAngle = angle;
			initTime = timer.get();
			angleCorrection = 70;
			functionControl += 1;
			break;
		case 1:
			
			SmartDashboard.putNumber("DB/String 5", DStime-(timer.get()-initTime));
			SmartDashboard.putNumber("DB/String 6", functionAngleCorrection);
			SmartDashboard.putNumber("DB/String 7", DSspeed);
			functionAngleCorrection = ((angle - FunctionAngle)/angleCorrection);
			if (functionAngleCorrection > 0.4) functionAngleCorrection = 0.4;
			setMotors(DSspeed + functionAngleCorrection, DSspeed - functionAngleCorrection);
			if(timer.get() - initTime > DStime) {
				functionControl += 1;
				}
			break;
		case 2:
			setMotors(0, 0);
			functionControl = 0;
			control += 1;
		}
	}
	
	
	public void setMotors (double power1, double power2){
		leftDrive.set(power1);
		rightDrive.set(power2);
	}

	public void turning(double Tangle) {
		SmartDashboard.putString("DB/String 3", "Turning");
		switch(functionControl) {
		case 0:
			initTime = timer.get();
			FunctionAngle = Tangle+angle;
			angleCorrection = 90;
			functionControl += 1;
			break;
		case 1:
			SmartDashboard.putString("DB/String 5", Double.toString(FunctionAngle-angle));
			SmartDashboard.putString("DB/String 6", Double.toString(FunctionAngle));
			SmartDashboard.putString("DB/String 7", Double.toString(functionAngleCorrection));
			SmartDashboard.putString("DB/String 8", Double.toString(2-(timer.get()-initTime)));
			
			functionAngleCorrection = ((angle - FunctionAngle)/angleCorrection);
			if (functionAngleCorrection > 0.4) functionAngleCorrection = 0.4;
			if (functionAngleCorrection < -0.4) functionAngleCorrection = -0.4;
			
			setMotors(functionAngleCorrection, -functionAngleCorrection);
			/*if(Math.abs(angle-FunctionAngle)<5) {
				functionControl += 1;
				}*\/
			if(timer.get()-initTime > 1.7) {
				functionControl += 1;
			}
			break;
		case 2:
			setMotors(0, 0);
			functionControl = 0;
			control += 1;
		}
	}

	public void driveWithEncoders(double distanceInInches, double speed, double FunctionAngle) {
		SmartDashboard.putString("DB/String 3", "Drive With Encoders");
		switch(functionControl) {
		case 0:
			eLeft.reset();
			eRight.reset();
			initDistance = eLeft.getDistance()/54.4;
			initTime = timer.get();
			//FunctionAngle = angle;
			angleCorrection = 90;
			positionCorrection = 50;
			accelerationCorrection = 2;
		positionDerivativeCorrection = 20;  	
			SmartDashboard.putString("DB/String 5", Double.toString((eLeft.getDistance()/54.4 - initDistance)));
			SmartDashboard.putString("DB/String 6", Double.toString((distanceInInches)));
			functionControl = 1;
			functionPositionCorrection = ((distanceInInches)-(eLeft.getDistance()/54.4))/positionCorrection;
			if (functionPositionCorrection > speed) functionPositionCorrection = speed;
			if (functionPositionCorrection < -speed) functionPositionCorrection = -speed;
			break;
		case 1:
			SmartDashboard.putString("DB/String 5", Double.toString((eLeft.getDistance()/54.4 - initDistance)));
			SmartDashboard.putString("DB/String 6", Double.toString((distanceInInches)));
			SmartDashboard.putString("DB/String 7", Double.toString(positionDerivative));
			SmartDashboard.putString("DB/String 8", Double.toString(functionPositionCorrection));
			SmartDashboard.putString("DB/String 9", Double.toString((functionPositionCorrection+positionDerivative+.2+functionAngleCorrection)*functionAccelerationCorrection));
			
			
			functionAccelerationCorrection = (timer.get()-initTime)/accelerationCorrection;
			if (functionAccelerationCorrection > 1) functionAccelerationCorrection = 1;
			if (functionAccelerationCorrection < -1) functionAccelerationCorrection = -1;
			
			
			positionDerivative = functionPositionCorrection;
			functionPositionCorrection = ((distanceInInches)-((eLeft.getDistance()/54.4)-initDistance))/positionCorrection;
			if (functionPositionCorrection > speed) functionPositionCorrection = speed;
			if (functionPositionCorrection < -speed) functionPositionCorrection = -speed;
			positionDerivative = (functionPositionCorrection - positionDerivative)*positionDerivativeCorrection;
			
			
			functionAngleCorrection = ((angle - FunctionAngle)/angleCorrection);
			if (functionAngleCorrection > speed) functionAngleCorrection = speed;
			if (functionAngleCorrection < -speed) functionAngleCorrection = -speed;
			
			setMotors((functionPositionCorrection+positionDerivative+(distanceInInches/Math.abs(distanceInInches))*.2+functionAngleCorrection)*functionAccelerationCorrection, (functionPositionCorrection+positionDerivative+(distanceInInches/Math.abs(distanceInInches))*.2-functionAngleCorrection)*functionAccelerationCorrection);
			
			
			if(Math.abs((eLeft.getDistance()/54.4 - initDistance)-(distanceInInches)) < 1) {
				functionControl ++;
			}
			break;
		case 2:
			setMotors(0,0);
			control ++;
			functionControl = 0;
			eLeft.reset();
			eRight.reset();
			break;
		}
	}*/

}



