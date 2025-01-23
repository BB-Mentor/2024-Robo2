package org.firstinspires.ftc.teamcode.BBcode.AutoPrograms;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.bluebananas.ftc.roadrunneractions.TrajectoryActionBuilders.AutoRefactorTest_Trajectories;
import org.firstinspires.ftc.teamcode.PinpointDrive;

@Config
@Autonomous(name = "Blue_Basket_Auto", group = "Autonomous")
public class AutoRefactorTest extends LinearOpMode {
    @Override
    public void runOpMode() {
    //Initialization steps
        //Creates instance of MechanismActionBuilders;

        //Initializes Pinpoint
        Pose2d initialPose = AutoRefactorTest_Trajectories.topLeft;
        PinpointDrive pinpointDrive = new PinpointDrive(hardwareMap, initialPose);

        telemetry.update();
        waitForStart();
        //----------------------------------------------------------------------------------------------
        if (isStopRequested()) return;

        //run auto steps
        Actions.runBlocking(new SequentialAction(
                AutoRefactorTest_Trajectories.topLeft_To_topRight(pinpointDrive::actionBuilder),
                AutoRefactorTest_Trajectories.topRight_To_bottomRight(pinpointDrive::actionBuilder),
                AutoRefactorTest_Trajectories.bottomRight_To_bottomLeft(pinpointDrive::actionBuilder),
                AutoRefactorTest_Trajectories.bottomLeft_To_topLeft(pinpointDrive::actionBuilder)
        ));
    }
}