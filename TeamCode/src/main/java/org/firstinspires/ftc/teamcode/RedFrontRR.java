package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.teamcode.NewBlueRightProcessor;


@Config
@Autonomous(name = "RedFrontRR", group = "Autonomous")
public class RedFrontRR extends LinearOpMode {
    private VisionPortal visionPortal;
    private NewBlueRightProcessor colorMassDetectionProcessor;

    public class Gate {
        private Servo gate;

        public Gate(HardwareMap hardwareMap) {
            gate = hardwareMap.get(Servo.class, "S-Basket");
        }

        public class OpenGate implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                gate.setPosition(0.5);
                return false;
            }
        }
        public Action openGate() {
            return new OpenGate();
        }

        public class CloseGate implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                gate.setPosition(.175);
                return false;
            }
        }
        public Action closeGate() {
            return new CloseGate();
        }
    }

    public class Yellow {
        private Servo yellow;

        public Yellow(HardwareMap hardwareMap) {
            yellow = hardwareMap.get(Servo.class, "Pixel");
        }

        public class PlaceYellow implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                yellow.setPosition(.4);
                return false;
            }
        }
        public Action placeYellow() {
            return new PlaceYellow();
        }

        public class InitYellow implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                yellow.setPosition(1);
                return false;
            }
        }
        public Action initYellow() {
            return new InitYellow();
        }


    }

    @Override
    public void runOpMode() {
        colorMassDetectionProcessor = new NewBlueRightProcessor();

        colorMassDetectionProcessor.setDetectionColor(true); //true is red, false is blue
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam"))
                .addProcessors(colorMassDetectionProcessor)
                .build();

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-36, -64, Math.toRadians(90)));
        Gate gate = new Gate(hardwareMap);
        Yellow yellow = new Yellow(hardwareMap);

        // vision here that outputs position
        int visionOutputPosition = 2;

        Action trajectoryLeftPurple;
        Action trajectoryLeftYellow;
        Action trajectoryMiddlePurple;
        Action trajectoryMiddleYellow;
        Action trajectoryRightPurple;
        Action trajectoryRightYellow;
        Action wait;
        Action trajectoryLeftCloseOut;
        Action trajectoryMiddleCloseOut;
        Action trajectoryRightCloseOut;

        trajectoryLeftPurple = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-50, -27.5), Math.toRadians(270))
                .build();
        trajectoryLeftYellow = drive.actionBuilder(new Pose2d(-50, -27.5, Math.toRadians(270)))
                .setReversed(true)
                .splineTo(new Vector2d(-50, -20), Math.toRadians(90))
                .splineTo(new Vector2d(-12, -10), 0)
                .splineToConstantHeading(new Vector2d(30, -10), 0)
                .splineToConstantHeading(new Vector2d(51, -32), 0)
                .build();
        trajectoryMiddlePurple = drive.actionBuilder(drive.pose)
                .splineToLinearHeading(new Pose2d(-38, -18, Math.toRadians(270)), Math.toRadians(90))
                .build();
        trajectoryMiddleYellow = drive.actionBuilder(new Pose2d(-38, -18, Math.toRadians(270)))
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-36, -14), Math.toRadians(90))
                .splineTo(new Vector2d(-12, -10), 0)
                .splineToConstantHeading(new Vector2d(30, -10), 0)
                .splineToConstantHeading(new Vector2d(51, -39), 0)
                .build();
        trajectoryRightPurple = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(-29,-35), 0)
                .build();
        trajectoryRightYellow = drive.actionBuilder(new Pose2d(-28, -35, 0))
                .setReversed(true)
                .splineTo(new Vector2d(-40, -25), Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-12, -10, Math.toRadians(180)), 0)
                .splineToConstantHeading(new Vector2d(30, -10), 0)
                .splineToConstantHeading(new Vector2d(51, -46), 0)
                .build();
        wait = drive.actionBuilder(drive.pose)
                .waitSeconds(1)
                .build();
        trajectoryLeftCloseOut = drive.actionBuilder(new Pose2d(49, -32, Math.PI))
                .waitSeconds(1)
                .lineToX(49)
                .strafeToConstantHeading(new Vector2d(49, -12))
                .build();
        trajectoryMiddleCloseOut = drive.actionBuilder(new Pose2d(49, -30, Math.PI))
                .waitSeconds(1)
                .lineToX(49)
                .strafeToConstantHeading(new Vector2d(49, -12))
                .build();
        trajectoryRightCloseOut = drive.actionBuilder(new Pose2d(49, -46, Math.PI))
                .waitSeconds(1)
                .lineToX(49)
                .strafeToConstantHeading(new Vector2d(49, -12))
                .build();


        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Currently Recorded Position: ", colorMassDetectionProcessor.getPropLocation());
            telemetry.addData("Camera State: ", visionPortal.getCameraState());
            telemetry.addData("Position during Init", position);
            FtcDashboard.getInstance().startCameraStream(colorMassDetectionProcessor, 30);
            telemetry.update();
        }
        NewBlueRightProcessor.PropPositions recordedPropPosition = colorMassDetectionProcessor.getPropLocation();
        switch (recordedPropPosition) {
            case LEFT:
                visionOutputPosition = 1;
                break;
            case MIDDLE:
                visionOutputPosition = 2;
                break;
            case RIGHT:
                visionOutputPosition = 3;
                break;
        }

        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryPurpleChosen;
        Action trajectoryYellowChosen;
        Action trajectoryCloseOutChosen;

        if (startPosition == 2) {
            Actions.runBlocking(gate.openGate());
            trajectoryPurpleChosen = trajectoryMiddlePurple;
            trajectoryYellowChosen = trajectoryMiddleYellow;
            trajectoryCloseOutChosen = trajectoryMiddleCloseOut;
        } else if (startPosition == 3) {
            trajectoryPurpleChosen = trajectoryRightPurple;
            trajectoryYellowChosen = trajectoryRightYellow;
            trajectoryCloseOutChosen = trajectoryRightCloseOut;
        } else {
            trajectoryPurpleChosen = trajectoryLeftPurple;
            trajectoryYellowChosen = trajectoryLeftYellow;
            trajectoryCloseOutChosen = trajectoryLeftCloseOut;
        }

        Actions.runBlocking(yellow.initYellow());

        Actions.runBlocking(
                new SequentialAction(
                        gate.closeGate(),
                        trajectoryPurpleChosen,
                        gate.openGate(),
                        trajectoryYellowChosen,
                        yellow.placeYellow(),
                        wait,
                        yellow.initYellow(),
                        trajectoryCloseOutChosen
                        //yellow.initYellow()
                )
        );
        telemetry.addLine("Didnt Closed Camera.");
        telemetry.update();
    }
}