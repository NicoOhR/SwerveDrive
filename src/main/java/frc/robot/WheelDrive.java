package frc.robot;  
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;



public class WheelDrive {
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private CANEncoder AngleEncoder;
    private CANEncoder SpeedEncoder;
    public WheelDrive(int angleMotorPort, int speedMotorPort, double P){
        this.angleMotor = new CANSparkMax(angleMotorPort, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotorPort, MotorType.kBrushless);
        AngleEncoder = angleMotor.getEncoder();
        //AngleEncoder.setPositionConversionFactor(360/88.5);
        SpeedEncoder = speedMotor.getEncoder();
        angleMotor.getPIDController().setP(P);
        angleMotor.getPIDController().setSmartMotionAllowedClosedLoopError(2.0, 0);
        angleMotor.setSmartCurrentLimit(60);
        speedMotor.setSmartCurrentLimit(40);
        AngleEncoder.setPosition(0);
        SpeedEncoder.setPosition(0);
    }
    public double printPosition(){
        return AngleEncoder.getPosition();
    }
    /*
    public double getAngle(){
        AngleEncoder = angleMotor.getEncoder();
        AngleEncoder.setPositionConversionFactor(360.0); 
        double AnglePosition = AngleEncoder.getPosition();
        return AnglePosition-180;
    }
    */
    /*
    public double ClosestAngle(double given, double ideal){
        double dir = -given%180.0 + ideal%180.0;
        if(Math.abs(dir) > 180.0){
            dir = -(Math.signum(dir)*360) + dir;
        }
        return dir;
    }
    */
	public double angleMultiplier(double desiredAngle){
        return ((desiredAngle/360)*88.1/*(1321621/15000)*/);
    }

    public void DriveWheel (double speed, double angle){

        angleMotor.getPIDController().setReference(angleMultiplier(angle), ControlType.kPosition);    
        speedMotor.set(speed);     
        /*
        if(speed > 0.3){
            angleMotor.getPIDController().setReference(angleMultiplier(angle), ControlType.kPosition);    
            speedMotor.set(speed);     
        }
        else{
            angleMotor.getPIDController().setReference(0,ControlType.kPosition);
            speedMotor.set(0);
        }
        */

    }
   
}

