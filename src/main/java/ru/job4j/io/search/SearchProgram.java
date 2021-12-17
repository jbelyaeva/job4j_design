package ru.job4j.io.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

public class SearchProgram {

  static List<Path> paths = new ArrayList<>();

  public static void validations(ArgsName args, int countArgs) {
    if (args.size() != countArgs) {
      throw new IllegalArgumentException("Not all args");
    }
    File filePath = new File(args.get("d"));
    if (!filePath.isDirectory()) {
      throw new IllegalArgumentException("Directory is wrong");
    }
  }

  public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
    SearchFiles searcher = new SearchFiles(condition);
    Files.walkFileTree(root, searcher);
    return searcher.getPaths();
  }

  private static void recordInFile(String target, List<Path> paths) {
    try (var writer = new BufferedWriter(
        new FileWriter(target, Charset.forName("WINDOWS-1251")))) {
      for (Path path : paths) {
        writer.write(path.toAbsolutePath().toString());
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println("File write error");
    }
  }

  public static void main(String[] args) throws IOException {
    ArgsName argsName = ArgsName.of(new String[]{args[0], args[1], args[2], args[3]});
    validations(argsName, 4);
    paths = search(Paths.get(argsName.get("d")), new SearchPredicate().getPredicate(argsName));
    recordInFile(argsName.get("o"), paths);
  }
}
