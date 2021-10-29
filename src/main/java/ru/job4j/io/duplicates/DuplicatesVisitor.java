package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

  private Set<FileProperty> box = new HashSet<>();
  private List<Path> result = new ArrayList<>();

  public List<Path> getResult() {
    return result;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    FileProperty target = new FileProperty(file.toFile().length(), file.toFile().getName());
    if (box.contains(target)) {
      result.add(file);
    } else {
      box.add(target);
    }
    return FileVisitResult.CONTINUE;
  }
}
