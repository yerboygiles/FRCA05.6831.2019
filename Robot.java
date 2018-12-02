package org.usfirst.frc.team6831.robot;

import java.util.Objects;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Robot extends TimedRobot {
	
	WPI_TalonSRX motorRight = new WPI_TalonSRX(1);
	WPI_TalonSRX motorRightFollower1 = new WPI_TalonSRX(2);
	WPI_TalonSRX motorRightFollower2 = new WPI_TalonSRX(3);
	WPI_TalonSRX canmotor4 = new WPI_TalonSRX(4);
	WPI_TalonSRX canmotor5 = new WPI_TalonSRX(5);
	WPI_TalonSRX motorLeftFollower2 = new WPI_TalonSRX(6);
	WPI_TalonSRX motorLeftFollower1 = new WPI_TalonSRX(7);
	WPI_TalonSRX motorLeft = new WPI_TalonSRX(8);
	
	public static Joystick stick1 = new Joystick(0);

	@Override
	public void robotInit() {
		motorRight.set(ControlMode.PercentOutput, 0);
		motorRightFollower1.follow(motorRight);
		motorRightFollower2.follow(motorRight);
		
		motorLeft.set(ControlMode.PercentOutput, 0);
		motorLeft.setInverted(true);
		motorLeftFollower1.follow(motorLeft);
		motorLeftFollower1.setInverted(true);
		motorLeftFollower2.follow(motorLeft);
		motorLeftFollower2.setInverted(true);
		
		
		CameraServer.getInstance().startAutomaticCapture();
		SmartDashboard.putString("DB/String 0", "init");
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		SmartDashboard.putString("help", "Functional");
	}


	@Override
	public void autonomousInit() {
	}
	
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		motorRight.set(stick1.getRawAxis(1)+stick1.getRawAxis(2));
		motorLeft.set(stick1.getRawAxis(1)-stick1.getRawAxis(2));
		
		
		SmartDashboard.putString("DB/String 3", "Functional");
		Scheduler.getInstance().run();
		
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
	
	public void updateGyro() {
		float yaw = ahrs.getYaw();
		if (lastangle - yaw > 250) {
			anglemultiplier += 1;
		}
		if (lastangle - yaw < -250) { 
			anglemultiplier -= 1;
		}
		angle = yaw + (360 * anglemultiplier);
		lastangle = yaw;
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



