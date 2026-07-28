package org.sciborgs1155.robot.shooter;

import static edu.wpi.first.units.Units.*;
import static org.sciborgs1155.robot.Constants.PERIOD;
import static org.sciborgs1155.robot.shooter.ShooterConstants.*;
import static org.sciborgs1155.robot.shooter.ShooterConstants.ControlConstants.*;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sciborgs1155.robot.Robot;

@Logged
public class Shooter extends SubsystemBase {
  private final WheelIO hardware;
  private final PIDController pid = new PIDController(P, I, D);
  private final SimpleMotorFeedforward ff = new SimpleMotorFeedforward(S, V, A, PERIOD.in(Seconds));

  public Shooter(WheelIO hardware) {
    this.hardware = hardware;
    setDefaultCommand(run(() -> hardware.setVoltage(0)));
  }

  public static Shooter create() {
    return new Shooter(Robot.isReal() ? new RealWheel() : new SimWheel());
  }

  public Command runShooter(double velocity) {
    return run(() -> hardware.setVoltage(velocity));
    update(velocity);
  }

  public double velocity() {
    return hardware.velocity();
  }

  public void update(double velocitySetpoint) {

    // makes sure the velocity it tries to go to does not exceed the max/min
    double velocity = velocitySetpoint;
    // calculated the needed voltages using PID and FF
    double ffVolts = ff.calculate(velocity);
    double pidVolts = pid.calculate(velocity(), velocity);
    hardware.setVoltage(MathUtil.clamp(pidVolts + ffVolts, -MAX_VOLTAGE, MAX_VOLTAGE));
  }
}
