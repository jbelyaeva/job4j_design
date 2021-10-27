package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

  private static void unavailable(String source, String target) {
    List<String> result = new ArrayList<>();
    try (BufferedReader in = new BufferedReader(new FileReader(source))) {
      int temp = 0;
      String str = null;
      for (String line = in.readLine(); line != null; line = in.readLine()) {
        String[] res = line.split(" ");
        if (temp == 0 && (res[0].equals("400") || res[0].equals("500"))) {
          str = res[1];
          temp++;
        }
        if (res[0].equals("200") && temp != 0) {
          result.add(str + ";" + res[1]);
          temp = 0;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    recordInFile(target, result);
  }

  private static void recordInFile(String target, List<String> result) {
    try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
      for (String s : result) {
        out.println(s);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    unavailable("./data/server.log", "./data/unavailable.csv");

    try (BufferedReader in = new BufferedReader(new FileReader("./data/unavailable.csv"))) {
      for (String line = in.readLine(); line != null; line = in.readLine()) {
        System.out.println(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
