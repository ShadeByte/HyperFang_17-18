package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by neil on 9/30/17.
 */

@Autonomous (name = "AutoTest", group = "Auto")
public class Auto_Test extends LinearOpMode {
    DcMotor leftF, leftB, rightF, rightB;

    ColorSensor sensorColor;
    float hsvValues[] = {0F,0F,0F};

    final double SCALE_FACTOR = 255;

    int jewelState;

    Servo jewel;

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    int newLeftTarget = 0;
    int newRightTarget = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Inintializing", "");

        initSystem();

        telemetry.addData("Initialized", "");

        waitForStart();

        while (opModeIsActive()) {
            auto();
            idle();
        }
    }

    public void initSystem() throws InterruptedException {

        leftF = hardwareMap.get(DcMotor.class, "leftF");
        leftB = hardwareMap.get(DcMotor.class, "leftB");
        rightF = hardwareMap.get(DcMotor.class, "rightF");
        rightB = hardwareMap.get(DcMotor.class, "rightB");
        leftF.setDirection(DcMotorSimple.Direction.REVERSE);
        leftB.setDirection(DcMotorSimple.Direction.REVERSE);
        rightF.setDirection(DcMotorSimple.Direction.FORWARD);
        rightB.setDirection(DcMotorSimple.Direction.REVERSE);

        sensorColor = hardwareMap.get(ColorSensor.class, "color_distance");
        sensorColor.enableLed(true);

        jewel = hardwareMap.get(Servo.class, "servo");
        jewel.setPosition(0);
    }

    public void auto() throws InterruptedException {
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);

        telemetry.addData("Jewel",  "State = " + jewelState);
        telemetry.update();

        jewel.setPosition(0.5);

        Forward(5, 0.7);
        Thread.sleep(100);

        if (sensorColor.red() > sensorColor.blue() && hsvValues[0] < 50) {
            jewelState = 1;
        } else {
            jewelState = 0;
        }

        switch (jewelState) {
            case 0:
                StrafeRight(20, 0.7);
                Stop();
                jewel.setPosition(0);
            case 1:
                StrafeLeft(20, 0.7);
                jewel.setPosition(0);
        }

        sensorColor.enableLed(false);

        Stop();
        Thread.sleep(30000);
    }

    public void Forward(int distance, double power) throws InterruptedException {
        newLeftTarget = leftF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftF.setTargetPosition(newLeftTarget);
        leftB.setTargetPosition(newLeftTarget);
        rightF.setTargetPosition(newRightTarget);
        rightB.setTargetPosition(newRightTarget);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(power);
        leftB.setPower(power);
        rightF.setPower(power);
        rightB.setPower(power);

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

    public void Backward(int distance, double power) throws InterruptedException {
        newLeftTarget = leftF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftF.setTargetPosition(newLeftTarget);
        leftB.setTargetPosition(newLeftTarget);
        rightF.setTargetPosition(newRightTarget);
        rightB.setTargetPosition(newRightTarget);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(power);
        leftB.setPower(power);
        rightF.setPower(power);
        rightB.setPower(power);

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

    public void TurnLeft(int distance, double power) throws InterruptedException {
        newLeftTarget = leftF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftF.setTargetPosition(newLeftTarget);
        leftB.setTargetPosition(newLeftTarget);
        rightF.setTargetPosition(newRightTarget);
        rightB.setTargetPosition(newRightTarget);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(power);
        leftB.setPower(power);
        rightF.setPower(-power);
        rightB.setPower(-power);

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

    public void TurnRight(int distance, double power) throws InterruptedException {
        newLeftTarget = leftF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftF.setTargetPosition(newLeftTarget);
        leftB.setTargetPosition(newLeftTarget);
        rightF.setTargetPosition(newRightTarget);
        rightB.setTargetPosition(newRightTarget);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(-power);
        leftB.setPower(-power);
        rightF.setPower(power);
        rightB.setPower(power);

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

    public void Stop() throws InterruptedException {
        leftF.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        rightB.setPower(0);
    }

    public void StrafeLeft(int distance, double power) throws InterruptedException {
        newLeftTarget = leftF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftF.setTargetPosition(newLeftTarget);
        leftB.setTargetPosition(newLeftTarget);
        rightF.setTargetPosition(newRightTarget);
        rightB.setTargetPosition(newRightTarget);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(power);
        leftB.setPower(-power);
        rightF.setPower(-power);
        rightB.setPower(power);

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

    public void StrafeRight(int distance, double power) throws InterruptedException {
        newLeftTarget = leftF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftF.setTargetPosition(newLeftTarget);
        leftB.setTargetPosition(newLeftTarget);
        rightF.setTargetPosition(newRightTarget);
        rightB.setTargetPosition(newRightTarget);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftF.setPower(-power);
        leftB.setPower(power);
        rightF.setPower(power);
        rightB.setPower(-power);

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

    public void StrafeDL(boolean p, int distance, double power) throws InterruptedException {
        newLeftTarget = leftF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightB.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftF.setTargetPosition(newLeftTarget);
        rightB.setTargetPosition(newRightTarget);

        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (p) {
            leftF.setPower(power);
            rightB.setPower(power);
        } else {
            leftF.setPower(-power);
            rightB.setPower(-power);
        }

        leftF.setPower(0);
        rightB.setPower(0);

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void SrafeDR(boolean p, int distance, double power) throws InterruptedException {
        newLeftTarget = leftB.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        newRightTarget = rightF.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
        leftB.setTargetPosition(newLeftTarget);
        rightF.setTargetPosition(newRightTarget);

        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (p) {
            leftB.setPower(power);
            rightF.setPower(power);
        } else {
            leftB.setPower(-power);
            rightF.setPower(-power);
        }

        leftB.setPower(0);
        rightF.setPower(0);

        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
