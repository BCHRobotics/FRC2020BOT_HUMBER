/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class PID {

    private double kP, kI, kD;

    private double error = 0, integral = 0, derivative = 0, previous_error = 0, rcw = 0;
    private String name;

    public PID(double kP, double kI, double kD, String name){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;

        SmartDashboard.putNumber(name + "-error:", -1);
        SmartDashboard.putNumber(name + "-rcw:", -1);
    }

    public double run(double setpoint, double position){

        error = setpoint - position;
        integral = integral + (error * 0.02);
        derivative = (error - previous_error) / 0.02;
        rcw = ( kP * error) + ( kI * integral) + ( kD * derivative);
        previous_error = error;

        SmartDashboard.putNumber(name + "-error:", error);
        SmartDashboard.putNumber(name + "-rcw:", rcw);

        return rcw;
    }

    public double getkP() {
        return kP;
    }

    public void setkP(double kP) {
        this.kP = kP;
    }

    public double getkI() {
        return kI;
    }

    public void setkI(double kI) {
        this.kI = kI;
    }

    public double getkD() {
        return kD;
    }

    public void setkD(double kD) {
        this.kD = kD;
    }

    public void resetPID(){
        error = 0;
        integral = 0;
        derivative = 0;
        previous_error = 0;
        rcw = 0;
    }

}
