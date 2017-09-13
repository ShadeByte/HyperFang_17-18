package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by neil on 9/12/17.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "TeleOp", group = "Test")

public class TeleOp extends LinearOpMode {
    DcMotor leftF, leftB, rightF, rightB;

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
        // init stuff
    }

    public void teleop() throws InterruptedException {
        double lFPower = gamepad1.left_stick_y;
        double lBPower = gamepad1.left_stick_y;
        double rFPower = gamepad1.right_stick_y;
        double rBPower = gamepad1.right_stick_y;

        double strafe = gamepad1.left_stick_x;

        lFPower = Range.clip(lFPower , -1, 1);
        lBPower = Range.clip(lBPower , -1, 1);
        rFPower = Range.clip(rFPower, -1, 1);
        rBPower = Range.clip(rBPower, -1, 1);

        strafe = Range.clip(strafe, -1, 1);

        if (Math.abs(strafe) > 0.35){
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


    }
}
