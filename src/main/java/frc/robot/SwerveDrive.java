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
        double r = Math.sqrt((L * L) + (W * W));
        azimuth *= -1;

        double a = forward - strafe * (L / r);
        double b = forward + strafe * (L / r);
        double c = azimuth - strafe * (W / r);
        double d = azimuth + strafe * (W / r);

        double backRightSpeed = Math.sqrt((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt((b * b) + (c * c));
        // System.out.println(backRightSpeed);

        double backRightAngle = (Math.atan2(a, d) / Math.PI);
        double backLeftAngle = Math.atan2(a, c) / Math.PI;
        double frontRightAngle = Math.atan2(b, d) / Math.PI;
        double frontLeftAngle = Math.atan2(b, c) / Math.PI;
        // prints angle in degrees. Calculations are done in radians
        /*
         * System.out.println("Back right: " + backRightAngle * 180);
         * System.out.println("Back left: " + backLeftAngle * 180);
         * System.out.println("Front left: " + frontLeftAngle * 180);
         * System.out.println("Front right: " + frontRightAngle * 180);
         */

        backRight.DriveWheel(backRightSpeed, backRightAngle);
        backLeft.DriveWheel(backLeftSpeed,backLeftAngle);
        frontRight.DriveWheel(frontRightSpeed,frontRightAngle);
        frontLeft.DriveWheel(frontLeftSpeed,frontLeftAngle);
    }
    //making it non static for now, still dont know what it'll do
    public void driveSimple(double speed, double angle){
        backRight.DriveWheel(speed, angle);
		backLeft.DriveWheel(speed, angle);
		frontLeft.DriveWheel(speed, angle);
		frontRight.DriveWheel(speed, angle);
    }
}