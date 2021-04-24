// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  //angle, speed
  /*
  WheelDrive frontRight = new WheelDrive(10 ,1, 1.0);
  WheelDrive frontLeft = new WheelDrive(2,3, 1.0);
  WheelDrive backLeft = new WheelDrive(4,5, 1.0);
  WheelDrive backRight = new WheelDrive(6,7, 1.0);
  */
  WheelDrive frontRight = new WheelDrive(10,1,1.0);//10,1
  WheelDrive frontLeft = new WheelDrive(6,7,1.0);//6,7
  WheelDrive backRight = new WheelDrive(2,3,1.0);//2,3
  WheelDrive backLeft = new WheelDrive(4,5,1.0);//4,5
  SwerveDrive swervedrive = new SwerveDrive(frontRight,frontLeft,backLeft,backRight);

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  @Override
  public void robotInit(){}
  @Override
  public void robotPeriodic() {}
  @Override
  public void autonomousInit(){
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);
  }
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }
  @Override
  public void teleopInit() {

  }
  @Override
  public void teleopPeriodic() {  
    swervedrive.drive(joystick.getRawAxis(1),joystick.getRawAxis(0),-joystick.getRawAxis(4));
    //swervedrive.drive(0,0,0); //returns 1 for desired angle
    //swervedrive.drive(1,0,0); //returns 0 for desired angle: should return 1/4
    //swervedrive.drive(1,1,0); // also returns 1 for desired angle
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
