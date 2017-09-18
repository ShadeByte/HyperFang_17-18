package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by neil on 9/18/17.
 */

public class Strafe {
    private DcMotor leftF, leftB, rightF, rightB;

    public Strafe() {
        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);
    }

    public void StrafeLeft(int encoder) {
        leftF.setTargetPosition(encoder);
        rightF.setTargetPosition(encoder);
        leftB.setTargetPosition(encoder);
        rightB.setTargetPosition(encoder);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(1);
        leftB.setPower(-1);
        rightF.setPower(-1);
        rightB.setPower(1);

        while (leftF.isBusy()) {

        }

        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void StrafeRight(int encoder) {
        leftF.setTargetPosition(encoder);
        rightF.setTargetPosition(encoder);
        leftB.setTargetPosition(encoder);
        rightB.setTargetPosition(encoder);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(-1);
        leftB.setPower(1);
        rightF.setPower(1);
        rightB.setPower(-1);

        while (leftF.isBusy()) {

        }

        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void StrafeDL(boolean p, int encoder) {
        leftF.setTargetPosition(encoder);
        rightB.setTargetPosition(encoder);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (p) {
            leftF.setPower(1);
            rightB.setPower(1);
        } else {
            leftF.setPower(-1);
            rightB.setPower(-1);
        }

        leftF.setPower(0);
        rightB.setPower(0);

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void SrafeDR(boolean p, int encoder) {
        leftB.setTargetPosition(encoder);
        rightF.setTargetPosition(encoder);

        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (p) {
            leftB.setPower(1);
            rightF.setPower(1);
        } else {
            leftB.setPower(-1);
            rightF.setPower(-1);
        }

        leftB.setPower(0);
        rightF.setPower(0);

        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
