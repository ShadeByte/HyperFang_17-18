package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by neil on 9/12/17.
 */


@TeleOp(name="TrollBot", group="Test")
public class TrollBotTeleOp extends LinearOpMode {
    DcMotor leftF, leftB, rightF, rightB;

    int driveCase = 0;

    ColorSensor sensorColor;
    float hsvValues[] = {0F,0F,0F};

    final double SCALE_FACTOR = 255;

    int jewelState;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Inintializing", "");

        initSystem();

        telemetry.addData("Initialized", "");

        waitForStart();

        while (opModeIsActive()) {
            teleop();
            idle();
        }
    }

    public void initSystem() throws InterruptedException {
        leftF = hardwareMap.get(DcMotor.class, "leftF");
        leftB = hardwareMap.get(DcMotor.class, "leftB");
        rightF = hardwareMap.get(DcMotor.class, "rightF");
        rightB = hardwareMap.get(DcMotor.class, "rightB");
        rightF.setDirection(DcMotorSimple.Direction.REVERSE);

        sensorColor = hardwareMap.get(ColorSensor.class, "color_distance");
        sensorColor.enableLed(true);
    }

    public void teleop() throws InterruptedException {

        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);

        telemetry.addData("Drive",  "Case = " + driveCase);
        telemetry.addData("Jewel ",  "State = " + jewelState);
        telemetry.update();

        double lFPower = gamepad1.left_stick_y;
        double lBPower = gamepad1.left_stick_y;
        double rFPower = gamepad1.right_stick_y;
        double rBPower = gamepad1.right_stick_y;

        double strafe = gamepad1.left_stick_x;

        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) - rightX;
        final double v2 = r * Math.sin(robotAngle) + rightX;
        final double v3 = r * Math.sin(robotAngle) - rightX;
        final double v4 = r * Math.cos(robotAngle) + rightX;

        lFPower = Range.clip(lFPower , -1, 1);
        lBPower = Range.clip(lBPower , -1, 1);
        rFPower = Range.clip(rFPower, -1, 1);
        rBPower = Range.clip(rBPower, -1, 1);

        strafe = Range.clip(strafe, -1, 1);

        if (sensorColor.red() > sensorColor.blue() && hsvValues[0] < 50) {
            jewelState = 1;
        } else {
            jewelState = 0;
        }

        if (gamepad1.left_bumper) {
            driveCase = 1;
        } else if (gamepad1.right_bumper){
            driveCase = 0;
        }

        if (driveCase == 0) {
            if (Math.abs(strafe) > 0.25){
                leftF.setPower(-strafe);
                leftB.setPower(strafe);
                rightF.setPower(strafe);
                rightB.setPower(-strafe);
            } else {
                leftF.setPower(lFPower);
                leftB.setPower(lBPower);
                rightF.setPower(rFPower);
                rightB.setPower(rBPower);
            }
        } else {
            leftF.setPower(v1);
            rightF.setPower(v2);
            leftB.setPower(v3);
            rightB.setPower(v4);
        }
    }
}
