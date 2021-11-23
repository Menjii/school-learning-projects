package pl.pwsztar.mobilerestaurant.utilities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
  public static String getFileContext(String path) {
    try {
      return new String(Files.readAllBytes(Paths.get(FileUtils.class.getResource(path).toURI())));
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
      return "";
    }
  }
}
