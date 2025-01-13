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

import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.Arm;
import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.Viper;
import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.WristClaw;

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


    HighBasketState highBasketState = HighBasketState.Home;
    SpecimenClipState specimenClipState = SpecimenClipState.Home;

    ElapsedTime wristTimer = new ElapsedTime();

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
        Arm arm = new Arm(this, telemetryHelper);
        Viper viper = new Viper(this);
        WristClaw wristClaw = new WristClaw(this);
        arm.Reset();
        viper.StopAndResetEncoder();
        wristTimer.reset();

        //Call the function to initialize telemetry functions
//        telemetryHelper.initMotorTelemetry( viperMotor, "viperMotor");
        telemetryHelper.initGamepadTelemetry(gamepad1);
        telemetryHelper.initGamepadTelemetry(gamepad2);
        //Where the start button is clicked, put some starting commands after
        waitForStart();
        arm.MoveToHome();

        while(opModeIsActive()){ //while loop for when program is active

            //Drive code
            drivetrain.Drive();

            handleGamepad1(viper);
            handleGamepad2(wristClaw);

            switch (highBasketState) {
                case Home:
                    if () {

                    }
                    break;
                case RaiseOuttakeSample:
                    if () {

                    }
                    else if () {

                    }
                    break;

                case WristDumpSample:
                    if () {

                    }
                    else if () {

                    }
                    break;

                case HighBasket:
                    if () {

                    }
                    break;

                case WristUpSample:
                    if () {

                    }
                    break;

                case LowerOuttakeSample:
                    if () {

                    }
            }

            switch (specimenClipState) {
                case Home:
                    if () {

                    }
                    break;
                case RaiseOuttakeSpecimen:
                    if () {

                    }
                    else if () {

                    }
                    break;

                case WristOutSpecimen:
                    if () {

                    }
                    else if () {

                    }
                    break;

                case SpecimenHang:
                    if () {

                    }
                    break;

                case WristInSpecimen:
                    if () {

                    }
                    break;

                case LowerOuttakeSpecimen:
                    if () {

                    }


            }

            telemetry.update();

        }
    }
}