package org.usfirst.frc.team4828.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

    public Drive d;
    public Joystick j1;
    public Joystick j2;

    @Override
    public void robotInit() {
        d = new Drive(9,8,7,6);
    }

    public void teleopPeriodic() {
        //d.tankDrive(j1,j2);
    	d.arcadeDrive(j1);
    }
}
