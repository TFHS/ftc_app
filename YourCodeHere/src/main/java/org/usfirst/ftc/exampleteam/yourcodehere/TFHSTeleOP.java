package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.hardware.*;
import org.swerverobotics.library.*;
import org.swerverobotics.library.interfaces.*;

/**
 * A Basic example of a first Tele OpMode. Go ahead and change this code
 * to suit your needs, or create a copy to modify.
 * Configuration matches the pushBot build: 2 tank drive motors, 1 arm motor, 2 servo hands
 * (my test bed only has one servo.  Be sure to uncomment out second servo
 */
@TeleOp(name="TFHSTeleOp") //name to appear in Driver Station OpMode selection
//@Disabled  //if you un-comment this, it will keep from showing on DriverStation

public class TFHSTeleOP extends SynchronousOpMode //Special note: this class name must match file name
{
    // Declare variable for all components to be used. Note initial values set to null. */

    //motors
    DcMotor motorL = null;
    DcMotor motorR = null;
    //DcMotor motorArm = null;

    //servos
    //Servo servoHandR = null;
    Servo servo1 = null;
    Servo servo2 = null;
    Servo servo3 = null;
    //Create and set default hand positions variables. To be determined based on your build
    double CLOSED = 0.2;
    double OPEN = 0.8;

    @Override public void main() throws InterruptedException {
        /* Initialize all hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names you assigned during the robot configuration
         * step you did in the FTC Robot Controller app on the phone.
         */
        this.motorL = this.hardwareMap.dcMotor.get("motorL");
        this.motorR = this.hardwareMap.dcMotor.get("motorR");
        //this.motorArm = this.hardwareMap.dcMotor.get("motorArm");

        //set motor channel to run without encoders
        //setChannelMode has been deprecated. Commented out. If you have this
        // EXAMPLE:  motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        //you will need to update to example below.
        //motorRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        // motorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        // motorArm.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        //reverse Left motor
        motorL.setDirection(DcMotor.Direction.REVERSE);

        //  this.servoHandR = this.hardwareMap.servo.get("servoHandR");
        this.servo1 = this.hardwareMap.servo.get("servo1");
        this.servo2 = this.hardwareMap.servo.get("servo2");
        this.servo3 = this.hardwareMap.servo.get("servo3");
        //Preset servoHandR position
        // servoHandR.setPosition(OPEN);
        servo1.setPosition(OPEN);
        servo2.setPosition(OPEN);
        servo3.setPosition(OPEN);

        // Wait for the game to start
        waitForStart();

        /************************
         * TeleOp Code Below://
         *************************/
        while (opModeIsActive()) {//loop to run while play is active. Until stop button is pressed.

            if (updateGamepads()){ //method to read gamepads

                // tank drive set to gamepad 1 joysticks
                motorL.setPower(gamepad1.left_stick_y);
                motorR.setPower(gamepad1.right_stick_y);

                // Arm Control - Uses dual buttons to control motor direction
                if(gamepad1.right_bumper)
                {
                    //motorArm.setPower(-gamepad1.right_trigger); // if both Bumper + Trigger, then negative power, runs arm down
                }
                else
                {
                    //motorArm.setPower(gamepad1.right_trigger);  // else trigger positive value, runs arm up
                }

                //servo commands
                if(gamepad1.a) //button 'a' will open
                {
                    // servoHandR.setPosition(OPEN);
                    servo1.setPosition(OPEN);
                }
                else if (gamepad1.b) //button 'b' will close
                {
                    // servoHandR.setPosition(CLOSED);
                    servo1.setPosition(CLOSED);
                }

            }//if updateGamepads

            telemetry.update(); //send telemetry to driver station
            idle();

        }//while opModeActive
    }//main
}//MyFirstOpMode