package ru.job4j.io.search;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.job4j.io.ArgsName;
import ru.job4j.sirialization.json.B;

public class SearchPredicate {

  Predicate<Path> predicate;

  public Predicate<Path> getPredicate(ArgsName argsName) {

    if (argsName.get("t").equals("name")) {
      predicate = p -> p.toFile().getName().equals(argsName.get("n"));
    }
    if (argsName.get("t").equals("mask")) {
      predicate = foundRegex(getMask(argsName));
    }
    if (argsName.get("t").equals("regex")) {
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
      } else {
        throw new IllegalArgumentException(
            "Mask is wrong. You need to write, for example, *.txt, for searching all files. "
                + " You need to write, for example, res?.txt, for searching all files, which start on 'res'.");
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
