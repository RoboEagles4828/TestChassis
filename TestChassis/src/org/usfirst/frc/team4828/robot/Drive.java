package org.usfirst.frc.team4828.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Victor;

/**
 * Created by cheez on 11/26/2016.
 */
public class Drive {
    public Victor frontLeft;
    public Victor backLeft;
    public Victor frontRight;
    public Victor backRight;

    public Drive(int fl, int bl, int fr, int br) {
        frontLeft = new Victor(fl);
        backLeft = new Victor(bl);
        frontRight = new Victor(fr);
        backRight = new Victor(br);
    }

    public void set(double fl, double bl, double fr, double br) {
        frontLeft.set(fl);
        backLeft.set(bl);
        frontRight.set(fr);
        backRight.set(br);
    }

    public void stop() {
        frontLeft.set(0);
        backLeft.set(0);
        frontRight.set(0);
        backRight.set(0);
    }
    public void tankDrive(GenericHID leftStick, GenericHID rightStick) {
        if (leftStick == null || rightStick == null) {
            throw new NullPointerException("Null HID provided");
        }
        double mult = (((-leftStick.getThrottle()+1)/2)*.99);
        //double mult = 1;
        tankDrive(-leftStick.getY() * mult, -rightStick.getY() * mult, true);
    }
    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs) {
        if (squaredInputs) {
            if (leftValue >= 0.0) {
                leftValue = (leftValue * leftValue);
            } else {
                leftValue = -(leftValue * leftValue);
            }
            if (rightValue >= 0.0) {
                rightValue = (rightValue * rightValue);
            } else {
                rightValue = -(rightValue * rightValue);
            }
        }
        setLeftRightMotorOutputs(leftValue, rightValue);
    }

    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        if (backLeft == null || backRight == null) {
            throw new NullPointerException("Null motor provided");
        }
        if (frontLeft != null) {
            frontLeft.set(leftOutput);
        }
        backLeft.set(leftOutput);
        if (frontRight != null) {
            frontRight.set(-rightOutput);
        }
        backRight.set(-rightOutput);
    }
    public void arcadeDrive(GenericHID stick) {
        this.arcadeDrive(stick, true);
    }

    public void arcadeDrive(GenericHID stick, boolean squaredInputs) {
        arcadeDrive(stick.getY(), stick.getX(), squaredInputs);
    }

    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        double leftMotorSpeed;
        double rightMotorSpeed;

        if (squaredInputs) {
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
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
        setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
    }
}
