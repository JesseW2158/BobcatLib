// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import BobcatLib.Hardware.Motors.BaseMotor;
import BobcatLib.Hardware.Motors.KrakenMotor;
import BobcatLib.Hardware.Motors.MotorConfigs;
import BobcatLib.Hardware.Motors.SensorHelpers.NeutralModeWrapper.disabledMode;
import BobcatLib.Hardware.Motors.Utility.SoftwareLimitWrapper;
import BobcatLib.Hardware.Motors.Utility.SoftwareLimitWrapper.SoftwareLimitType;
import BobcatLib.Utilities.CANDeviceDetails;
import BobcatLib.Utilities.CANDeviceDetails.Manufacturer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class KrakenBasedSusbsystem extends SubsystemBase {
  private BaseMotor mMotor;

  /** Creates a new NovaBasedSubsystem. */
  public KrakenBasedSusbsystem() {
    CANDeviceDetails canDevice = new CANDeviceDetails(1, Manufacturer.Thrifty);
    MotorConfigs mc = new MotorConfigs();
    mc.motorToGearRatio = 1;
    mc.canDevice = canDevice;
    mc.isInverted = false;
    mc.kP = 0.05;
    mc.kI = 0.00;
    mc.kD = 0.00;
    mc.mode = disabledMode.Brake;
    // Set any Software Limits
    SoftwareLimitWrapper limits = new SoftwareLimitWrapper(0, 20, SoftwareLimitType.BOTH);
    mMotor = new BaseMotor(new KrakenMotor(canDevice, "rio", mc), limits);
  }

  @Override
  public void periodic() {
  }

  /**
   * This is a method that makes the arm move at your desired speed
   * Positive values make it spin forward and negative values spin it in reverse
   * 
   * @param speed motor speed from -1.0 to 1, with 0 stopping it
   */
  public void runMotor(double speed) {
    mMotor.setSpeed(speed, 1, false);
  }

  /**
   * This is a method that makes the subsystem motor go to a set position.
   * 
   * @param position motor position in rotations
   */
  public void runMotorToPosition(double position) {
    mMotor.setAngle(position);
  }



  /**
   * Implements a command composition style command to the subsystem that enables us to 
   * move the motor to a set position. This will take the input you provide in rotations and set that as the desired rotation output.
   * @return command 
   */
  public Command rotateMotorCW(){
    Command moveMotorCW = new InstantCommand(() -> runMotor(0.5));
    return moveMotorCW;
  }  

  /**
   * Implements a command composition style command to the subsystem that enables us to 
   * move the motor to a set position. This will do it at half a rotation output.
   * @return command 
   */
  public Command rotateMotorCCW(){
    Command moveHalfRotation = new InstantCommand(() -> runMotorToPosition(0.5));
    return moveHalfRotation;
  }  


  /**
   * Implements a command composition style command to the subsystem that enables us to 
   * move the motor in a counter-clockwise direction to operate the subsystem. This will do it at 50% output.
   * nothing fancy here and this is not PID based.
   * @param angle in rotations you want the motor to turn too.
   * @return command
   */
  public Command rotateSetPosition(double rotations){
    Command moveMotorCW = new InstantCommand(() -> runMotor(rotations));
    return moveMotorCW;
  }  
  /**
   * Implements a command composition style command to the subsystem that enables us to 
   * move the motor in a counter-clockwise direction to operate the subsystem. This will do it at 180 degrees or 0.5 output.
   * nothing fancy here and this is not PID based. 
   * @return command
   */
  public Command rotateSetPosition(){
    Command moveMotorCW = new InstantCommand(() -> runMotor(0.5));
    return moveMotorCW;
  }  


   /**
   * Implements a command composition style command to the subsystem that enables us to 
   * move the to enable the position to be held to operate the subsystem. 
   * @return
   */
  public Command holdPosition(){
    Rotation2d motorPosition = mMotor.getPosition();
    Command moveMotorCW = new InstantCommand(() -> runMotorToPosition(motorPosition.getRotations()));
    return moveMotorCW;
  }

}
