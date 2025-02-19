package BobcatLib.Subsystems.Vision.Limelight.Structures;

import BobcatLib.Subsystems.Vision.Limelight.LimelightCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

/** Pipeline data for {@link LimelightCamera}. */
public class LimelightPipelineData {

  /** {@link NetworkTable} for the {@link LimelightCamera} */
  private NetworkTable limelightTable;
  /** {@link LimelightCamera} to fetch data for. */
  private LimelightCamera limelight;
  /** Pipeline processing latency contribution. */
  private NetworkTableEntry processingLatency;
  /** Pipeline capture latency. */
  private NetworkTableEntry captureLatency;
  /** Current pipeline index. */
  private NetworkTableEntry pipelineIndex;
  /** Current pipeline type */
  private NetworkTableEntry pipelineType;

  /**
   * Construct data for pipelines.
   *
   * @param camera {@link LimelightCamera} to use.
   */
  public LimelightPipelineData(LimelightCamera camera) {
    limelight = camera;
    limelightTable = limelight.getNTTable();
    processingLatency = limelightTable.getEntry("tl");
    captureLatency = limelightTable.getEntry("cl");
    pipelineIndex = limelightTable.getEntry("getpipe");
    pipelineType = limelightTable.getEntry("getpipetype");
  }

  /**
   * Gets the pipeline's processing latency contribution.
   *
   * @return Pipeline latency in milliseconds
   */
  public double getProcessingLatency() {
    return processingLatency.getDouble(0.0);
  }

  /**
   * Gets the capture latency.
   *
   * @return Capture latency in milliseconds
   */
  public double getCaptureLatency() {
    return captureLatency.getDouble(0.0);
  }

  /**
   * Gets the active pipeline index.
   *
   * @return Current pipeline index (0-9)
   */
  public double getCurrentPipelineIndex() {
    return pipelineIndex.getDouble(0);
  }

  /**
   * Gets the current pipeline type.
   *
   * @return Pipeline type string (e.g. "retro", "apriltag", etc)
   */
  public String getCurrentPipelineType() {
    return pipelineType.getString("");
  }
}
