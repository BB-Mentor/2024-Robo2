package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWristClaw {
    OpMode _opMode;
    Servo _intakeWrist;
    Servo _intakeClaw;
    public IntakeWristClaw (OpMode opMode)
    {
        _opMode = opMode;
        _intakeWrist = _opMode.hardwareMap.tryGet(Servo.class, "intakeWrist");
        _intakeClaw = _opMode.hardwareMap.tryGet(Servo.class, "intakeClaw");
    }
    //-----------------------------------------
    //Variable Storage:
    //need to tune numbers
    double pickupPosition = 0;
    double dropPosition = 0;
    double closePosition = 0;
    double openPosition = 0;
    //-----------------------------------------
    //actions for teleop
    public void intakeWristPickupPosition() {
        intakeWristCustom(pickupPosition);
    }

    public void intakeWristDropPosition() {
        intakeWristCustom(dropPosition);
    }

    public void intakeClawClosePosition() {
        intakeClawCustom(closePosition);
    }

    public void intakeClawOpenPosition() {
        intakeClawCustom(openPosition);
    }
    //-----------------------------------------
    //RR actions for auto
    public Action OpenClaw() {return new moveClawTo(openPosition);}
    public Action CloseClaw() {return new moveClawTo(closePosition);}

    //-----------------------------------------
    //base actions for teleop
    public void intakeWristCustom(double position)
    {
        if (_intakeWrist == null)
        {
            _opMode.telemetry.addLine("Intake Wrist servo not found!");
        } else {
            _intakeWrist.setPosition(position);
        }

    }
    public void intakeClawCustom(double position)
    {
        if (_intakeClaw == null)
        {
            _opMode.telemetry.addLine("Intake Claw Servo not found!");
        } else {
            _intakeClaw.setPosition(position);
        }
    }
    //-----------------------------------------
    //base RR actions for auto
    public class moveClawTo implements Action {
        double position;
        moveClawTo(double p) {position = p;}
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeClawCustom(position);
            return false;
        }
    }
}