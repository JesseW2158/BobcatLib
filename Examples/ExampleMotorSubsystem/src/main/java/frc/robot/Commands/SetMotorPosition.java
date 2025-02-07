// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Subsystems.FalconBasedSubsystem;
import frc.robot.Subsystems.KrakenBasedSusbsystem;
import frc.robot.Subsystems.NovaBasedSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SetMotorPosition extends Command  {
  private final Subsystem subsystem;
  public double operationalPosition = 0.5;
  /** Creates a new RotateMotorCW. */
  public SetMotorPosition(Subsystem sub) {
    this.subsystem = sub;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if( subsystem instanceof NovaBasedSubsystem){
      ((NovaBasedSubsystem)subsystem).rotateSetPosition(operationalPosition);
    }
    else  if( subsystem instanceof KrakenBasedSusbsystem){

      ((KrakenBasedSusbsystem)subsystem).rotateSetPosition(operationalPosition);
    }
    else  if( subsystem instanceof FalconBasedSubsystem){

      ((FalconBasedSubsystem)subsystem).rotateSetPosition(operationalPosition);
    }
    else{
        // Edge case I got nothing here.....
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if( subsystem instanceof NovaBasedSubsystem){
      ((NovaBasedSubsystem)subsystem).rotateSetPosition();
    }
    else  if( subsystem instanceof KrakenBasedSusbsystem){

      ((KrakenBasedSusbsystem)subsystem).rotateSetPosition();
    }
    else  if( subsystem instanceof FalconBasedSubsystem){

      ((FalconBasedSubsystem)subsystem).rotateSetPosition();
    }
    else{
        // Edge case I got nothing here.....
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
