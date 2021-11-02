package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

  private final Map<String, String> values = new HashMap<>();

  public String get(String key) {
    return values.get(key);
  }

  private void parse(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Not found any arguments");
    }
    for (String a : args) {
      String[] masFirst = a.split("=");
      if (!a.contains("=") || masFirst.length != 2) {
        throw new IllegalArgumentException("Illegal arguments");
      }
      String[] masSecond = masFirst[0].split("-");
      values.put(masSecond[1], masFirst[1]);
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
