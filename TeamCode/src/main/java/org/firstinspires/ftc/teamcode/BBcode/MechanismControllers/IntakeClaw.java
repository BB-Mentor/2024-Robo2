package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {
    OpMode _OpMode;
    Servo _IntakeClaw;
    public IntakeClaw(OpMode opMode)
    {
        _OpMode = opMode;
        _IntakeClaw = _OpMode.hardwareMap.tryGet(Servo.class, "intakeClaw");
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
        if (_IntakeClaw == null)
        {
            _OpMode.telemetry.addLine("intakeClaw Servo not found!");
        } else {
            _IntakeClaw.setPosition(position);
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