package org.sciborgs1155.robot.slapdown;


import static edu.wpi.first.units.Units.Radians;

import org.sciborgs1155.robot.Robot;

import com.ctre.phoenix6.swerve.utility.WheelForceCalculator.Feedforwards;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Slapdown extends SubsystemBase implements AutoCloseable{
    private final SlapdownIO hardware;

public Slapdown(SlapdownIO hardware){
    this.hardware = hardware;
pid.reset(hardware.position());
pid.setGoal(SlapdownConstants.START.in(Radians));

}

    private final ProfiledPIDController pid = new ProfiledPIDController(
        SlapdownConstants.P,
        SlapdownConstants.I,
        SlapdownConstants.D,
        SlapdownConstants.normal);
        private final ProfiledPIDController limited_pid = new ProfiledPIDController(
            SlapdownConstants.P, 
            SlapdownConstants.I, 
            SlapdownConstants.D, 
            SlapdownConstants.limited);

private final ArmFeedforward ff = new ArmFeedforward(
    SlapdownConstants.S,
    SlapdownConstants.G,
    SlapdownConstants.V,
    SlapdownConstants.A
);

public Slapdown create(){
    return new Slapdown(Robot.isReal() ? new RealSlapdown() : new SimSlapdown());
}
public static Slapdown none(){
    return new Slapdown(new NOSlapdown());     
} 

public Command extend(){
return run(() -> hardware.setVoltage(SlapdownConstants.EXTEND)).until(() -> hardware.current() > SlapdownConstants.STALL);
}
public Command retract(){
    return run(() -> hardware.setVoltage(SlapdownConstants.RETRACT)).until(() -> hardware.current() > SlapdownConstants.STALL);
}

public Command ExtendAngle(){
    return goTo(SlapdownConstants.MIN_ANGLE.in(Radians));
}

public Command RetractAngle(){
    return goTo(SlapdownConstants.MAX_ANGLE.in(Radians));
}
public Command goTo(double angle){
    return run(()-> update(angle));
}

public void update(double angle){
    update(angle , pid);
}


public void update(double angle, ProfiledPIDController pid){
double rad = MathUtil.clamp(angle, SlapdownConstants.MIN_ANGLE.in(Radians), SlapdownConstants.MAX_ANGLE.in(Radians));
double pid_voltage = pid.calculate( hardware.position() , rad);
double ff_voltage = ff.calculate(pid.getSetpoint().position, pid.getSetpoint().velocity);
hardware.setVoltage(ff_voltage + pid_voltage);
}
@Override
public void close() throws Exception{
    hardware.close();
}
}
