package org.sciborgs1155.robot.hood;

import static org.sciborgs1155.lib.UnitTestingUtil.run;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

public final class Hood extends SubsystemBase {

  private final HoodIO hardware;

  private final PIDController pid =
      new PIDController(HoodConstants.PID.kP, HoodConstants.PID.kI, HoodConstants.PID.kD);

  private final SimpleMotorFeedforward ff =
      new SimpleMotorFeedforward(HoodConstants.FF.kS, HoodConstants.FF.kV, HoodConstants.FF.kA);

  private Hood(HoodIO hardware) {
    this.hardware = hardware;
  }

  public static Hood create(HoodIO hardware) {
    return RobotBase.isReal() ? new Hood(new RealHood()) : new Hood(new SimHood());
  }

  public static Hood none() {
    return new Hood(new NoHood());
  }

  public double getAngle() {
    return hardware.getAngle();
  }

  public void update(double angleSetpoint) {

    double angle = MathUtil.clamp(angleSetpoint, -HoodConstants.MIN_ANGLE, HoodConstants.MAX_ANGLE);

    double ffVolts = ff.calculate(angle);
    double pidVolts = pid.calculate(getAngle(), angle);

    hardware.setVoltage(
        MathUtil.clamp(pidVolts + ffVolts, -HoodConstants.MAX_VOLTAGE, HoodConstants.MAX_VOLTAGE));
  }

  public Command runHood(DoubleSupplier angle) {
    return run(() -> update(angle.getAsDouble()));
  }

  public Command runHood(double angle) {
    return runHood(() -> angle);
  }
}
