package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepMain {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(500);

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(BlueRightOption1(meepMeep))
                .addEntity(BlueLeft(meepMeep))
                .addEntity(RedRightOption1(meepMeep))
                //.addEntity(RedRightOption2(meepMeep))
                .addEntity(RedLeft(meepMeep))
                .start();
    }

    //begin auto runs

    //generates path for BlueRightOption1
    private static RoadRunnerBotEntity BlueRightOption1(MeepMeep meepMeep)
    {
        RoadRunnerBotEntity botEntity = CreateBotEntity(meepMeep, "blue");
        botEntity.runAction(botEntity.getDrive().actionBuilder(new Pose2d(-15, 63, Math.toRadians(-90)))
                .splineTo(new Vector2d(-9,42), Math.toRadians(-90))
                .waitSeconds(1)//hook preloaded specimen
                .strafeTo(new Vector2d(-12,42))
                .splineToSplineHeading(new Pose2d(-36,24, Math.toRadians(0)), Math.toRadians(-90))
                .splineTo(new Vector2d(-42,12), Math.toRadians(180))
                .strafeTo(new Vector2d(-45,12))
                .strafeTo(new Vector2d(-45,48))
                .strafeTo(new Vector2d(-45,24))
                .splineTo(new Vector2d(-57,12), Math.toRadians(180))
                .strafeTo(new Vector2d(-57,48))
                .strafeTo(new Vector2d(-57,42))
                .waitSeconds(1)//wait for human player
                .strafeTo(new Vector2d(-63,63))
                .build());

        return botEntity;
    }

    //generates path for BlueLeft
    private static RoadRunnerBotEntity BlueLeft(MeepMeep meepMeep)
    {
        RoadRunnerBotEntity botEntity = CreateBotEntity(meepMeep, "blue");
        botEntity.runAction(botEntity.getDrive().actionBuilder(new Pose2d(33, 63, Math.toRadians(-90)))
                .splineTo(new Vector2d(48,48), Math.toRadians(45))
                .waitSeconds(1)//deposit sample
                .turnTo(Math.toRadians(-90))
                .waitSeconds(1)//pick up sample
                .turnTo(Math.toRadians(45))
                .waitSeconds(1)//deposit sample
                .strafeToLinearHeading(new Vector2d(58,48), Math.toRadians(-90))
                .waitSeconds(1)//pick up sample
                .strafeToLinearHeading(new Vector2d(48,48), Math.toRadians(45))
                .waitSeconds(10)//deposit sample and wait for other alliance to park
                .splineTo(new Vector2d(24,60), Math.toRadians(180))
                .strafeTo(new Vector2d(-36,60))
                .build());

        return botEntity;
    }

    //generates path for RedRightOption1
    private static RoadRunnerBotEntity RedRightOption1(MeepMeep meepMeep)
    {
        RoadRunnerBotEntity botEntity = CreateBotEntity(meepMeep, "red");
        botEntity.runAction(botEntity.getDrive().actionBuilder(new Pose2d(15, -63, Math.toRadians(90)))
                .splineTo(new Vector2d(9,-42), Math.toRadians(90))
                .waitSeconds(1)//hook preloaded specimen
                .strafeTo(new Vector2d(12,-42))
                .splineToSplineHeading(new Pose2d(36,-24, Math.toRadians(180)), Math.toRadians(90))
                .splineTo(new Vector2d(42,-12), Math.toRadians(0))
                .strafeTo(new Vector2d(45,-12))
                .strafeTo(new Vector2d(45,-48))
                .strafeTo(new Vector2d(45,-24))
                .splineTo(new Vector2d(57,-12), Math.toRadians(0))
                .strafeTo(new Vector2d(57,-48))
                .strafeTo(new Vector2d(57,-42))
                .waitSeconds(1)//wait for human player
                .strafeTo(new Vector2d(63,-63))
                .build());

        return botEntity;
    }

    //generates path for RedRightOption2
    private static RoadRunnerBotEntity RedRightOption2(MeepMeep meepMeep)
    {
        RoadRunnerBotEntity botEntity = CreateBotEntity(meepMeep, "red");
        botEntity.runAction(botEntity.getDrive().actionBuilder(new Pose2d(10, -63, Math.toRadians(90.0)))
                .strafeTo(new Vector2d(10.0, -45.0))
                .waitSeconds(1)
                .strafeTo(new Vector2d(25, -45))
                .splineTo(new Vector2d(45, -10), Math.toRadians(0))
//                .strafeTo(new Vector2d(28, -10))
//                .strafeTo(new Vector2d(46, -10))
                .strafeTo(new Vector2d(45, -55))
                .strafeToLinearHeading(new Vector2d(45, -30), Math.toRadians(270.0))
                .waitSeconds(1)
                .strafeTo(new Vector2d(45, -45))
                .waitSeconds(1)
                .splineTo(new Vector2d(10, -45), Math.toRadians(90))
//                .splineTo(new Vector2d(46, -30), Math.toRadians(180))
                .build());

        return botEntity;
    }

    //generates path for RedLeft
    private static RoadRunnerBotEntity RedLeft(MeepMeep meepMeep)
    {
        RoadRunnerBotEntity botEntity = CreateBotEntity(meepMeep, "red");
        botEntity.runAction(botEntity.getDrive().actionBuilder(new Pose2d(-33, -63, Math.toRadians(90)))
                .splineTo(new Vector2d(-48,-48), Math.toRadians(-135))
                .waitSeconds(1)//deposit sample
                .turnTo(Math.toRadians(90))
                .waitSeconds(1)//pick up sample
                .turnTo(Math.toRadians(-135))
                .waitSeconds(1)//deposit sample
                .strafeToLinearHeading(new Vector2d(-58,-48), Math.toRadians(90))
                .waitSeconds(1)//pick up sample
                .strafeToLinearHeading(new Vector2d(-48,-48), Math.toRadians(-135))
                .waitSeconds(10)//deposit sample and wait for other alliance to park
                .splineTo(new Vector2d(-24,-60), Math.toRadians(0))
                .strafeTo(new Vector2d(36,-60))
                .build());

        return botEntity;
    }

    //end auto runs


    //generates botEntities
    private static RoadRunnerBotEntity CreateBotEntity(MeepMeep meepMeep, String color)
    {
        ColorScheme c;
        if (color.equals("red"))
        {
            c = new ColorSchemeRedLight();
        }
        else
        {
            c = new ColorSchemeBlueLight();
        }
        return new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(c)
                .build();

    }


}