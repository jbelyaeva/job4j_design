package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {

  private final String path;
  private final String botAnswers;
  private boolean pause;
  private static final String OUT = "закончить";
  private static final String STOP = "стоп";
  private static final String CONTINUE = "продолжить";

  public ConsoleChat(String path, String botAnswers) {
    this.path = path;
    this.botAnswers = botAnswers;
  }

  public void run() {
    List<String> log = new ArrayList<>();
    List<String> answers = readPhrases();
    try (var reader = new BufferedReader(
        new InputStreamReader(System.in))) {
      String line = reader.readLine();
      log.add(line);
      while (!OUT.equals(line)) {
        if (!isPausa(line)) {
          String answer = answers.get(new Random().nextInt(answers.size()));
          System.out.println(answer);
          log.add(answer);
        }
        line = reader.readLine();
        log.add(line);
      }
      saveLog(log);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean isPausa(String line) {
    if (STOP.equals(line)) {
      pause = true;
    }
    if (CONTINUE.equals(line)) {
      pause = false;
    }
    return pause;
  }

  private List<String> readPhrases() {
    List<String> result = new ArrayList<>();
    try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
      result = in.lines().collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private void saveLog(List<String> log) {
    try (PrintWriter pw = new PrintWriter(
        new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
      log.forEach(pw::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ConsoleChat cc = new ConsoleChat("./testData/log.txt", "./testData/chatAnswers.txt");
    cc.run();
  }
}