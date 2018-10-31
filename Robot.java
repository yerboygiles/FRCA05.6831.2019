/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.*;
import frc.robot.functions.classConstruct;
import frc.robot.functions.driveWithAHRS;
import frc.robot.functions.driveWithEncoders;
import frc.robot.functions.driveWithTime;
import frc.robot.functions.moveServos;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
  Talon motorLeft = new Talon(0);
  Talon motorRight= new Talon(1);
  Encoder encoderML;
  Encoder encoderMR;

  AHRS ahrs;

  classConstruct toConstructClass = new classConstruct();
  driverControl drivingControl = new driverControl();
  /*
  here are the functions being described, anything in quotes like
  "this is so and so variable"
  describes what should be put in that argument for the function
  */
  driveWithAHRS drivingAHRS = new driveWithAHRS();
  /*
  drivingAHRS.driveStraight(Talon left, Talon right, double power, double turn, AHRS ahrs)
  drivingAHRS.driveStraight(Talon left, Talon right, double power, double distance, AHRS ahrs)

  */
  driveWithEncoders drivingENCODERS = new driveWithEncoders();
  /*

  
  */
  driveWithTime drivingTIME = new driveWithTime();
  /*

  
  */
  moveServos movingSERVOS = new moveServos();
  /*
  moveWithAngle(Servo servo)
  moveWithValue(Servo "put the servo variable here",double "this is the value the servo is set to")
  
  */

  public int control;
  double robotTimer;
  double one = 1;
  double thousand = 1000;
  private Timer rbtTimer = new Timer();

  //why isnt this working fuck my life
  /*
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    motorRight.setInverted(true);
    
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }
  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    drivingENCODERS.driveStraight(motorLeft,motorRight, .5,500,encoderML,encoderMR);

  }
  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {

  }

}
