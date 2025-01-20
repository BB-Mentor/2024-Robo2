package org.firstinspires.ftc.teamcode.BBcode;


//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.OuttakeWristClaw;
import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.OuttakeSlides;
import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.IntakeSlides;
import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.IntakeWristClaw;

@TeleOp(name = "MainTeleopState")
public class MainTeleOpState extends LinearOpMode{
    enum HighBasketState {
        Home,
        RaiseOuttakeSample,
        WristDumpSample,
        HighBasket,
        WristUpSample,
        LowerOuttakeSample
    }

    enum SpecimenClipState {
        Home,
        RaiseOuttakeSpecimen,
        WristOutSpecimen,
        SpecimenHang,
        WristInSpecimen,
        LowerOuttakeSpecimen
    }

    enum HangState {
        Home,
        RaiseOuttakeHang,
        Hang,
        Lower
    }

    enum SubmersibleIntakeState {
        Home,
        ExtendIntakeSubmersible,
        WristDownSubmersible,
        IntakeAtSubmersible,
        WristUpSubmersible,
        RetractIntakeSubmersible

    }

    HighBasketState highBasketState = HighBasketState.Home;
    SpecimenClipState specimenClipState = SpecimenClipState.Home;
    HangState hangState = HangState.Home;
    SubmersibleIntakeState submersibleIntakeState = SubmersibleIntakeState.Home;

    ElapsedTime outtakewristTimer = new ElapsedTime();
//Need to Tune time
    final double wristFlipTime = 0.75;

    private void handleGamepad1 () {

    }

    private void handleGamepad2 () {

    }

    @Override
    public void runOpMode() throws InterruptedException{
        // Initialization Code Goes Here
        TelemetryHelper telemetryHelper = new TelemetryHelper(this);
        //Allows for telemetry to be added to without clearing previous data. This allows setting up telemetry functions to be called in the loop or adding telemetry items within a function and not having it cleared on next loop
        telemetry.setAutoClear(false);
        //Init for the other classes this opmode pulls methods from
        MecanumDrivetrain drivetrain = new MecanumDrivetrain(this);
        OuttakeWristClaw outtakeWristClaw = new OuttakeWristClaw(this);
        OuttakeSlides outtakeSlides = new OuttakeSlides(this);
        IntakeWristClaw intakeWristClaw = new IntakeWristClaw(this);
        outtakeSlides.resetEncoderRight();
        outtakeSlides.resetEncoderLeft();
        outtakewristTimer.reset();

        //Call the function to initialize telemetry functions
//        telemetryHelper.initMotorTelemetry( viperMotor, "viperMotor");
        telemetryHelper.initGamepadTelemetry(gamepad1);
        telemetryHelper.initGamepadTelemetry(gamepad2);
        //Where the start button is clicked, put some starting commands after
        waitForStart();

        while(opModeIsActive()){ //while loop for when program is active

            //Drive code
            drivetrain.Drive();

            handleGamepad1();
            handleGamepad2();

            switch (highBasketState) {
                case Home:
                    if (gamepad2.dpad_up && gamepad2.left_trigger > 0) {
                        outtakeSlides.ExtendHighBasket();
                        highBasketState = HighBasketState.RaiseOuttakeSample;
                    }
                    break;
                case RaiseOuttakeSample:
                    if (outtakeSlides.getIsOuttakeLeftHangExtend() && outtakeSlides.getIsOuttakeRightHangExtend()) {
                        outtakeWristClaw.OuttakeWristSampleDropPosition();
                        highBasketState = HighBasketState.WristDumpSample;
                        outtakewristTimer.reset();
                    }
                    else if (gamepad2.dpad_down && gamepad2.left_trigger > 0) {
                        outtakeSlides.ExtendClosed();
                        highBasketState = HighBasketState.LowerOuttakeSample;
                    }
                    break;

                case WristDumpSample:
                    if (outtakewristTimer.seconds() >= wristFlipTime) {
                        highBasketState = HighBasketState.HighBasket;
                    }
                    else if (gamepad2.dpad_down && gamepad2.left_trigger > 0) {
                        outtakeWristClaw.OuttakeWristSamplePickupPosition();
                        highBasketState = HighBasketState.WristUpSample;
                        outtakewristTimer.reset();
                    }
                    break;

                case HighBasket:
                    if (gamepad2.dpad_down && gamepad2.left_trigger > 0) {
                        outtakeWristClaw.OuttakeWristSamplePickupPosition();
                        highBasketState = HighBasketState.WristUpSample;
                        outtakewristTimer.reset();
                    }
                    break;

                case WristUpSample:
                    if (outtakewristTimer.seconds() >= wristFlipTime) {
                        outtakeSlides.ExtendClosed();
                        highBasketState = HighBasketState.LowerOuttakeSample;
                    }
                    break;

                case LowerOuttakeSample:
                    if (outtakeSlides.getIsOuttakeRightClosedExtend() && outtakeSlides.getIsOuttakeLeftClosedExtend()) {
                        highBasketState = HighBasketState.Home;
                    }
                    break;
            }

            switch (specimenClipState) {
                case Home:
                    if (gamepad2.dpad_up && gamepad2.right_trigger > 0) {
                        outtakeSlides.ExtendSpecimen();
                        specimenClipState = SpecimenClipState.RaiseOuttakeSpecimen;
                    }
                    break;
                case RaiseOuttakeSpecimen:
                    if (outtakeSlides.getIsOuttakeRightSpecimenExtend() && outtakeSlides.getIsOuttakeLeftSpecimenExtend()) {
                        outtakeWristClaw.OuttakeWristSpecimenDropPosition();
                        specimenClipState = SpecimenClipState.WristOutSpecimen;
                        outtakewristTimer.reset();
                    }
                    else if (gamepad2.dpad_down && gamepad2.right_trigger > 0) {
                        outtakeSlides.ExtendClosed();
                        specimenClipState = SpecimenClipState.LowerOuttakeSpecimen;
                    }
                    break;

                case WristOutSpecimen:
                    if (outtakewristTimer.seconds() >= wristFlipTime) {
                        specimenClipState = SpecimenClipState.SpecimenHang;
                    }
                    else if (gamepad2.dpad_down && gamepad2.right_trigger > 0) {
                        outtakeWristClaw.OuttakeWristSamplePickupPosition();
                        specimenClipState = SpecimenClipState.WristInSpecimen;
                        outtakewristTimer.reset();
                    }
                    break;

                case SpecimenHang:
                    if (gamepad2.dpad_down && gamepad2.right_trigger > 0) {
                        outtakeWristClaw.OuttakeWristSamplePickupPosition();
                        specimenClipState = SpecimenClipState.WristInSpecimen;
                        outtakewristTimer.reset();
                    }
                    break;

                case WristInSpecimen:
                    if (outtakewristTimer.seconds() >= wristFlipTime) {
                        outtakeSlides.ExtendClosed();
                        specimenClipState = SpecimenClipState.LowerOuttakeSpecimen;
                    }
                    break;

                case LowerOuttakeSpecimen:
                    if (outtakeSlides.getIsOuttakeRightClosedExtend() && outtakeSlides.getIsOuttakeLeftClosedExtend()) {
                        specimenClipState = SpecimenClipState.Home;
                    }
                    break;


            }

            telemetry.update();

        }
    }
}