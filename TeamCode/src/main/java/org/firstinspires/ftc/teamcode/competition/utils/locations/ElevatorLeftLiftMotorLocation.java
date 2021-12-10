package org.firstinspires.ftc.teamcode.competition.utils.locations;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.competition.utils.interactions.InteractionSurface;
import org.firstinspires.ftc.teamcode.competition.utils.interactions.items.StandardMotor;

public class ElevatorLeftLiftMotorLocation extends Location {

    public enum Action {
        SET_SPEED,
        SET_VOLTAGE,
        MOVE_DISTANCE_IN_INCHES
    }

    private final StandardMotor LEFT;

    public ElevatorLeftLiftMotorLocation(HardwareMap hardware) {
        LEFT = new StandardMotor(hardware, hardware.appContext.getString(R.string.LIFT_LEFT_ELEVATOR_MOTOR), DcMotorSimple.Direction.REVERSE);
    }

    public void handleInput(Action action, int input) {
        switch(action) {
            case SET_SPEED:
                LEFT.driveWithEncoder(input);
                break;
            case SET_VOLTAGE:
                LEFT.driveWithoutEncoder(input);
                break;
            case MOVE_DISTANCE_IN_INCHES:
                LEFT.driveDistance(input, 50);
                break;
        }
    }

    @Override
    public void stop() {
        LEFT.stop();
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
        return LEFT;
    }

}
