package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSlides {
    OpMode _opMode;
    Servo _intakeSlideRight;
    Servo _intakeSlideLeft;
    public IntakeSlides(OpMode opMode) {
        _opMode = opMode;
        _intakeSlideRight = _opMode.hardwareMap.tryGet(Servo.class, "intakeSlideRight");
        _intakeSlideLeft = _opMode.hardwareMap.tryGet(Servo.class, "intakeSlideLeft");
    }

    //-----------------------------------------
    //Constants
    final double lengthToServoPosition = 1;

    //Positions
    double retractLength = 0;
    double extendLength = 0;

    //-----------------------------------------
    //Actions for TeleOp
    public void Extend() {MoveTo(extendLength);}
    public void Retract() {MoveTo(retractLength);}

    //RR actions for auto
    public Action Extend_RR() {return new MoveTo_RR(extendLength);}
    public Action Retract_RR() {return new MoveTo_RR(retractLength);}

    //-----------------------------------------
    //TeleOp base action
     void MoveTo(double length) {
        if (_intakeSlideLeft == null) {_opMode.telemetry.addLine("intakeSlideLeft servo not found!");}
        else if (_intakeSlideRight == null) {_opMode.telemetry.addLine("intakeSlideRight servo not found!");}
        else {
            double servoPosition = length * lengthToServoPosition;
            _intakeSlideLeft.setPosition(servoPosition);
            _intakeSlideRight.setPosition(servoPosition);
        }
    }

    //RR auto base action
    class MoveTo_RR implements Action {
        double length;
        MoveTo_RR(double l) {length = l;}
        public boolean run(@NonNull TelemetryPacket packet) {
            MoveTo(length);
            return false;
        }
    }
}
