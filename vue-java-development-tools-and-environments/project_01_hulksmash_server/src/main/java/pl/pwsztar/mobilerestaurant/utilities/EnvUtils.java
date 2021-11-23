package pl.pwsztar.mobilerestaurant.utilities;

import java.io.*;
import java.util.Properties;

public class EnvUtils {
  public static Properties getProperties() {
    try (InputStream input = EnvUtils.class.getClassLoader().getResourceAsStream("application.properties")) {

      Properties prop = new Properties();

      if (input == null) {
        System.out.println("Sorry, unable to find config.properties");
        return null;
      }

      prop.load(input);
      return prop;

    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
