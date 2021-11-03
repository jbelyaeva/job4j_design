package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

  public static void main(String[] args) throws IOException {
    validations(args, 2);
    search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1]))
        .forEach(System.out::println);
  }

  public static void validations(String[] args, int countArgs) {
    if (args.length != countArgs) {
      throw new IllegalArgumentException("Not all args");
    }
    File file = new File(args[0]);
    if (!file.isDirectory()) {
      throw new IllegalArgumentException("Directory is wrong");
    }
  }

  public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
    SearchFiles searcher = new SearchFiles(condition);
    Files.walkFileTree(root, searcher);
    return searcher.getPaths();
  }
}
