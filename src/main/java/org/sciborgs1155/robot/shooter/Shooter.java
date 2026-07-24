package org.sciborgs1155.robot.shooter;

import static edu.wpi.first.units.Units.*;
import static org.sciborgs1155.robot.shooter.ShooterConstants.*;
import static org.sciborgs1155.robot.shooter.ShooterConstants.ControlConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final WheelIO hardware;

  public Shooter(WheelIO hardware) {
    this.hardware = hardware;
    setDefaultCommand(runShooter());
  }

  public Command runShooter() {
    return run(() -> hardware.setVoltage(MAX_VOLTAGE));
  }
}
