package frc.robot;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
//import edu.wpi.first.wpilibj.PIDController;



public class WheelDrive {
    public double angleMultiplier = 88.5;
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private CANEncoder m_encoder;
    private CANPIDController pidController;
    public WheelDrive(int angleMotor, int speedMotor){
        this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
        this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);

    }
	public double angleMultiplier(double desiredAngle){
        System.out.println("ticks: "+ desiredAngle * (angleMultiplier) + "angle: "+ desiredAngle);
        //desiredAngle raw is -1 to 1, by adding 1, it is 1 to 2, and /2 makes it 0 to 1
        //0 to 1 makes the math accurate for setReference   
        return (((desiredAngle + 1)/2) * (angleMultiplier));
    }

    public void DriveWheel (double speed, double angle){

        speedMotor.set(speed/2);  
        angleMotor.getPIDController().setReference(angleMultiplier(angle), ControlType.kPosition);
    }
   
}

