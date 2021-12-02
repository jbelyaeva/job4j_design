package ru.job4j.io;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVReader {

  public static void handle(ArgsName argsName) {
    validations(argsName, 4);
    List<List<String>> scannerResult = getStringsFromFile(argsName);
    List<String> tableFields = scannerResult.get(1);
    List<String> dataTable = scannerResult.get(0);
    List<Integer> indexes = parseFilter(argsName, tableFields);
    List<String> fieldsForResult = getFieldsForResult(tableFields, indexes);
    List<List<String>> mas = getResultAfterFilter(tableFields, dataTable, indexes);
    StringBuilder builder = getStringBuilder(mas, fieldsForResult);
    if (("stdout").equals(argsName.get("out"))) {
      recordInConsole(builder);
    } else {
      recordInFile(argsName.get("out"), builder);
    }
  }

  private static StringBuilder getStringBuilder(List<List<String>> mas, List<String> fields) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < fields.size(); i++) {
      builder.append(fields.get(i));
      builder.append(";");
    }
    builder.setLength(builder.length() - 1);
    builder.append(System.lineSeparator());
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

  private static List<List<String>> getResultAfterFilter(List<String> tableField,
      List<String> result, List<Integer> indexes) {
    List<List<String>> temp = new ArrayList<>();
    int count = tableField.size();
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
            .filter(n -> (n + tableField.size() - indexes.get(finalI)) % count == 0)
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

  private static List<String> getFieldsForResult(List<String> tableFields, List<Integer> indexes) {
    List<String> fields = new ArrayList<>();
    for (int i = 0; i < indexes.size(); i++) {
      fields.add(tableFields.get(indexes.get(i)));
    }
    return fields;
  }

  private static List<List<String>> getStringsFromFile(ArgsName argsName) {
    List<String> scannerResult = new ArrayList<>();
    List<String> tableFields = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(argsName.get("path"),
        StandardCharsets.UTF_8))) {
      Scanner scanner = new Scanner(br).useDelimiter(argsName.get("delimiter"));
      if (scanner.hasNextLine()) {
        String[] headers = scanner.nextLine().split(argsName.get("delimiter"));
        tableFields.addAll(Arrays.asList(headers));
        while (scanner.hasNextLine()) {
          String[] lines = scanner.nextLine().split(argsName.get("delimiter"));
          scannerResult.addAll(Arrays.asList(lines));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    List<List<String>> res = new ArrayList<>();
    res.add(scannerResult);
    res.add(tableFields);
    return res;
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
