package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsAnalogOpticalDistanceSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by neil on 9/12/17.
 */


@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "range", group = "Test")

public class ODS_Test extends LinearOpMode {
    ModernRoboticsAnalogOpticalDistanceSensor ods;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Inintializing", "");
        telemetry.update();

        initSystem();

        telemetry.addData("Initialized", "");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            teleop();
            idle();
        }
    }

    public void initSystem() throws InterruptedException {
        ods = hardwareMap.get(ModernRoboticsAnalogOpticalDistanceSensor.class, "ods");
    }

    public void teleop() throws InterruptedException {
        telemetry.addData("Distance = ", ods.getLightDetected());
        telemetry.addData("Distance = ", ods.getRawLightDetected());
        telemetry.update();
    }
}
