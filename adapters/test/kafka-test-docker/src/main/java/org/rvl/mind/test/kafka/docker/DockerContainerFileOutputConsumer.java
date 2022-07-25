package org.rvl.mind.test.kafka.docker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

import org.testcontainers.containers.output.OutputFrame;

public class DockerContainerFileOutputConsumer implements Consumer<OutputFrame> {

  private final FileWriter writer;

  public DockerContainerFileOutputConsumer(String path, String containerName) {
    try {
      new File(path).mkdirs();
      File file = new File(path + containerName + ".log");
      writer = new FileWriter(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void accept(OutputFrame outputFrame) {
    try {
      writer.write(outputFrame.getUtf8String());
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
