package org.sciborgs1155.robot.hood;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class SimHood implements HoodIO {
  private final SingleJointedArmSim hood;

  public SimHood() {
    hood =
        new SingleJointedArmSim(
            DCMotor.getKrakenX60(2),
            HoodConstants.GEARING,
            HoodConstants.MOI,
            0.3,
            Math.toRadians(HoodConstants.MIN_ANGLE),
            Math.toRadians(HoodConstants.MAX_ANGLE),
            true,
            Math.toRadians(HoodConstants.DEFAULT_ANGLE),
            null);
  }

  public void setVoltage(double voltage) {
    hood.setInputVoltage(voltage);
    hood.update(0.02);
  }

  public double getAngle() {
    return Math.toRadians((hood.getAngleRads()));
  }
}
