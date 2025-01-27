package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    OpMode _opMode;
    Servo _intakeWrist;
    public IntakeWrist (OpMode opMode) {
        _opMode = opMode;
        _intakeWrist = _opMode.hardwareMap.tryGet(Servo.class, "intakeWrist");
    }

    //-----------------------------------------
    //Positions
    double pickupPosition = 0;
    double transferPosition = 0;

    //-----------------------------------------
    //Actions for TeleOp
    public void PickUp() {MoveTo(pickupPosition);}
    public void Transfer() {MoveTo(transferPosition);}

    //RR actions for auto
    public Action PickUp_RR() {return new MoveTo_RR(pickupPosition);}
    public Action Transfer_RR() {return new MoveTo_RR(transferPosition);}

    //-----------------------------------------
    //TeleOp base action
    void MoveTo(double position) {
        if (_intakeWrist == null) {
            _opMode.telemetry.addLine("Intake Wrist servo not found!");
        }
        else {
            _intakeWrist.setPosition(position);
        }
    }

    //RR auto base action
    class MoveTo_RR implements Action {
        double position;
        MoveTo_RR(double p) {position = p;}
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            MoveTo(position);
            return false;
        }
    }
}