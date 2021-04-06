package frc.robot;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;



public class WheelDrive {
    public double angleMultiplier = 88.5;         
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    //private CANEncoder m_encoder;
    private CANPIDController pidController;
    public WheelDrive(int angleMotorPort, int speedMotorPort, double P){
        
        this.angleMotor = new CANSparkMax(angleMotorPort, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotorPort, MotorType.kBrushless);
        angleMotor.getPIDController().setP(P);
    }
	public double angleMultiplier(double desiredAngle){
        return ((desiredAngle + Math.PI) * (angleMultiplier/2));
    }

    public void DriveWheel (double speed, double angle){
        System.out.println(angle);
        angleMotor.getPIDController().setReference(angleMultiplier(angle), ControlType.kPosition);    
        speedMotor.set(speed/2);  
        }
   
}

