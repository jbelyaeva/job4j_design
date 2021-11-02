package ru.job4j.io;

import static ru.job4j.io.Search.search;
import static ru.job4j.io.Search.validations;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

  public static void packFiles(List<Path> sources, Path target) {
    try (ZipOutputStream zip = new ZipOutputStream(
        new BufferedOutputStream(new FileOutputStream(target.toString())))) {
      for (Path source : sources) {
        zip.putNextEntry(new ZipEntry(source.toString()));
        try (var out = new BufferedInputStream(new FileInputStream(source.toString()))) {
          zip.write(out.readAllBytes());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    ArgsName jvm = ArgsName.of(args);
    Path target = Paths.get(jvm.get("o"));
    Path start = Paths.get(jvm.get("d"));
    validations(args, start, args.length);
    List<Path> files = search(start,
        p -> !p.toFile().getName().endsWith(Paths.get(jvm.get("e")).toString()));
    packFiles(files, target);
  }
}