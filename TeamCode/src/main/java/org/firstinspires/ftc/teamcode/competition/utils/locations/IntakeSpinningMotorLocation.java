package org.firstinspires.ftc.teamcode.competition.utils.locations;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.competition.utils.interactions.InteractionSurface;
import org.firstinspires.ftc.teamcode.competition.utils.interactions.items.StandardMotor;

public class IntakeSpinningMotorLocation extends Location {

    public enum Action {
        SET_SPEED,
        SET_VOLTAGE,
        MOVE_DISTANCE_IN_INCHES
    }

    private final StandardMotor MOTOR;

    public IntakeSpinningMotorLocation(HardwareMap hardware) {
        MOTOR = new StandardMotor(hardware, hardware.appContext.getString(R.string.INTAKE_SPINNING_MOTOR), DcMotorSimple.Direction.FORWARD);
    }

    public void handleInput(Action action, int input) {
        switch(action) {
            case SET_SPEED:
                MOTOR.driveWithEncoder(input);
                break;
            case SET_VOLTAGE:
                MOTOR.driveWithoutEncoder(input);
                break;
            case MOVE_DISTANCE_IN_INCHES:
                MOTOR.driveDistance(input, 50);
                break;
        }
    }

    @Override
    public void stop() {
        MOTOR.stop();
    }

    @Override
    public boolean isInputLocation() {
        return true;
    }

    @Override
    public boolean isOutputLocation() {
        return false;
    }

    @Override
    public InteractionSurface getInternalInteractionSurface() {
        return MOTOR;
    }

}
