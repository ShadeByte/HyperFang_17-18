package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by peter on 9/19/2017.
 */

public class BasicMovement{

    private DcMotor leftF, leftB, rightF, rightB;

    public BasicMovement() {
        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);
    }

    public void Forward(int encoder) {
        leftF.setTargetPosition(encoder);
        rightF.setTargetPosition(encoder);
        leftB.setTargetPosition(encoder);
        rightB.setTargetPosition(encoder);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(1);
        leftB.setPower(1);
        rightF.setPower(1);
        rightB.setPower(1);

        while (leftF.isBusy()) {}

        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void Backward(int encoder) {
        leftF.setTargetPosition(encoder);
        rightF.setTargetPosition(encoder);
        leftB.setTargetPosition(encoder);
        rightB.setTargetPosition(encoder);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(-1);
        leftB.setPower(-1);
        rightF.setPower(-1);
        rightB.setPower(-1);

        while (leftF.isBusy()) {}

        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void Stop() {
        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);
    }
}
