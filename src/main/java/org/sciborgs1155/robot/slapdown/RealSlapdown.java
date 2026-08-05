package org.sciborgs1155.robot.slapdown;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Radians;

import org.sciborgs1155.robot.Ports;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.config.EncoderConfig;

public class RealSlapdown implements SlapdownIO{
    private final TalonFX motor;
    private final CANcoder encoder;
    public RealSlapdown(){
        encoder = new CANcoder(Ports.Slapdown.SLAPDOWN_ENCODER);
        motor = new TalonFX(Ports.Slapdown.SlAPDOWN_MOTOR);
        TalonFXConfiguration mconfig = new TalonFXConfiguration();
        CANcoderConfiguration econfig = new CANcoderConfiguration();
        mconfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        mconfig.CurrentLimits.SupplyCurrentLimit = SlapdownConstants.CURRENT_LIMIT.in(Amps);
        mconfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        mconfig.Feedback.SensorToMechanismRatio = 1;
        mconfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        econfig.MagnetSensor.MagnetOffset = -0.824462890625;
        econfig.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.9;

        encoder.getConfigurator().apply(econfig);
        motor.getConfigurator().apply(mconfig);
    } 
    public void setVoltage(double voltage){
        setVoltage(voltage);
    }
    public double position(){
return encoder.getAbsolutePosition().getValue().in(Radians);
    }
    public double current(){
       return motor.getStatorCurrent().getValueAsDouble();
    }
    public void resetposition(){
        encoder.setPosition(SlapdownConstants.MIN_ANGLE);
    }
    
  @Override
  public void close() throws Exception {
    motor.close();
  }

}
