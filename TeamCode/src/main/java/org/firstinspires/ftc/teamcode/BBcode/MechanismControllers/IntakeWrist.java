package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    OpMode _OpMode;
    Servo _IntakeWrist;
    public IntakeWrist (OpMode opMode) {
        _OpMode = opMode;
        _IntakeWrist = _OpMode.hardwareMap.tryGet(Servo.class, "intakeWrist");
    }

    //-----------------------------------------
    //Positions
    double pickupPosition = 0;
    double transferPosition = 0;

    //-----------------------------------------
    //Actions for TeleOp
    public void PickUp() {
        RotateTo(pickupPosition);}
    public void Transfer() {
        RotateTo(transferPosition);}

    //RR actions for auto
    public Action PickUp_RR() {return new RotateTo_RR(pickupPosition);}
    public Action Transfer_RR() {return new RotateTo_RR(transferPosition);}

    //-----------------------------------------
    //TeleOp base action
    void RotateTo(double position) {
        if (_IntakeWrist == null) {
            _OpMode.telemetry.addLine("intakeWrist servo not found!");
        }
        else {
            _IntakeWrist.setPosition(position);
        }
    }

    //RR auto base action
    class RotateTo_RR implements Action {
        double position;
        RotateTo_RR(double p) {position = p;}
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            RotateTo(position);
            return false;
        }
    }
}