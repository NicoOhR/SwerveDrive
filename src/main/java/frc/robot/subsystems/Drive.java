// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SwerveDrive;
import frc.robot.WheelDrive;


public class Drive extends SubsystemBase {
  Joystick joystick = new Joystick(0);
  //Create wheelDrives and assign to ports 
  static WheelDrive frontLeftWheel = new WheelDrive(1, 2);
  static WheelDrive frontRightWheel = new WheelDrive(3, 4);
  static WheelDrive backLeftWheel = new WheelDrive(5, 6);
  static WheelDrive backRightWheel = new WheelDrive(7, 8);

  public static SwerveDrive swerve = new SwerveDrive(frontRightWheel, frontLeftWheel, backLeftWheel, backRightWheel);

  public static void drive(double forward, double strafe, double azimuth) {
    swerve.drive(forward, strafe, azimuth);
  }
  //techno dogs code uses driveSimple for some reason,I think its test code but im compeled to keep it
  /*
  public void driveSimple(double speed, double angle){
    swerve.driveSimple(speed, angle);
  }
  */
  @Override

  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
