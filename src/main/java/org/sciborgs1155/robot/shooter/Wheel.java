package org.sciborgs1155.robot.shooter;

import edu.wpi.first.epilogue.Logged;

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

  // log("shooter/velocity", velocity());

  @Override
  public void close() {}
}
