package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSlides {
    OpMode _opMode;
    Servo _intakeSlideRight;
    Servo _intakeSlideLeft;
    public IntakeSlides (OpMode opMode)
    {
        _opMode = opMode;
        _intakeSlideRight = _opMode.hardwareMap.tryGet(Servo.class, "intakeSlideRight");
        _intakeSlideLeft = _opMode.hardwareMap.tryGet(Servo.class, "intakeSlideLeft");
    }
    //-----------------------------------------
    //Variable Storage:
    //need to tune numbers
    double inPosition = 0;
    double middlePosition = 0;
    double outPosition = 0;
    //-----------------------------------------

    public void intakeInPosition() {
        intakeSlideRightCustom(inPosition);
        intakeSlideLeftCustom(inPosition);
    }
    public void intakeMiddlePosition() {
        intakeSlideRightCustom(middlePosition);
        intakeSlideLeftCustom(middlePosition);
    }
    public void intakeOutPosition() {
        intakeSlideRightCustom(outPosition);
        intakeSlideLeftCustom(outPosition);
    }
    public void intakeSlideRightCustom(double position)
    {
        if (_intakeSlideRight == null)
        {
            _opMode.telemetry.addLine("intakeSlideRight servo not found!");
        } else {
            _intakeSlideRight.setPosition(position);
        }

    }
    public void intakeSlideLeftCustom(double position)
    {
        if (_intakeSlideLeft == null)
        {
            _opMode.telemetry.addLine("intakeSlideLeft Servo not found!");
        } else {
            _intakeSlideLeft.setPosition(position);
        }
    }
}
