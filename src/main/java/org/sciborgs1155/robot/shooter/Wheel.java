package org.sciborgs1155.robot.shooter;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.networktables.DoubleEntry;
import org.sciborgs1155.lib.LoggingUtils;

public class Wheel implements WheelIO, AutoCloseable {
  private final WheelIO hardware;

  public Wheel(WheelIO hardware) {
    this.hardware = hardware;
  }

  @Override
  public void setVoltage(double voltage) {
    hardware.setVoltage(voltage);
  }

  @Logged
  @Override
  public double velocity() {
    return hardware.velocity();
  }

  log("shooter/velocity", velocity());

  @Override
  public void close() {}
}
