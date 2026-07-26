package org.sciborgs1155.robot.hood;

import static org.sciborgs1155.robot.Ports.Hood;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import org.sciborgs1155.robot.Ports.Hood;

public class RealHood implements HoodIO {
  private final TalonFX leader;
  private final TalonFX follower;

  public RealHood() {
    leader = new TalonFX(Hood.LEADER);
    follower = new TalonFX(Hood.FOLLOWER);

    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Feedback.SensorToMechanismRatio = 5;
    leader.getConfigurator().apply(config);
    follower.getConfigurator().apply(config);
    follower.setControl(new Follower(leader.getDeviceID(), MotorAlignmentValue.Aligned));
    leader.setPosition(0.0);
  }

  @Override
  public void setVoltage(double voltage) {
    leader.setVoltage(voltage);
  }

  @Override
  public double getAngle() {
    return leader.getPosition().getValueAsDouble();
  }
}
