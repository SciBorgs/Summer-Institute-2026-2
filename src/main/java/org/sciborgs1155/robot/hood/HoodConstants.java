package org.sciborgs1155.robot.hood;

public final class HoodConstants {

  public static final double GEARING = 50.0;

  public static final double MOI = 0.5;

  public static final double CURRENT_LIMIT = 40.0;

  public static final double DEFAULT_ANGLE = 10.0;

  public static final double MAX_ANGLE = 20.0;

  public static final double MIN_ANGLE = 0.0;

  public static final double MAX_VOLTAGE = 12.0;

  public static final class FF {
    public static final double kS = 0;
    public static final double kV = 0;
    public static final double kA = 0;
  }

  public static final class PID {
    public static final double kP = 2.5;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
  }
}
