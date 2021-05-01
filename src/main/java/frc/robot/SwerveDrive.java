package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveDrive {
    public static final double L = 30.0; //will put numbers later
    public static final double W = 30.0;
    private WheelDrive backRight;
    private WheelDrive backLeft;
    private WheelDrive frontRight;
    private WheelDrive frontLeft;
    public SwerveDrive(WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft) {
        this.backRight =  backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;    
    } 
        public void drive(double forward,double strafe, double azimuth){
        if(azimuth < 0.4 && azimuth > -0.4){
            azimuth = 0;    
        }
        double r = Math.sqrt ((L * L) + (W * W));
        //azimuth *= -1;       
        //forward *= -1;
        strafe *= -1;
        
        SmartDashboard.putNumber("strafe",strafe);       
        SmartDashboard.putNumber("forward",forward);       
        SmartDashboard.putNumber("azi", azimuth);      
        double a = strafe - azimuth * (L / r);
        double b = strafe + azimuth * (L / r);
        double c = forward - azimuth * (W / r);
        double d = forward + azimuth * (W / r);
     
        /*
         *                FRONT
         * 
         *            c          d
         *            | 		 |
         *       b ------------------ b
         *            |          |
         *            |          |
         * LEFT       |          |      RIGHT
         *            |          |
         *            |          |
         *       a ------------------ a
         *            |          |
         *            c          d
         * 
         *                BACK
         */
        SmartDashboard.putNumber("a", a);       
        SmartDashboard.putNumber("b",b);       
        SmartDashboard.putNumber("c", c);
        SmartDashboard.putNumber("d", d);

        double backRightSpeed = Math.sqrt ((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));
        SmartDashboard.putNumber("Back Right speed", backRightSpeed);
        SmartDashboard.putNumber("Front Right speed", backLeftSpeed);
        SmartDashboard.putNumber("Back Left speed",frontRightSpeed);
        SmartDashboard.putNumber("Front Left speed", frontLeftSpeed);

        double backRightAngle = (Math.atan2(a,d)*(180/Math.PI));
        double backLeftAngle = (Math.atan2(a,c)*(180/Math.PI));
        double frontLeftAngle = Math.atan2(b,d)*(180/Math.PI);
        double frontRightAngle = Math.atan2(b,c)*(180/Math.PI);
        
        SmartDashboard.putNumber("back Right Angle", backRightAngle);
        SmartDashboard.putNumber("front Right Angle", frontRightAngle);
        SmartDashboard.putNumber("back Left Angle", backLeftAngle);
        SmartDashboard.putNumber("front Left Angle",frontLeftAngle);

        backRight.DriveWheel(backRightSpeed,backRightAngle); 
        backLeft.DriveWheel(backLeftSpeed, backLeftAngle);
        frontRight.DriveWheel(frontRightSpeed,frontRightAngle);//flipping angles for testing
        frontLeft.DriveWheel(frontLeftSpeed,frontLeftAngle);
    }
}