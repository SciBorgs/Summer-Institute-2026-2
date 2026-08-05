package org.sciborgs1155.robot.slapdown;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface SlapdownIO extends AutoCloseable{

    public void setVoltage(double voltage);

    double position();

    double current();

    public void resetposition();

public @Override
default void close() throws Exception {}
}

    


