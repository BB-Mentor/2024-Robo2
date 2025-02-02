package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeWrist {
    OpMode _OpMode;
    Servo _OuttakeWrist;
    public OuttakeWrist(OpMode opMode)
    {
        _OpMode = opMode;
        _OuttakeWrist = _OpMode.hardwareMap.tryGet(Servo.class, "outtakeWrist");
    }

    //-------------------------------------- ---
    //Positions
    double transferPosition = 0;
    double dropPosition = 0;
    double clipPosition = 0;

    //-----------------------------------------
    //Actions for TeleOp
    public void Transfer() {
        RotateTo(transferPosition);
    }
    public void Drop() {
        RotateTo(dropPosition);
    }
    public void Clip() {
        RotateTo(clipPosition);
    }

    //RR actions for auto
    public Action Transfer_RR() {return new RotateTo_RR(transferPosition);}
    public Action Drop_RR() {return new RotateTo_RR(dropPosition);}
    public Action Clip_RR() {return new RotateTo_RR(clipPosition);}

    //-----------------------------------------
    //TeleOp base action
    void RotateTo(double position)
    {
        if (_OuttakeWrist == null)
        {
            _OpMode.telemetry.addLine("outtakeWrist servo not found!");
        } else {
            _OuttakeWrist.setPosition(position);
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
