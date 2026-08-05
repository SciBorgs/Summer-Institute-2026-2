package org.sciborgs1155.robot.slapdown;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.units.VoltageUnit;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Velocity;

public class SlapdownConstants {
    public static final Current CURRENT_LIMIT = Amps.of(40);
public static double P = 0;
public static double I = 0;
public static double D = 0;
public static double S = 0.34;
public static double G = 0.85;
public static double V = 0.17;
public static double A = 0.62;

public static double EXTEND = -6;
public static double RETRACT = 6;
public static final Velocity<VoltageUnit> RAMP = Volts.of(1).per(Second);
public static final AngularVelocity MAX_VELOCITY = RadiansPerSecond.of(5);
public static final AngularVelocity LIMIT_VELOCITY = RadiansPerSecond.of(1);
public static final AngularVelocity MAX_ACCEL = RadiansPerSecond.of(2.5);
public static final Constraints normal = new Constraints(MAX_VELOCITY.in(RadiansPerSecond), MAX_ACCEL.in(RadiansPerSecond));
public static final Constraints limited = new Constraints(LIMIT_VELOCITY.in(RadiansPerSecond),MAX_ACCEL.in(RadiansPerSecond));

public static final DCMotor DC_MOTOR = DCMotor.getKrakenX60(1);
public static final double GEARING = 25 * 2.5;
public static final double MOI = 0.2135396026;
public static final Angle MIN_ANGLE = Radians.of(0);
public static final Angle MAX_ANGLE = Radians.of(1.864 - 0.07);
public static final Distance length = Inches.of(18.5); 
public static final Angle START = MAX_ANGLE;
public static final double STALL = 40;
}


