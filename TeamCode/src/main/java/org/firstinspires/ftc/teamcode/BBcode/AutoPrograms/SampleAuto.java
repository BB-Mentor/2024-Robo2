package org.firstinspires.ftc.teamcode.BBcode.AutoPrograms;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.bluebananas.ftc.roadrunneractions.TrajectoryActionBuilders.SampleTrajectories;
import org.firstinspires.ftc.teamcode.BBcode.MechanismControllers.IntakeClaw;
import org.firstinspires.ftc.teamcode.BBcode.UtilClasses.UtilActions;
import org.firstinspires.ftc.teamcode.PinpointDrive;

@Config
@Autonomous(name = "Blue_Basket_Auto", group = "Autonomous")
public class SampleAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
    //Initialization steps
        //Creates instance of MechanismControllers;
        IntakeClaw _IntakeClaw = new IntakeClaw(this);

        //Initializes Pinpoint
        Pose2d initialPose = SampleTrajectories.topLeft;
        PinpointDrive _PinpointDrive = new PinpointDrive(hardwareMap, initialPose);

        telemetry.update();
        waitForStart();
        //----------------------------------------------------------------------------------------------
        if (isStopRequested()) return;

        //run auto steps
        Actions.runBlocking(new SequentialAction(
                SampleTrajectories.topLeft_To_topRight(_PinpointDrive::actionBuilder),
                _IntakeClaw.Open_RR(),
                UtilActions.Wait(2),
                SampleTrajectories.topRight_To_bottomRight(_PinpointDrive::actionBuilder),
                _IntakeClaw.Close_RR(),
                UtilActions.Wait(2),
                SampleTrajectories.bottomRight_To_bottomLeft(_PinpointDrive::actionBuilder),
                _IntakeClaw.Open_RR(),
                UtilActions.Wait(2),
                SampleTrajectories.bottomLeft_To_topLeft(_PinpointDrive::actionBuilder),
                _IntakeClaw.Close_RR()
        ));
    }
}
