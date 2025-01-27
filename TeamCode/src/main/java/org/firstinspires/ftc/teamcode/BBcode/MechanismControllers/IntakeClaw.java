package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {
    OpMode _opMode;
    Servo _intakeClaw;
    public IntakeClaw(OpMode opMode)
    {
        _opMode = opMode;
        _intakeClaw = _opMode.hardwareMap.tryGet(Servo.class, "intakeClaw");
    }

    //-----------------------------------------
    //Positions
    double closePosition = 0;
    double openPosition = 0;

    //-----------------------------------------
    //TeleOp base action
    public void Open() {MoveTo(openPosition);}
    public void Close() {MoveTo(closePosition);}

    //RR actions for auto
    public Action Open_RR() {return new MoveTo_RR(openPosition);}
    public Action Close_RR() {return new MoveTo_RR(closePosition);}

    //-----------------------------------------
    //TeleOp base action
    void MoveTo(double position)
    {
        if (_intakeClaw == null)
        {
            _opMode.telemetry.addLine("Intake Claw Servo not found!");
        } else {
            _intakeClaw.setPosition(position);
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