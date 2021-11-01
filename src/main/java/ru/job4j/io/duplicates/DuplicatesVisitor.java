package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

  private Map<FileProperty, List<Path>> box = new HashMap<>();
  private List<Path> result = new ArrayList<>();

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    FileProperty target = new FileProperty(file.toFile().length(), file.toFile().getName());
    if (box.containsKey(target)) {
      List<Path> paths = box.get(target);
      paths.add(file);
      box.put(target, paths);
    } else {
      List<Path> newPath = new ArrayList<>();
      newPath.add(file);
      box.put(target, newPath);
    }
    return FileVisitResult.CONTINUE;

  }

 public List<Path> getResult() {
    int i = 0;
    for (Map.Entry<FileProperty, List<Path>> entry : box.entrySet()) {
      int countPath = entry.getValue().size();
      if (countPath > 1) {
        for (int j = 0; j < countPath; j++) {
          result.add(entry.getValue().get(j));
        }
      }
      ++i;
    }
    return result;
  }
}
