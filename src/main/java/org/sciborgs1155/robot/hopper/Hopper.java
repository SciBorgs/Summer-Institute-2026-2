package org.sciborgs1155.robot.hopper;

import static org.sciborgs1155.robot.Constants.TUNING;
import static org.sciborgs1155.robot.Ports.Hopper.BEAMBREAK;
import static org.sciborgs1155.robot.hopper.HopperConstants.A;
import static org.sciborgs1155.robot.hopper.HopperConstants.D;
import static org.sciborgs1155.robot.hopper.HopperConstants.I;
import static org.sciborgs1155.robot.hopper.HopperConstants.MAX_VOLTAGE;
import static org.sciborgs1155.robot.hopper.HopperConstants.P;
import static org.sciborgs1155.robot.hopper.HopperConstants.S;
import static org.sciborgs1155.robot.hopper.HopperConstants.V;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.epilogue.NotLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.function.DoubleSupplier;
import org.sciborgs1155.lib.Beambreak;
import org.sciborgs1155.lib.Tuning;
import org.sciborgs1155.robot.Robot;

public class Hopper extends SubsystemBase {
  private final HopperIO hardware;
  private final PIDController pid = new PIDController(P, I, D);
  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(S, V, A);

  public static Hopper create(){
    return Robot.isReal() ? new RealHopper() : new NoHopper();
  }

  public static Hopper(){
    return new Hopper(new NoHopper())
  }
  private Hopper(HopperIO hardware) {
    this.hardware = hardware;
  }

  public void update(double targetVelocity) {
    double voltage = MathUtil.clamp(pid.calculate(hardware.getVelocity(), targetVelocity)+ feedforward.calculate(targetVelocity), -MAX_VOLTAGE, MAX_VOLTAGE);
    hardware.setVoltage(voltage);
  }

  public Command runHopper(DoubleSupplier targetVelocity) {
    return run(() -> update(targetVelocity.getAsDouble())),
  }

  @Logged
  public double velocity() {
    return hardware.velocity();
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
