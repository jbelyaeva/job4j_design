package ru.job4j.io;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVReader {

  public static void handle(ArgsName argsName) {
    validations(argsName, 4);
    List<String> scannerResult = getStringsFromFile(argsName);
    List<String> tableIndex = getTableFields(scannerResult);
    List<String> result = prepareScannerResultForWork(scannerResult);
    List<Integer> indexes = parseFilter(argsName, tableIndex);
    List<List<String>> mas = getResultAfterFilter(tableIndex, result, indexes);
    StringBuilder builder = getStringBuilder(mas);
    if (("stdout").equals(argsName.get("out"))) {
      recordInConsole(builder);
    } else {
      recordInFile(argsName.get("out"), builder);
    }
  }

  private static StringBuilder getStringBuilder(List<List<String>> mas) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < mas.get(0).size(); i++) {
      for (int j = 0; j < mas.size(); j++) {
        builder.append(mas.get(j).get(i));
        builder.append(";");
      }
      builder.setLength(builder.length() - 1);
      builder.append(System.lineSeparator());
    }
    return builder;
  }

  private static List<List<String>> getResultAfterFilter(List<String> tableIndex,
      List<String> result, List<Integer> indexes) {
    List<List<String>> temp = new ArrayList<>();
    int count = tableIndex.size();
    for (int i = 0; i < indexes.size(); i++) {
      int finalI = i;
      if (indexes.get(finalI) == 0) {
        List<String> a = IntStream.range(0, result.size())
            .filter(n -> n % count == 0)
            .mapToObj(result::get)
            .collect(Collectors.toList());
        temp.add(a);
      } else {
        List<String> a = IntStream.range(0, result.size())
            .filter(n -> (n + tableIndex.size() - indexes.get(finalI)) % count == 0)
            .mapToObj(result::get)
            .collect(Collectors.toList());
        temp.add(a);
      }
    }
    return temp;
  }

  private static List<Integer> parseFilter(ArgsName argsName, List<String> tableIndex) {
    String[] filter = argsName.get("filter").split(",");
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < tableIndex.size(); i++) {
      for (int j = 0; j < filter.length; j++) {
        if (tableIndex.get(i).equals(filter[j])) {
          indexes.add(i);
          break;
        }
      }
    }
    return indexes;
  }

  private static List<String> prepareScannerResultForWork(List<String> scannerResult) {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < scannerResult.size(); i++) {
      String temp = scannerResult.get(i);
      if (temp.contains("\r\n")) {
        String[] a = temp.split("\r\n");
        result.add(a[0]);
        result.add(a[1]);
      } else {
        result.add(temp);
      }
    }
    return result;
  }

  private static List<String> getTableFields(List<String> scannerResult) {
    List<String> tempMas = new ArrayList<>();
    for (int i = 0; i < scannerResult.size(); i++) {
      String temp = scannerResult.get(i);
      if (!temp.contains("\r\n")) {
        tempMas.add(temp);
      } else {
        tempMas.add(temp.split("\r\n")[0]);
        break;
      }
    }
    return tempMas;
  }

  private static List<String> getStringsFromFile(ArgsName argsName) {
    List<String> scannerResult = new ArrayList<>();
    try (Scanner scanner = new Scanner(Paths.get(argsName.get("path")).toFile())
        .useDelimiter(argsName.get("delimiter"))) {
      while (scanner.hasNext()) {
        scannerResult.add(scanner.next());
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return scannerResult;
  }

  private static void recordInFile(String target, StringBuilder builder) {
    try (var writer = new FileWriter(target)) {
      writer.write(builder.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void recordInConsole(StringBuilder builder) {
    System.out.println(builder);
  }

  private static void validations(ArgsName args, int countArgs) {
    if (args.size() != countArgs) {
      throw new IllegalArgumentException("Not all args");
    }
    File filePath = new File(args.get("path"));
    if (!filePath.isFile()) {
      throw new IllegalArgumentException("Directory is wrong");
    }
    File fileOut = new File(args.get("out"));
    if (!fileOut.getName().equals("stdout")) {
      if (!fileOut.isFile()) {
        throw new IllegalArgumentException("Directory out is wrong");
      }
    }
  }

  public static void main(String[] args) throws IOException {
    ArgsName argsName = ArgsName.of(new String[]{args[0], args[1], args[2], args[3]});
    CSVReader.handle(argsName);
  }

}
