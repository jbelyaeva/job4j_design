package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

  private final String path;
  private final Map<String, String> values = new HashMap<>();

  public Config(final String path) {
    this.path = path;
  }

  public void load() {
    boolean temp = false;
    try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
      for (String line = in.readLine(); line != null; line = in.readLine()) {
        if (line.contains("#") || line.length() == 0) {
          continue;
        }
        String[] res = line.split("=");
        if (res[0].length() == 0) {
          temp = true;
          break;
        }
        values.put(res[0], res.length == 1 ? null : res[1]);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (temp) {
      throw new IllegalArgumentException("Error config structure!!");
    }
  }

  public String value(String key) {
    return values.get(key);
  }

  @Override
  public String toString() {
    StringJoiner out = new StringJoiner(System.lineSeparator());
    try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
      read.lines().forEach(out::add);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out.toString();
  }

  public static void main(String[] args) {
    System.out.println(new Config("./data/config.properties"));
  }

}
