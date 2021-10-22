package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

  public static List<String> filter(String file) {
    List<String> log = new ArrayList<>();
    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      for (String line = in.readLine(); line != null; line = in.readLine()) {
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

  public static void save(List<String> log, String file) {
    try (PrintWriter out = new PrintWriter(
        new BufferedOutputStream(
            new FileOutputStream(file)
        ))) {
      log.forEach(out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    List<String> log = filter("log.txt");
    save(log, "404.txt");
    log.forEach(System.out::println);
  }
}
