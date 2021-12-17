package ru.job4j.io.search;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import ru.job4j.io.ArgsName;

public class SearchPredicate {

  Predicate<Path> predicate;

  public Predicate<Path> getPredicate(ArgsName argsName) {

    if (("name").equals(argsName.get("t"))) {
      predicate = p -> p.toFile().getName().equals(argsName.get("n"));
    }
    if (("mask").equals(argsName.get("t"))) {
      predicate = foundRegex(getMask(argsName));
    }
    if (("regex").equals(argsName.get("t"))) {
      predicate = foundRegex(argsName.get("n"));
    }
    return predicate;
  }

  private String getMask(ArgsName argsName) {
    String mask = argsName.get("n");
    if (mask.contains("*.")) {
      mask = mask.replace("*", "^.*") + "$";
    } else {
      if (mask.contains("?.")) {
        mask = mask.replace("?", ".*\\") + "$";
      }
      if (mask.startsWith("?")) {
        mask = mask.replace("?", "[^ ]{0,}") + "$";
      } else {
        throw new IllegalArgumentException(
            "Mask is wrong. Examples: "
                + "1. You need to write *.txt for searching all files. "
                + "2. You need to write res?.txt for searching all files, which started on 'res'. "
                + "3. You need to write ?t.txt for searching all files, which finished on 't'. ");
      }
    }
    return mask;
  }

  private Predicate<Path> foundRegex(String file) {
    return foundRegex -> {
      var pattern = Pattern.compile(file);
      var matcher = pattern.matcher(foundRegex.toFile().getName());
      return matcher.find();
    };
  }
}
