package org.firstinspires.ftc.teamcode.BBcode.MechanismControllers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.BBcode.TelemetryHelper;

public class OuttakeSlides {
    OpMode _opMode;
    DcMotorEx _outtakeMotorRight;
    DcMotorEx _outtakeMotorLeft;

    public OuttakeSlides(OpMode opMode) {
        TelemetryHelper telemetryHelper = new TelemetryHelper(opMode);
        _opMode = opMode;
        _outtakeMotorRight = _opMode.hardwareMap.tryGet(DcMotorEx.class, "outtakeMotorRight");
        telemetryHelper.initMotorTelemetry(_outtakeMotorRight, "OMR");
        _outtakeMotorLeft = _opMode.hardwareMap.tryGet(DcMotorEx.class, "outtakeMotorLeft");
        telemetryHelper.initMotorTelemetry(_outtakeMotorLeft, "OMR");
    }

    //--------------------------
    //Variable Storage:
    //Need to tune
    //RPM ? = ? Ticks per Revolution
    int highBasketExtend = 0;
    int specimenExtend = 0;
    int hangExtend = 0;
    int closedExtend = 0;
    //--------------------------

    private int InchConverterToTicks(double lengthInches) {
        //need to tune
        //Full motor rotation = ? ticks
        //? inches per rotation
        //~? ticks per inch

        //int ticksPerInch = 1541;
        double ticksPerInch = 537.7 / 4.625;
        return (int) (lengthInches * ticksPerInch);
    }

    public void ExtendHighBasket() {
        outtakeMotorRightCustom(highBasketExtend, 0.75);

    }

    public void ExtendSpecimen() {
        outtakeMotorRightCustom(specimenExtend, 0.75);

    }

    public void ExtendHang() {
        outtakeMotorRightCustom(hangExtend, 0.75);

    }

    public void ExtendClosed() {
        outtakeMotorRightCustom(closedExtend, 0.75);

    }

    public void outtakeMotorRightCustom(double lengthInches, double power) {
        if (_outtakeMotorRight == null) {
            _opMode.telemetry.addLine("outtakeMotorRight not found!");
            return;
        }
        _outtakeMotorRight.setDirection(DcMotor.Direction.REVERSE);
        int extensionTicks = InchConverterToTicks(lengthInches);
        _outtakeMotorRight.setTargetPosition(extensionTicks);    //Sets Target Tick Position
        _outtakeMotorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        _outtakeMotorRight.setPower(power);
    }
    public void outtakeMotorLeftCustom ( double lengthInches, double power) {
        if (_outtakeMotorLeft == null) {
            _opMode.telemetry.addLine("outtakeMotorLeft not found!");
            return;
        }
        _outtakeMotorLeft.setDirection(DcMotor.Direction.REVERSE);
        int extensionTicks = InchConverterToTicks(lengthInches);
        _outtakeMotorLeft.setTargetPosition(extensionTicks);    //Sets Target Tick Position
        _outtakeMotorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        _outtakeMotorLeft.setPower(power);
        }
}
