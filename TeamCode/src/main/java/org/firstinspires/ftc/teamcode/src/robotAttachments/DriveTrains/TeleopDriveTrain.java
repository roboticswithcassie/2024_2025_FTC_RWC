package org.firstinspires.ftc.teamcode.src.robotAttachments.DriveTrains;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Adds extra functionality to BasicDrivetrain to be used in Teleop
 */
public class TeleopDriveTrain extends BasicDrivetrain {
    /**
     * A internal variable to scale the input sensitivity
     */
    private double DrivePowerMult;

    private boolean frontDrive = true; //Starts facing forward

    /**
     * Constructs drive train from hardware map and motor names
     *
     * @param hardwareMap The hardware map object from the OpMode class
     * @param frontRight  The name of the front right motor
     * @param frontLeft   The name of the front left motor
     * @param backRight   The name of the back right motor
     * @param backLeft    The name of the back left motor
     */
    public TeleopDriveTrain(HardwareMap hardwareMap, String frontRight, String frontLeft, String backRight, String backLeft) {
        super(hardwareMap, frontRight, frontLeft, backRight, backLeft);
        this.DrivePowerMult = 1;
    }

    //true is forward, false is backwards
    public boolean getFacingDirection() {
        return this.frontDrive;
    }

    /**
     * Sets the power to the motors based on the right and left stick of the gamepad object
     *
     * @param gamepad The gamepad to set powers off of
     */
    public void setPowerFromGamepad(Gamepad gamepad) {

        // The Y axis of a joystick ranges from -1 in its topmost position
        // to +1 in its bottom most position. We negate this value so that
        // the topmost position corresponds to maximum forward power.
        if (frontDrive) {
            this.back_left.setPower(DrivePowerMult * ((gamepad.left_stick_y + gamepad.left_stick_x) - gamepad.right_stick_x));
            this.front_left.setPower(DrivePowerMult * ((gamepad.left_stick_y - gamepad.left_stick_x) - gamepad.right_stick_x));
            this.back_right.setPower(DrivePowerMult * ((gamepad.left_stick_y - gamepad.left_stick_x) + gamepad.right_stick_x));
            this.front_right.setPower(DrivePowerMult * ((gamepad.left_stick_y + gamepad.left_stick_x) + gamepad.right_stick_x));
        } else {
            this.back_left.setPower(DrivePowerMult * ((-gamepad.left_stick_y - gamepad.left_stick_x) - gamepad.right_stick_x));
            this.front_left.setPower(DrivePowerMult * ((-gamepad.left_stick_y + gamepad.left_stick_x) - gamepad.right_stick_x));
            this.back_right.setPower(DrivePowerMult * ((-gamepad.left_stick_y + gamepad.left_stick_x) + gamepad.right_stick_x));
            this.front_right.setPower(DrivePowerMult * ((-gamepad.left_stick_y - gamepad.left_stick_x) + gamepad.right_stick_x));
        }

    }

    /**
     * Allows caller to scale the sensitivity of the gamepad
     *
     * @param drivePowerMult The new power scale factor
     */
    public void setDrivePowerMult(double drivePowerMult) {
        this.DrivePowerMult = drivePowerMult;
    }


    public void flipFrontAndBack() {
        frontDrive = frontDrive != true;
    }
}
