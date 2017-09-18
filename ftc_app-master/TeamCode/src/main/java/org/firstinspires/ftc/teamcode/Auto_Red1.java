package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by neil on 9/18/17.
 */

public class Auto_Red1 extends LinearOpMode {
    DcMotor leftF, leftB, rightF, rightB;

    ModernRoboticsI2cColorSensor jewelDetector;

    Strafe sDrive = new Strafe();

    private ElapsedTime runtime = new ElapsedTime();
    private Decoder vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        vuforia = new Decoder(hardwareMap, "AYx3Kw3/////AAAAGQreNEJhLkdWqUbBsQ06dnWIksoccLxh/R9WNkXB8hvuonWmFXUWJ2tYqM+8VqYCWXkHfanXzG/G1un7ZvwgGkkO6u0ktevZDb8AFWF2/Y4wVH1BWGQ2psV5QkHAKZ7Z6ThZI01HPZqixiQowyeUstv7W/QU8jJ48NrqGBLVYdE6eFfzNDzVY/1IvrBJaRwqKR8vo+3a2zmeFEnEhFTqMI7anU2WSPy8RP7tR61CdfidjL2biMe0RiSOBIbqOe4rs9NGaDvp1Crtz17uyY71GyMkp+Kmjbejyfj8LgZ/dZQoEsuVuQyo0dbd4KBxsEJlQj/uAEst22QoEwZe0Af4DnFtwn6/IEe02L3DT3/Np+ZX");

        waitForStart();
        vuforia.start();
        runtime.reset();
    }

    public void auto() throws InterruptedException {
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Status", "Target: " + vuforia.getDecodedColumn());
        telemetry.addData("Status", "Name: " + vuforia.getMark().name());
        telemetry.update();

        int column = vuforia.getDecodedColumn();

        if (jewelDetector.blue() < jewelDetector.red()) {
            sDrive.StrafeLeft(1000);

            switch (column) {
                case 1:
                    sDrive.StrafeLeft(2000);
                    //driveFd(500);
                    //release();
                    //driveBd(500);
                    //rotate180();
                    sDrive.SrafeDR(false, 100);
            }
        }
    }

    public void modeResetEncoder() throws InterruptedException {
        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setTargetPos(int l, int r) throws InterruptedException {
        leftF.setTargetPosition(l);
        rightF.setTargetPosition(r);
        leftB.setTargetPosition(l);
        rightB.setTargetPosition(r);
    }

    public void modeRunToPosition() throws InterruptedException {
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void modeRunUsingEncoder() throws InterruptedException {
        leftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void modeRunWithoutEncoder() throws InterruptedException {
        leftF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
