package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.BBcode.TelemetryHelper;

public class OuttakeSlides {
    OpMode _OpMode;
    DcMotorEx _OuttakeSlideRight;
    DcMotorEx _OuttakeSlideLeft;

    public OuttakeSlides(OpMode opMode) {
        TelemetryHelper telemetryHelper = new TelemetryHelper(opMode);
        _OpMode = opMode;
        _OuttakeSlideRight = _OpMode.hardwareMap.tryGet(DcMotorEx.class, "outtakeSlideRight");
        telemetryHelper.initMotorTelemetry(_OuttakeSlideRight, "OMR");
        _OuttakeSlideLeft = _OpMode.hardwareMap.tryGet(DcMotorEx.class, "outtakeSlideLeft");
        telemetryHelper.initMotorTelemetry(_OuttakeSlideLeft, "OMR");
    }

    //-----------------------------------------
    //Constants
    final double ticksPerInch = 116.26;
    final double defaultExtendSpeed = 0.75;

    //Position
    final double highBasketDistance = 0;
    final double specimenDistance = 0;
    final double hangDistance = 0;
    final double homeDistance = 0;

    //-----------------------------------------

    private int InchConverterToTicks(double lengthInches) {
        //need to tune
        //Full motor rotation = ? ticks
        //? inches per rotation
        //~? ticks per inch

        //int ticksPerInch = 1541;
        double ticksPerInch = 537.7 / 4.625;
        return (int) (lengthInches * ticksPerInch);
    }
    public DcMotorEx get_outtakeMotorRight() {
        return _OuttakeSlideRight;
    }
    public DcMotorEx get_outtakeMotorLeft() {
        return _OuttakeSlideLeft;
    }
    public boolean getIsOuttakeRightHighBasketExtend() {return _OuttakeSlideRight.getCurrentPosition() > InchConverterToTicks(highBasketDistance - 1);}
    public boolean getIsOuttakeLeftHighBasketExtend() {return _OuttakeSlideLeft.getCurrentPosition() > InchConverterToTicks(highBasketDistance - 1);}
    public boolean getIsOuttakeRightSpecimenExtend() {return _OuttakeSlideLeft.getCurrentPosition() > InchConverterToTicks(specimenDistance - 1);}
    public boolean getIsOuttakeLeftSpecimenExtend() {return _OuttakeSlideLeft.getCurrentPosition() > InchConverterToTicks(specimenDistance - 1);}
    public boolean getIsOuttakeRightHangExtend() {return _OuttakeSlideLeft.getCurrentPosition() > InchConverterToTicks(hangDistance - 1);}
    public boolean getIsOuttakeLeftHangExtend() {return _OuttakeSlideLeft.getCurrentPosition() > InchConverterToTicks(hangDistance - 1);}
    public boolean getIsOuttakeRightClosedExtend() {return _OuttakeSlideLeft.getCurrentPosition() < InchConverterToTicks(homeDistance + 1);}
    public boolean getIsOuttakeLeftClosedExtend() {return _OuttakeSlideLeft.getCurrentPosition() < InchConverterToTicks(homeDistance + 1);}

    public void resetEncoderRight() {
        if (_OuttakeSlideRight == null) {
            _OpMode.telemetry.addLine("outtakeMotorRight not found!");
            return;
        }
        else {
            _OuttakeSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void resetEncoderLeft() {
        if (_OuttakeSlideLeft == null) {
            _OpMode.telemetry.addLine("outtakeMotorLeft not found!");
            return;
        }
        else {
            _OuttakeSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    //-----------------------------------------
    //Actions for TeleOp
    public void ExtendToHighBasket() {ExtendTo(highBasketDistance, defaultExtendSpeed);}
    public void ExtendToSpecimen() {ExtendTo(specimenDistance, defaultExtendSpeed);}
    public void ExtendToHang() {ExtendTo(hangDistance, defaultExtendSpeed);}
    public void ExtendToHome() {ExtendTo(homeDistance, defaultExtendSpeed);}

    //RR actions for auto
    public Action ExtendToHighBasket_RR() {return new ExtendTo_RR(highBasketDistance, defaultExtendSpeed);}
    public Action ExtendToSpecimen_RR() {return new ExtendTo_RR(specimenDistance, defaultExtendSpeed);}
    public Action ExtendToHang_RR() {return new ExtendTo_RR(hangDistance, defaultExtendSpeed);}
    public Action ExtendToHome_RR() {return new ExtendTo_RR(homeDistance, defaultExtendSpeed);}

    //-----------------------------------------
    //TeleOp base action
    void ExtendTo(double length, double power) {
        if (_OuttakeSlideLeft == null) {_OpMode.telemetry.addLine("outtakeSlideLeft not found!");}
        else if (_OuttakeSlideRight == null) {_OpMode.telemetry.addLine("outtakeSlideRight not found!");}
        else {
            int targetPos = (int)Math.round(length * ticksPerInch);

            //Left motor
            _OuttakeSlideLeft.setDirection(DcMotor.Direction.REVERSE);
            _OuttakeSlideLeft.setTargetPosition(targetPos);
            _OuttakeSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            _OuttakeSlideLeft.setPower(power);

            //Right motor
            _OuttakeSlideRight.setDirection(DcMotor.Direction.REVERSE);
            _OuttakeSlideRight.setTargetPosition(targetPos);
            _OuttakeSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            _OuttakeSlideRight.setPower(power);
        }
    }

    //RR auto base action
    class ExtendTo_RR implements Action {
        double length;
        double power;
        ExtendTo_RR(double l, double p) {length = l; power = p;}
        public boolean run(@NonNull TelemetryPacket packet) {
            ExtendTo(length, power);
            return false;
        }
    }
}
