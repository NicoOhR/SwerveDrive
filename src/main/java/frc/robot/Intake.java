package frc.robot;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;;

public class Intake {

  private static CANSparkMax intakeMotor;
  private static CANSparkMax arm;
  private static CANEncoder armEncoder;
  public double armUp = 0.0;
  public double armDown = 0.0;

  public Intake(int intakePort, int armPort){
    this.arm = new CANSparkMax(armPort, MotorType.kBrushless);
    Intake.intakeMotor = new CANSparkMax(intakePort,MotorType.kBrushless);
    armEncoder = arm.getEncoder();
  }
  public static void run(Boolean inButton,Boolean outButton){
    if(inButton){
      //run in  
      intakeMotor.set(0.75);
    }  
    else if(outButton){
      //runout
      intakeMotor.set(-0.75);
    }
    else{
      intakeMotor.set(0);
    }
  }  
  public static void armDrive(double Triggers){
    arm.set(Triggers);
    System.out.println(armEncoder.getPosition());
  }  
}
