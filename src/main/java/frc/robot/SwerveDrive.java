package frc.robot;

public class  SwerveDrive {
    public static final double L = 5.0; //will put numbers later
    public static final double W = 5.0;
    private static WheelDrive backRight;
    private static WheelDrive backLeft;
    private static WheelDrive frontRight;
    private static WheelDrive frontLeft;

    public SwerveDrive(WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;

    }
    //Static b/c drive cries about it
    public void drive(double forward, double strafe, double azimuth) {
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
        double r = Math.sqrt((L * L) + (W * W));
        azimuth *= -1;
        /*
        a,b,c, d are vectors, however, they only have the ability to point in one direction, so functionally, they are scalar values
        forward strafe and azimuth are also technically vector values, all this part is is vector addition 
        (to mention, strafe * (L/r) is correlative to Omega * r) 
        */
        double a = forward - strafe * (L / r);
        double b = forward + strafe * (L / r);
        double c = azimuth - strafe * (W / r);
        double d = azimuth + strafe * (W / r);
        //simple pythogras to get the speed between the "vectors"
        double backRightSpeed = Math.sqrt((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt((b * b) + (c * c));

        //get the unknown angle from the two vectors, IN RADIANS
        double backRightAngle = (Math.atan2(a, d) / Math.PI);
        double backLeftAngle = Math.atan2(a, c) / Math.PI;
        double frontRightAngle = Math.atan2(b, d) / Math.PI;
        double frontLeftAngle = Math.atan2(b, c) / Math.PI;
        

        backRight.DriveWheel(backRightSpeed, backRightAngle);
        backLeft.DriveWheel(backLeftSpeed,backLeftAngle);
        frontRight.DriveWheel(frontRightSpeed,frontRightAngle);
        frontLeft.DriveWheel(frontLeftSpeed,frontLeftAngle);
    }
    public void driveSimple(double speed, double angle){
        backRight.DriveWheel(speed, angle);
		backLeft.DriveWheel(speed, angle);
		frontLeft.DriveWheel(speed, angle);
		frontRight.DriveWheel(speed, angle);
    }
}