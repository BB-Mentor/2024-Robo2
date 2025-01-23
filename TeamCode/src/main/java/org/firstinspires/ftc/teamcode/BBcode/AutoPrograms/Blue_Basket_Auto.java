package org.firstinspires.ftc.teamcode.BBcode.AutoPrograms;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BBcode.MechanismActionBuilders.ViperArmActions;
import org.firstinspires.ftc.teamcode.BBcode.MechanismActionBuilders.WristClawActions;
import org.firstinspires.ftc.teamcode.PinpointDrive;


@Config
@Autonomous(name = "Blue_Basket_Auto", group = "Autonomous")
public class Blue_Basket_Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
    //Initialization steps
        //Creates instance of MechanismActionBuilders
        WristClawActions _WristClawActions = new WristClawActions(this);
        ViperArmActions _ViperArmActions = new ViperArmActions(this);

        //Initializes Pinpoint
        Pose2d initialPose = new Pose2d(31, 63, Math.toRadians(0));
        PinpointDrive drive = new PinpointDrive(hardwareMap, initialPose);

        //closes claw on init
        Actions.runBlocking(_WristClawActions.CloseClaw());

        telemetry.update();
        waitForStart();
    //----------------------------------------------------------------------------------------------

        if (isStopRequested()) return;

        Vector2d basket_clear_position = new Vector2d(42, 42);
        double basket_clear_heading = Math.toRadians(45);
        //Before new grease
        //Vector2d drop_position = new Vector2d(53, 53);
        //After new grease
        Vector2d drop_position = new Vector2d(54, 54);
        double drop_heading = Math.toRadians(45);

        Vector2d outer_sample_pickup_position = new Vector2d(46, 43.25);
        Vector2d middle_sample_pickup_position = new Vector2d(56, 43);
        double sample_pickup_heading = Math.toRadians(-90);
        Vector2d inner_sample_pickup_position = new Vector2d(57, 37.5);//57, 38.5
        double inner_sample_pickup_heading = Math.toRadians(-50);

        Action trajectory, wristPickUp1, wristPickUp2, downWait, downWait1, grabWait, closeWait, grabWait1, closeWait1, grabWait2, closeWait2, testingWait, clawOpenWait, testingWait1, clawOpenWait1, testingWait2, clawOpenWait2, wristUpWait1, wristUpWait2, wristUpWait3, viperUpWait1, viperUpWait2, viperUpWait3, armUpWait1, armUpWait2, armUpWait3, viperDownWait1, viperDownWait2, viperDownWait3, driveToClearance, driveToDrop, driveToBackAway, driveToSample1, driveToClearance1, driveToDrop1, driveToBackAway1, driveToSample2, driveToClearance2, driveToDrop2, driveToBackAway2, driveToSample3;


//        trajectory = ActionBuilder.BlueBasket(drive::actionBuilder);
//
//        driveToClearance = drive.actionBuilder(drive.pose)
//                .strafeToLinearHeading(basket_clear_position, basket_clear_heading)
//                .build();
//
//        driveToClearance1 = drive.actionBuilder(new Pose2d(outer_sample_pickup_position.x,outer_sample_pickup_position.y,sample_pickup_heading))
//                .strafeToLinearHeading(basket_clear_position, basket_clear_heading)
//                .build();
//
//        driveToClearance2 = drive.actionBuilder(new Pose2d(middle_sample_pickup_position.x,middle_sample_pickup_position.y,sample_pickup_heading))
//                .strafeToLinearHeading(basket_clear_position, basket_clear_heading)
//                .build();

        driveToDrop = drive.actionBuilder(new Pose2d(basket_clear_position.x,basket_clear_position.y,basket_clear_heading))
                .splineTo(drop_position, drop_heading)
                .build();

        driveToDrop1 = drive.actionBuilder(new Pose2d(basket_clear_position.x,basket_clear_position.y,basket_clear_heading))
                .splineTo(drop_position, drop_heading)
                .build();

        driveToDrop2 = drive.actionBuilder(new Pose2d(basket_clear_position.x,basket_clear_position.y,basket_clear_heading))
                .splineTo(drop_position, drop_heading)
                .build();

//        driveToBackAway = drive.actionBuilder(new Pose2d(drop_position.x,drop_position.y,drop_heading))
//                .lineToXConstantHeading(basket_clear_position.x)
//                .build();

        //driveToBackAway1 = drive.actionBuilder(new Pose2d(drop_position.x,drop_position.y,drop_heading))
        //        .lineToXConstantHeading(basket_clear_position.x)
         //       .build();

        //driveToBackAway2 = drive.actionBuilder(new Pose2d(drop_position.x,drop_position.y,drop_heading))
        //        .lineToXConstantHeading(basket_clear_position.x)
        //        .build();

        driveToSample1 = drive.actionBuilder(new Pose2d(drop_position.x, drop_position.y,drop_heading))
                .splineTo(outer_sample_pickup_position, sample_pickup_heading)
                .build();

        driveToSample2 = drive.actionBuilder(new Pose2d(drop_position.x, drop_position.y,drop_heading))
                .splineTo(middle_sample_pickup_position, sample_pickup_heading)
                .build();

        driveToSample3 = drive.actionBuilder(new Pose2d(drop_position.x, drop_position.y,drop_heading))
                .splineTo(inner_sample_pickup_position, inner_sample_pickup_heading)
                .build();

        viperDownWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();

        viperDownWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();

        viperDownWait3 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();

        armUpWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();

        armUpWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();

        armUpWait3 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();

        viperUpWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.5)
                .build();

        viperUpWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.5)
                .build();

        viperUpWait3 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.5)
                .build();

        wristUpWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.25)
                .build();

        wristUpWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.25)
                .build();

        wristUpWait3 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.25)
                .build();

        clawOpenWait = drive.actionBuilder(drive.pose)
                .waitSeconds(1)
                .build();
        testingWait = drive.actionBuilder(drive.pose)
                .waitSeconds(1)
                .build();

        clawOpenWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(1)
                .build();
        testingWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(1)
                .build();

        clawOpenWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(1)
                .build();
        testingWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(1)
                .build();

        grabWait = drive.actionBuilder(drive.pose)
                .waitSeconds(.5)
                .build();
        closeWait = drive.actionBuilder(drive.pose)
                .waitSeconds(.5)
                .build();

        grabWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(.5)
                .build();
        closeWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(.5)
                .build();

        grabWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(.5)
                .build();
        closeWait2 = drive.actionBuilder(drive.pose)
                .waitSeconds(.5)
                .build();

        downWait = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();
        downWait1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.75)
                .build();

        wristPickUp1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.5)
                .build();

        wristPickUp2 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.5)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        // JOSHUANOTE: This is where you put the final set of actions.
                        //ActionBuilder.BlueRightOption1(drive::actionBuilder)
                        driveToDrop,
                        _ViperArmActions.MoveArmToHighBasket(),
                        armUpWait1,
                        _ViperArmActions.MoveViperToHighBasket(),
                        viperUpWait1,
                        _WristClawActions.WristDump(),
                        testingWait,
                        _WristClawActions.OpenClaw(),
                        clawOpenWait,
                        _WristClawActions.WristUp(),
                        wristUpWait1,
                        _ViperArmActions.MoveViperToHome(),
                        viperDownWait1,
                        _ViperArmActions.MoveArmToHome(),
                        downWait,
                        driveToSample1,
                        _WristClawActions.WristDown(),
                        grabWait,
                        _WristClawActions.CloseClaw(),
                        closeWait,
                        _WristClawActions.WristUp(),
                        wristPickUp1,
                        driveToDrop1,
                        _ViperArmActions.MoveArmToHighBasket(),
                        armUpWait2,
                        _ViperArmActions.MoveViperToHighBasket(),
                        viperUpWait2,
                        _WristClawActions.WristDump(),
                        testingWait1,
                        _WristClawActions.OpenClaw(),
                        clawOpenWait1,
                        _WristClawActions.WristUp(),
                        wristUpWait2,
                        _ViperArmActions.MoveViperToHome(),
                        viperDownWait2,
                        _ViperArmActions.MoveArmToHome(),
                        downWait1,
                        driveToSample2,
                        _WristClawActions.WristDown(),
                        grabWait1,
                        _WristClawActions.CloseClaw(),
                        closeWait1,
                        _WristClawActions.WristUp(),
                        wristPickUp2,
                        driveToDrop2,
                        _ViperArmActions.MoveArmToHighBasket(),
                        armUpWait3,
                        _ViperArmActions.MoveViperToHighBasket(),
                        viperUpWait3,
                        _WristClawActions.WristDump(),
                        testingWait2,
                        _WristClawActions.OpenClaw(),
                        clawOpenWait2,
                        _WristClawActions.WristUp(),
                        wristUpWait3,
                        _ViperArmActions.MoveViperToHome(),
                        viperDownWait3,
                        _ViperArmActions.MoveArmToHome(),
                        driveToSample3,
                        _WristClawActions.WristDown(),
                        grabWait2,
                        _WristClawActions.CloseClaw(),
                        closeWait2,
                        _WristClawActions.WristUp()
                        )
        );
        while(opModeIsActive()) {
            // _leftFront.setPower(0.3);
            telemetry.update();
        }
    }
}
