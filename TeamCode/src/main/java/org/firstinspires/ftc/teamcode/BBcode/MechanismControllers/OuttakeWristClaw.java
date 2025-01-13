package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeWristClaw {
    OpMode _opMode;
    Servo _outtakeWrist;
    Servo _outtakeClaw;
    public OuttakeWristClaw (OpMode opMode)
    {
        _opMode = opMode;
        _outtakeWrist = _opMode.hardwareMap.tryGet(Servo.class, "outtakeWrist");
        _outtakeClaw = _opMode.hardwareMap.tryGet(Servo.class, "outtakeClaw");
    }
    //-----------------------------------------
    //Variable Storage:
    //need to tune numbers
    double samplePickupPosition = 0;
    double sampleDropPosition = 0;
    double specimenPickupPosition = 0;
    double specimenDropPosition = 0;
    double openPosition = 0;
    double closePosition = 0;
    //-----------------------------------------

    public void OuttakeWristSamplePickupPosition() {
        OuttakeWristCustom(samplePickupPosition);
    }

    public void OuttakeWristSampleDropPosition() {
        OuttakeWristCustom(sampleDropPosition);
    }

    public void OuttakeWristSpecimenPickupPosition() {
        OuttakeWristCustom(specimenPickupPosition);
    }

    public void OuttakeWristSpecimenDropPosition() {
        OuttakeWristCustom(specimenDropPosition);
    }

    public void OuttakeClawOpenPosition() {
        OuttakeClawCustom(openPosition);
    }

    public void OuttakeClawClosePosition() {
        OuttakeClawCustom(closePosition);
    }

    public void OuttakeWristCustom(double position)
    {
        if (_outtakeWrist == null)
        {
            _opMode.telemetry.addLine("Outtake Wrist servo not found!");
        } else {
            _outtakeWrist.setPosition(position);
        }

    }
    public void OuttakeClawCustom(double position)
    {
        if (_outtakeClaw == null)
        {
            _opMode.telemetry.addLine("Outtake Claw Servo not found!");
        } else {
            _outtakeClaw.setPosition(position);
        }
    }
}
