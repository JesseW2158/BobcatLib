package BobcatLib.Utilities;

public class CANDeviceDetails {
  public enum Manufacturer {
    Unknown,
    Thrifty,
    Grapple,
    Pwf,
    Redux,
    Rev,
    Ctre
  }

  private final Manufacturer manufacturer;
  private final int deviceNumber;
  private final String bus;
  private final String subsystemName;

  /**
   * CAN Device Id constructor given the device number, bus name , and manufacturer type.
   *
   * @param deviceNumber
   * @param bus
   * @param manufacturer
   */
  public CANDeviceDetails(
      int deviceNumber, String bus, Manufacturer manufacturer, String subsystemName) {
    this.deviceNumber = deviceNumber;
    this.bus = bus;
    this.manufacturer = manufacturer;
    this.subsystemName = subsystemName;
  }

  /**
   * CAN Device Id constructor given the device number, bus name , and manufacturer type.
   *
   * @param deviceNumber
   * @param bus
   * @param manufacturer
   */
  public CANDeviceDetails(int deviceNumber, String bus, Manufacturer manufacturer) {
    this.deviceNumber = deviceNumber;
    this.bus = bus;
    this.manufacturer = manufacturer;
    this.subsystemName = "";
  }

  /**
   * CAN Device Id constructor given only the device number Uses the default bus name of "" (empty
   * string)
   *
   * @param deviceNumber
   * @param manufacturer
   */
  public CANDeviceDetails(int deviceNumber, Manufacturer manufacturer) {
    this(deviceNumber, "", manufacturer);
  }

  /**
   * CAN Device Id constructor given only the device number Uses the default bus name of "" (empty
   * string)
   *
   * @param deviceNumber
   * @param manufacturer
   * @param subsystemName
   */
  public CANDeviceDetails(int deviceNumber, Manufacturer manufacturer, String subsystemName) {
    this(deviceNumber, "", manufacturer, subsystemName);
  }

  /**
   * CAN Device Id constructor given only the device number Uses the default bus name of "" (empty
   * string) and Unknown manufacturer
   *
   * @param deviceNumber
   */
  public CANDeviceDetails(int deviceNumber) {
    this(deviceNumber, "", Manufacturer.Unknown);
  }

  /**
   * Gets the manufacturer type
   *
   * @return Manufacturer type.
   */
  public Manufacturer getManufacturer() {
    return manufacturer;
  }

  /**
   * Gets the device number
   *
   * @return device number
   */
  public int getDeviceNumber() {
    return deviceNumber;
  }

  /**
   * Gets the bus name
   *
   * @return bus name
   */
  public String getBus() {
    return bus;
  }

  /**
   * Gets the Subsystem this is associated with.
   *
   * @return Subsystem this is associated with.
   */
  public String getSubsysemName() {
    return subsystemName;
  }

  public boolean equals(CANDeviceDetails other) {
    return other.deviceNumber == deviceNumber
        && other.bus == bus
        && other.manufacturer == manufacturer
        && other.subsystemName == subsystemName;
  }
}
