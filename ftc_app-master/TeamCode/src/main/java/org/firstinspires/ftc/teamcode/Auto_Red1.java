package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

/**
 * Created by neil on 9/18/17.
 */

public class Auto_Red1 extends LinearOpMode {
    DcMotor leftF, leftB, rightF, rightB;

    ModernRoboticsI2cColorSensor jewelDetector;

    BNO055IMU imu;

    Orientation angles;

    private ElapsedTime runtime = new ElapsedTime();
    private Decoder vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        vuforia = new Decoder(hardwareMap, "AYx3Kw3/////AAAAGQreNEJhLkdWqUbBsQ06dnWIksoccLxh/R9WNkXB8hvuonWmFXUWJ2tYqM+8VqYCWXkHfanXzG/G1un7ZvwgGkkO6u0ktevZDb8AFWF2/Y4wVH1BWGQ2psV5QkHAKZ7Z6ThZI01HPZqixiQowyeUstv7W/QU8jJ48NrqGBLVYdE6eFfzNDzVY/1IvrBJaRwqKR8vo+3a2zmeFEnEhFTqMI7anU2WSPy8RP7tR61CdfidjL2biMe0RiSOBIbqOe4rs9NGaDvp1Crtz17uyY71GyMkp+Kmjbejyfj8LgZ/dZQoEsuVuQyo0dbd4KBxsEJlQj/uAEst22QoEwZe0Af4DnFtwn6/IEe02L3DT3/Np+ZX");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit            = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        waitForStart();
        runtime.reset();
    }

    public void auto() throws InterruptedException {
        vuforia.start();

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Status", "Target: " + vuforia.getDecodedColumn());
        telemetry.addData("Status", "Name: " + vuforia.getMark().name());
        telemetry.update();

        int column = vuforia.getDecodedColumn();

        if (jewelDetector.blue() < jewelDetector.red()) {

            switch (column) {
                case 1:
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

    void composeTelemetry() {
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
