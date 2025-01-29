package BobcatLib.Hardware.Sensors.SpatialSensor.Utility;

import com.playingwithfusion.TimeOfFlight.RangingMode;

public class DistanceMode {
  public enum modes {
    SHORT,
    MEDIUM,
    LONG
  }

  public modes currentMode;

  public au.grapplerobotics.interfaces.LaserCanInterface.RangingMode asLaserCAN() {
    if (currentMode == modes.SHORT) {
      return au.grapplerobotics.interfaces.LaserCanInterface.RangingMode.SHORT;
    }
    if (currentMode == modes.MEDIUM) {
      return au.grapplerobotics.interfaces.LaserCanInterface.RangingMode.SHORT;
    }
    if (currentMode == modes.LONG) {
      return au.grapplerobotics.interfaces.LaserCanInterface.RangingMode.LONG;
    }
    return null;
  }

  public RangingMode asPWF() {
    if (currentMode == modes.SHORT) {
      return RangingMode.Short;
    }
    if (currentMode == modes.MEDIUM) {
      return RangingMode.Medium;
    }
    if (currentMode == modes.LONG) {
      return RangingMode.Long;
    }
    return null;
  }

  public DistanceMode fromPWF(RangingMode mode) {
    if (mode == RangingMode.Short) {
      DistanceMode nDistanceMode = new DistanceMode();
      nDistanceMode.currentMode = DistanceMode.modes.SHORT;
      return nDistanceMode;
    }
    if (mode == RangingMode.Medium) {
      DistanceMode nDistanceMode = new DistanceMode();
      nDistanceMode.currentMode = DistanceMode.modes.MEDIUM;
      return nDistanceMode;
    }
    if (mode == RangingMode.Long) {
      DistanceMode nDistanceMode = new DistanceMode();
      nDistanceMode.currentMode = DistanceMode.modes.LONG;
      return nDistanceMode;
    }
    return null;
  }

  public DistanceMode fromLaserCAN(
      au.grapplerobotics.interfaces.LaserCanInterface.RangingMode mode) {
    if (mode == au.grapplerobotics.interfaces.LaserCanInterface.RangingMode.SHORT) {
      DistanceMode nDistanceMode = new DistanceMode();
      nDistanceMode.currentMode = DistanceMode.modes.SHORT;
      return nDistanceMode;
    }
    if (mode == au.grapplerobotics.interfaces.LaserCanInterface.RangingMode.SHORT) {
      DistanceMode nDistanceMode = new DistanceMode();
      nDistanceMode.currentMode = DistanceMode.modes.SHORT;
      return nDistanceMode;
    }
    if (mode == au.grapplerobotics.interfaces.LaserCanInterface.RangingMode.LONG) {
      DistanceMode nDistanceMode = new DistanceMode();
      nDistanceMode.currentMode = DistanceMode.modes.LONG;
      return nDistanceMode;
    }
    return null;
  }
}
