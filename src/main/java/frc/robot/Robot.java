// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Intake;
import com.revrobotics.CANPIDController;
import frc.robot.WheelDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Joystick joystick = new Joystick(0);
  Joystick joystickArm = new Joystick(1);
  Boolean armButton = joystickArm.getRawButton(1);
  //angle, speed
  /*
  WheelDrive frontRight = new WheelDrive(10 ,1, 1.0);
  WheelDrive frontLeft = new WheelDrive(2,3, 1.0);
  WheelDrive backLeft = new WheelDrive(4,5, 1.0);
  WheelDrive backRight = new WheelDrive(6,7, 1.0);

  */
  /*
  public double JoystickDeadband(double JoystickValue){
    if(Math.abs(JoystickValue) < 0.1){ 
      return 0;
    }
    else{
      return JoystickValue;
    }
  }
  */
  double kP = 14.0;
  double kI = 1.0;
  WheelDrive frontRight = new WheelDrive(4,5,kP,kI);//10,1
  WheelDrive frontLeft = new WheelDrive(2,3,kP,kI);//6,7
  WheelDrive backRight = new WheelDrive(6,7,kP,kI);//2,3
  WheelDrive backLeft = new WheelDrive(10,1,12,0.00001);//4,5
  SwerveDrive swervedrive = new SwerveDrive(frontRight,frontLeft,backLeft,backRight);
  Intake Intake = new Intake(15, 14);
  public int timer;
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  @Override
  public void robotInit(){}
  @Override
  public void robotPeriodic() {
    timer += 20;
    if(timer < 200){
      swervedrive.drive(0.5,0,0);
    }
  }
  @Override
  public void autonomousInit(){
    m_autoSelected = m_chooser.getSelected();
    

  }
  @Override
  public void autonomousPeriodic() {
    
  }
  @Override
  public void teleopInit() {

  }
  @Override
  public void teleopPeriodic() {  
    //swervedrive.drive(-JoystickDeadband(joystick.getRawAxis(1)),-JoystickDeadband(joystick.getRawAxis(0)),-JoystickDeadband(joystick.getRawAxis(4)));
    swervedrive.drive(-joystick.getRawAxis(1),-joystick.getRawAxis(0),joystick.getRawAxis(4));
    Intake.run(joystickArm.getRawButton(1),joystickArm.getRawButton(2));
    Intake.armDrive(-joystickArm.getRawAxis(3)+joystickArm.getRawAxis(2));
    System.out.println("FrontRight" + frontRight.printPosition());   
    System.out.println("FrontLeft" + frontLeft.printPosition());
    System.out.println("BackRight"+backRight.printPosition());                                                                                                                          
    System.out.println("BackLeft"+backLeft.printPosition());
  }
  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
