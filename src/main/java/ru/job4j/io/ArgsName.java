package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

  private final Map<String, String> values = new HashMap<>();

  public String get(String key) {
    return values.get(key);
  }

  public Integer size() {
    return values.size();
  }

  private void parse(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Not found any arguments");
    }
    for (String arg : args) {
      if (!arg.startsWith("-") || !arg.contains("=")) {
        throw new IllegalArgumentException("Not found any arguments");
      }
      String[] res = arg.split("=");
      if (res.length != 2 || res[0].length() < 2 || res[1].length() == 0) {
        throw new IllegalArgumentException("Not found any arguments");
      }
      values.put(res[0].substring(1), res[1]);
    }
  }

  public static ArgsName of(String[] args) {
    ArgsName names = new ArgsName();
    names.parse(args);
    return names;
  }

  public static void main(String[] args) {
    ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
    System.out.println(jvm.get("Xmx"));

    ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
    System.out.println(zip.get("out"));
  }
}
