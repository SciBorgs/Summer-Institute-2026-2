package org.sciborgs1155.robot.Intake;


import org.sciborgs1155.robot.Ports;
import org.sciborgs1155.robot.Robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase implements AutoCloseable{
    private final TalonFX motor;
   

  public Intake(){
motor = new TalonFX(Ports.Intake.INTAKE_MOTOR);
        TalonFXConfiguration config = new TalonFXConfiguration();
config.CurrentLimits.SupplyCurrentLimitEnable = true;
config.CurrentLimits.SupplyCurrentLimit = 40;
config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
config.Feedback.SensorToMechanismRatio = IntakeConstants.GEARING;
        motor.getConfigurator().apply(config);
    }

    public static Intake none(){
        return new Intake();
    }
   
public static Intake create(){
return Robot.isReal() ? new Intake() : none();
}
    private Command run(double power){
     return Commands.run(() -> motor.set(power),this);
    }
    public Command intake(){
return run(IntakeConstants.INTAKE_POWER);
    }
    public Command Outtake(){
        return run(-IntakeConstants.INTAKE_POWER);
    }
    public Command stop(){
        return run(0);
    }
    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
        
    }
}

