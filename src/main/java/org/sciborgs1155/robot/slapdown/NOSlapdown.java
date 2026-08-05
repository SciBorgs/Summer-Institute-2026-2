package org.sciborgs1155.robot.slapdown;

public class NOSlapdown implements SlapdownIO{
      public void setVoltage(double voltage){}
      

    public double position(){
        return 0;
    }

    public double current(){
        return 0;
    }

    public void resetposition(){}

    @Override
    public void close() throws Exception{}
    }

