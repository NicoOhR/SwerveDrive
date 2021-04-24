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
    /*
    public static double ClosestAngle(double current,double ideal){
        double direction = current % 360
    }
    */
        public void drive(double forward,double strafe, double azimuth){
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
        //System.out.println(Math.atan2(a, d));
        double backLeftAngle = (Math.atan2(a,c)*(180/Math.PI));
        double frontLeftAngle = Math.atan2(b,d)*(180/Math.PI);
        double frontRightAngle = Math.atan2(b,c)*(180/Math.PI);
        //bad idea, still here for keepsake
        //double backRightAngle = (Math.atan2(a,d)*(180/Math.PI)) - Math.atan2(a,c)*(180/Math.PI) ;
        //double backLeftAngle = Math.atan2(a,c)*(180/Math.PI) - Math.atan2(a,d)*(180/Math.PI);
        //double frontRightAngle = Math.atan2(b,d)*(180/Math.PI) - Math.atan2(b,c)*(180/Math.PI);
        //double frontLeftAngle = Math.atan2(b,c)*(180/Math.PI) - Math.atan2(b,d)*(180/Math.PI) ;
        SmartDashboard.putNumber("back Right Angle", backRightAngle);
        SmartDashboard.putNumber("front Right Angle", frontRightAngle);
        SmartDashboard.putNumber("back Left Angle", backLeftAngle);
        SmartDashboard.putNumber("front Left Angle",frontLeftAngle);
        
        /*
        if(forward < 1 && forward > -1){
            backRight.DriveWheel(0,backRightAngle);    
            backLeft.DriveWheel(0,backLeftAngle);
            frontRight.DriveWheel(0,frontRightAngle);
            frontLeft.DriveWheel(0,frontLeftAngle);
        }
        if(strafe < 1  && strafe > -1){
        backRight.DriveWheel(backRightSpeed,0); 
        backLeft.DriveWheel(backLeftSpeed, 0);
        frontRight.DriveWheel(frontRightSpeed,0);
        frontLeft.DriveWheel(frontLeftSpeed,0);
        }
        if(azimuth < 1 && azimuth > -1){
            azimuth = 0;
        }
*/
        backRight.DriveWheel(backRightSpeed,backRightAngle); 
        backLeft.DriveWheel(backLeftSpeed, backLeftAngle);
        frontRight.DriveWheel(frontRightSpeed,frontRightAngle);//flipping angles for testing
        frontLeft.DriveWheel(frontLeftSpeed,frontLeftAngle);
    }
}