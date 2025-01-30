// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;
import java.util.List;

import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

import BobcatLib.Hardware.Controllers.OI;
import BobcatLib.Hardware.Sensors.SpatialSensor.Spatial;
import BobcatLib.Hardware.Sensors.SpatialSensor.SpatialTOF;
import BobcatLib.Hardware.Sensors.SpatialSensor.Components.RangeSensor;
import BobcatLib.Hardware.Sensors.SpatialSensor.Components.SENS3006;
import BobcatLib.Hardware.Sensors.SpatialSensor.Utility.DistanceMode;
import BobcatLib.Hardware.Sensors.SpatialSensor.Utility.DistanceMode.modes;
import BobcatLib.Subsystems.Swerve.SimpleSwerve.Containers.SwerveBase;
import BobcatLib.Subsystems.Swerve.SimpleSwerve.Swerve.Module.Utility.PIDConstants;
import BobcatLib.Subsystems.Swerve.SimpleSwerve.Utility.Alliance;
import BobcatLib.Subsystems.Swerve.Utility.LoadablePathPlannerAuto;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer extends SwerveBase{
        /* Subsystems */
        private final LoggedDashboardChooser<Command> autoChooser = new LoggedDashboardChooser<>("Auto Routine"); // Choose an Auto!
        private final Spatial distanceSensing;

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer(OI driver_controller,
                        List<LoadablePathPlannerAuto> autos,
                        String robotName,
                        boolean isSim,
                        Alliance alliance,
                        PIDConstants tranPidPathPlanner,
                        PIDConstants rotPidPathPlanner) {

                super(driver_controller, autos, robotName, isSim, alliance, tranPidPathPlanner, rotPidPathPlanner);
                List<RangeSensor> distanceSensors = new ArrayList<RangeSensor>();
                distanceSensors.add(new SENS3006(0, new DistanceMode(modes.SHORT), 20));
                distanceSensors.add(new SENS3006(0, new DistanceMode(modes.SHORT), 20));
                distanceSensing = new Spatial( new SpatialTOF( distanceSensors ) );
        }

        public void periodic(){
                distanceSensing.isSquared();
        }

        /**
         * Use this method to define your button->command mappings. Buttons can be
         * created by
         * instantiating a {@link GenericHID} or one of its subclasses ({@link
         * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
         * it to a {@link
         * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
         */
        @Override
        public void configureButtonBindings() {
                super.configureButtonBindings();
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        @Override
        public Command getAutonomousCommand(String name) {
                // This method loads the auto when it is called, however, it is recommended
                // to first load your paths/autos when code starts, then return the
                // pre-loaded auto/path
                return super.getAutonomousCommand(name);
        }

        /**
         * Use this to pass the test command to the main {@link Robot} class.
         * Control pattern is forward, right , backwards, left, rotate in place
         * clockwise, rotate in place counterclowise, forward while rotating Clockwise,
         * forward while rotating counter clockwise
         *
         * @return the command to run in autonomous
         */
        @Override
        public Command getTestCommand() {
                return super.getTestCommand();
        }
}