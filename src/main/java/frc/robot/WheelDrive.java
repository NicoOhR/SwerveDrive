package frc.robot;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;



public class WheelDrive {
    public double angleMultiplier = 88.5;         
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private CANEncoder AngleEncoder;
    private CANEncoder SpeedEncoder;
    private CANPIDController pidController;
    public WheelDrive(int angleMotorPort, int speedMotorPort, double P){
        this.angleMotor = new CANSparkMax(angleMotorPort, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotorPort, MotorType.kBrushless);
        AngleEncoder = angleMotor.getEncoder();
        //AngleEncoder.setPositionConversionFactor(360/88.5);
        SpeedEncoder = speedMotor.getEncoder();
        angleMotor.getPIDController().setP(P);
        angleMotor.setSmartCurrentLimit(20);
        //speedMotor.setSmartCurrentLimit(20);
        AngleEncoder.getPosition();
        AngleEncoder.setPosition(0);
        SpeedEncoder.setPosition(0);
    }
    public double getAngle(){
        AngleEncoder.setPositionConversionFactor(360.0); 
        double AnglePosition = AngleEncoder.getPosition();
        return ((AnglePosition%360)-180);
    }
	public double angleMultiplier(double desiredAngle){
        /*if (desiredAngle < 0) {
            desiredAngle = (360 - (desiredAngle * -1));
        }
        else if (desiredAngle >360) {
            desiredAngle = desiredAngle -360;       
        } 
        */  
        return ((desiredAngle/360)*(1321621/15000));
    }

    public void DriveWheel (double speed, double angle){
        if(speed > 0.3){
        angleMotor.getPIDController().setReference(angleMultiplier(angle), ControlType.kPosition);    
        speedMotor.set(speed/2);     
        }
        else{
            angleMotor.getPIDController().setReference(0,ControlType.kPosition);
            speedMotor.set(0);
        }
    }
   
}

