package org.usfirst.frc.team4828;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Victor;

public class DriveTrain {
    private Victor frontLeft, frontRight;
    private CANTalon backLeft, backRight;

    DriveTrain(int frontLeftPort, int backLeftPort, int frontRightPort, int backRightPort) {
        frontLeft = new Victor(frontLeftPort);
        frontRight = new Victor(frontRightPort);
        backLeft = new CANTalon(backLeftPort);
        backRight = new CANTalon(backRightPort);
    }

    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        double leftMotorSpeed;
        double rightMotorSpeed;

        if (squaredInputs) {
            // square the inputs (while preserving the sign) to increase fine control
            // while permitting full power
            if (moveValue >= 0.0) {
                moveValue = moveValue * moveValue;
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = rotateValue * rotateValue;
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }

        frontRight.set(rightMotorSpeed);
        backRight.set(rightMotorSpeed);
        frontRight.set(leftMotorSpeed);
        backRight.set(leftMotorSpeed);
    }

    /**
     * Turn all wheels slowly for testing purposes
     */
    public void testMotors() {
        frontLeft.set(.2);
        frontRight.set(.2);
        backLeft.set(.2);
        backRight.set(.2);
    }

}
