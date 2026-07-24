package org.sciborgs1155.robot.shooter;

import edu.wpi.first.epilogue.Logged;

public interface WheelIO extends AutoCloseable {
  void setVoltage(double voltage);

  @Logged
  double velocity();
}
