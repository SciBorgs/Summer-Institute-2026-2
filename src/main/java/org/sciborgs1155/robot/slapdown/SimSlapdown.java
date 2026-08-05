package org.sciborgs1155.robot.slapdown;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Radians;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class SimSlapdown implements SlapdownIO{
    
    
    private final SingleJointedArmSim sim = new SingleJointedArmSim(SlapdownConstants.DC_MOTOR, 
        SlapdownConstants.GEARING, 
        SlapdownConstants.MOI, 
        SlapdownConstants.length.in(Inches), 
        SlapdownConstants.MIN_ANGLE.in(Radians), 
        SlapdownConstants.MAX_ANGLE.in(Radians), 
        true, 
        SlapdownConstants.START.in(Radians) 
        );
    
        public void setVoltage(double voltage){
            sim.setInputVoltage(voltage);
        }
        
        public double position(){
return sim.getAngleRads();
        }

        public double current(){
            return sim.getCurrentDrawAmps();
        }

        public void resetposition(){
            sim.setState(SlapdownConstants.MIN_ANGLE.in(Radians),0);
        }
        @Override
        public void close() throws Exception{
            sim.setInputVoltage(0);
        }

    
}
