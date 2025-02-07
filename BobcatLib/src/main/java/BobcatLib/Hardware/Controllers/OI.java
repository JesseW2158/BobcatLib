package BobcatLib.Hardware.Controllers;

import BobcatLib.Hardware.Controllers.parser.ControllerJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.io.File;
import java.io.IOException;

/**
 * The Operator Interface (OI) class manages the controller inputs and button mappings for the
 * robot's driver. It supports various controller types and their configurations.
 */
public class OI {
  /** The driver joystick. */
  public Joystick single;

  /** The driver joystick. */
  public Joystick split_two;

  /** Driver Buttons */
  public Trigger fieldCentric;

  /** Driver Zero Gyros */
  public Trigger zeroGyro;

  /* controller configuration */
  public ControllerJson controllerJson;

  /** The wrapper for the driver controller, supporting different controller types. */
  public ControllerWrapper single_controller;

  public ControllerWrapper split_two_controller;

  private String robotName;
  /**
   * Constructs the Operator Interface (OI) with the specified driver port and configuration.
   * Initializes controller inputs and button mappings.
   */
  public OI(String robotName) {
    this.robotName = robotName;
    loadConfigurationFromFile();
    int driverPort = controllerJson.single.id;
    String type = controllerJson.single.type;
    /* USB Xbox Controllers */
    single = new Joystick(driverPort);
    split_two = single;
    /* Driver Buttons */
    single_controller = init(type, driverPort);
    split_two_controller = single_controller;
    if (controllerJson.isDual) {
      driverPort = controllerJson.split_one.id;
      type = controllerJson.split_one.type;
      /* USB Xbox Controllers */
      split_two = new Joystick(driverPort);
      /* Driver Buttons */
      single_controller = init(type, driverPort);
    }
  }

  public ControllerWrapper init(String type, int driverPort) {
    ControllerWrapper tmp;
    switch (type) {
      case "xbox":
        tmp = new XboxControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      case "ps4":
        tmp = new PS4ControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      case "ps5":
        tmp = new PS5ControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      case "ruffy":
        tmp = new Ruffy(driverPort);
        fieldCentric = tmp.getTopButton();
        zeroGyro = tmp.getTopButton();
        break;
      case "logitech":
        tmp = new Logitech(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      case "eightbitdo":
        tmp = new EightBitDo(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
      default:
        tmp = new XboxControllerWrapper(driverPort);
        fieldCentric = tmp.getAorCross();
        zeroGyro = tmp.getBorCircle();
        break;
    }
    return tmp;
  }

  /**
   * Loads Configuration From Deployed File
   *
   * @return ControllerJson
   */
  public ControllerJson loadConfigurationFromFile() {
    String name = "oi.json";
    File deployDirectory = Filesystem.getDeployDirectory();
    assert deployDirectory.exists();
    File directory = new File(deployDirectory, "configs/swerve/" + robotName + "/");
    assert new File(directory, name).exists();
    File moduleFile = new File(directory, name);
    assert moduleFile.exists();
    controllerJson = new ControllerJson();
    try {
      controllerJson = new ObjectMapper().readValue(moduleFile, ControllerJson.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return controllerJson;
  }

  /**
   * Gets Driver LeftY Axis
   *
   * @return double
   */
  public double getLeftYValue() {
    return single_controller.getLeftYAxis();
  }

  /**
   * Gets Driver LeftX Axis
   *
   * @return double
   */
  public double getLeftXValue() {
    return single_controller.getLeftXAxis();
  }

  /**
   * Gets Driver RightX Axis
   *
   * @return double
   */
  public double getRightXValue() {
    if (controllerJson.isDual) {
      return split_two_controller.getRightXAxis();
    }
    return single_controller.getRightXAxis();
  }
  /**
   * Gets Driver RightY Axis
   *
   * @return double
   */
  public double getRightYValue() {
    if (controllerJson.isDual) {
      return split_two_controller.getRightYAxis();
    }
    return single_controller.getRightYAxis();
  }
}
