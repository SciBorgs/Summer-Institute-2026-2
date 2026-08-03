package org.sciborgs1155.robot.hopper;

public class NoHopper implements HopperIO {
    @Override
    public void setVoltage(double voltage) {
        // Do nothing
    }

    @Override
    public double getVoltage() {
        return 0;
    }

    @Override
    public double getVelocity() {
        return 0;
    }

    @Override
    public void close() throws Exception {
        // Do nothing
    }
    
}
