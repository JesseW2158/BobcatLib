package BobcatLib.Hardware.Gyros;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class GyroIOInputsAutoLogged extends GyroIO.GyroIOInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("Connected", connected);
    table.put("YawPosition", yawPosition);
    table.put("PitchPosition", pitchPosition);
    table.put("RollPosition", rollPosition);
    table.put("Faulted", faulted);
  }

  @Override
  public void fromLog(LogTable table) {
    connected = table.get("Connected", connected);
    yawPosition = table.get("YawPosition", yawPosition);
    pitchPosition = table.get("PitchPosition", pitchPosition);
    rollPosition = table.get("RollPosition", rollPosition);
    faulted = table.get("Faulted", faulted);
  }

  public GyroIOInputsAutoLogged clone() {
    GyroIOInputsAutoLogged copy = new GyroIOInputsAutoLogged();
    copy.connected = this.connected;
    copy.yawPosition = this.yawPosition;
    copy.pitchPosition = this.pitchPosition;
    copy.rollPosition = this.rollPosition;
    copy.faulted = this.faulted;
    return copy;
  }
}
