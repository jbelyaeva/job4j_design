package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

  public static List<String> filter(String file) {
    List<String> log = new ArrayList<>();
    try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
      List<String> lines = in.lines().collect(Collectors.toCollection(ArrayList::new));
      for (String line : lines) {
        String res = line.split("\"")[2].trim().split(" ")[0];
        if (res.equals("404")) {
          log.add(line);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return log;
  }

  public static void main(String[] args) {
    List<String> log = filter("log.txt");
    log.forEach(System.out::println);
  }
}
