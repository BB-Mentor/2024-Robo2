package org.bluebananas.ftc.roadrunneractions.TrajectoryActionBuilders;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;

import java.util.function.Function;

public class SampleTrajectories {
    //key poses
    public final static Pose2d topLeft = new Pose2d(-47.4,47.4,Math.toRadians(0)); //make start pose public
    final static Pose2d topRight = new Pose2d(47.4,47.4,Math.toRadians(-90));
    final static Pose2d bottomRight = new Pose2d(47.4,-47.4,Math.toRadians(180));
    final static Pose2d bottomLeft = new Pose2d(-47.4,-47.4,Math.toRadians(90));

    //------------------------------------------------------------------
    //Full path
    public static Action Full(Function<Pose2d, TrajectoryActionBuilder> builderFunction) {
        return new SequentialAction(
                topLeft_To_topRight(builderFunction),
                topRight_To_bottomRight(builderFunction),
                bottomRight_To_bottomLeft(builderFunction),
                bottomLeft_To_topLeft(builderFunction)
        );
    }

    //topLeft to topRight
    public static Action topLeft_To_topRight(Function<Pose2d, TrajectoryActionBuilder> builderFunction) {
        return builderFunction.apply(/*Starting Pose */ topLeft)
                .strafeToLinearHeading(topRight.position, topRight.heading)
                .build();
    }

    //topRight to bottomRight
    public static Action topRight_To_bottomRight(Function<Pose2d, TrajectoryActionBuilder> builderFunction) {
        return builderFunction.apply(/*Starting Pose */ topRight)
                .strafeToLinearHeading(bottomRight.position, bottomRight.heading)
                .build();
    }

    //bottomRight to bottomLeft
    public static Action bottomRight_To_bottomLeft(Function<Pose2d, TrajectoryActionBuilder> builderFunction) {
        return builderFunction.apply(/*Starting Pose */ bottomRight)
                .strafeToLinearHeading(bottomLeft.position, bottomLeft.heading)
                .build();
    }

    //bottomLeft to topLeft
    public static Action bottomLeft_To_topLeft(Function<Pose2d, TrajectoryActionBuilder> builderFunction) {
        return builderFunction.apply(/*Starting Pose */ bottomLeft)
                .strafeToLinearHeading(topLeft.position, topLeft.heading)
                .build();
    }


}
