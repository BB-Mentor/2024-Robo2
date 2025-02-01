package org.firstinspires.ftc.teamcode.BBcode.UtilClasses;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.PinpointDrive;

public class AutoUtils {
    OpMode opMode;
    public AutoUtils(OpMode o) {
        opMode = o;}
    //---------------------------
    public Action Wait(double seconds) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                return (new PinpointDrive(opMode.hardwareMap, new Pose2d(0,0,0))
                        .actionBuilder(new Pose2d(0,0,0))
                        .waitSeconds(seconds))
                        .build().run(telemetryPacket);
            }
        };
    }
}
