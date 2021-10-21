package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiTable {

  public static void main(String[] args) {
    try (FileOutputStream out = new FileOutputStream("result.txt")) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
          int res = i * j;
          if (res <= 9) {
            out.write((i + "x" + j + "=" + res + ";  ").getBytes());
          } else {
            out.write((i + "x" + j + "=" + res + "; ").getBytes());
          }
        }
        out.write(System.lineSeparator().getBytes());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
