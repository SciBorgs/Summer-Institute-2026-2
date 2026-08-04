package org.sciborgs1155.robot.hopper;

import static org.sciborgs1155.robot.Constants.TUNING;
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

import com.ctre.phoenix6.hardware.TalonFX;

public class Hopper extends SubsystemBase implements AutoCloseable{
  private final SimpleMotor hardware;
  private final Beambreak beambreak;
  private final Trigger blocked;

  public static Hopper create() {
    return Robot.isReal() ? new Hopper(realMotor(), Beambreak.real(BEAMBREAK)) : none();
  }

  public static Hopper none() {
    return new Hopper(Hopper.none(), Beambreak.none());
  }

  private static SimpleMotor realMotor() {
    TalonFX motor(HopperConstants.MOTOR_ID, TUNING.get(Tuning.HOPPER));
    TalonFX.configureCurrentLimit(HopperConstants.CURRENT_LIMIT);
    return SimpleMotor.talon(motor);
  }

  private Hopper(HopperIO hardware, Beambreak beambreak) {
    this.hardware = hardware;
    this.beambreak = beambreak;
    this.blocked = new Trigger(beambreak::get);
  }


  public Command intake(DoubleSupplier speed) {
    return run(() -> hardware.set(speed.getAsDouble()));
  }

  public Command outtake(DoubleSupplier speed) {
    return run(() -> hardware.set(-speed.getAsDouble()));
  }

  public Command stop() {
    return run(() -> hardware.set(0));
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
