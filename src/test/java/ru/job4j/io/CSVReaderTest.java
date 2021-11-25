package ru.job4j.io;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;

public class CSVReaderTest {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Test
  public void whenFilterTwoColumnsNew() throws Exception {
    String data = String.join(
        System.lineSeparator(),
        "name;age;last_name;education",
        "Tom;20;Smith;Bachelor",
        "Jack;25;Johnson;Undergraduate",
        "William;30;Brown;Secondary special"
    );
    File file = temporaryFolder.newFile("source.csv");
    File target = temporaryFolder.newFile("target.csv");
    ArgsName argsName = ArgsName.of(new String[]{
        "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
    });
    Files.writeString(file.toPath(), data);
    String expected = String.join("",
        "name;age", "Tom;20", "Jack;25", "William;30");
    CSVReader.handle(argsName);
    Assert.assertEquals(expected, Files.readString(target.toPath()));
  }

  @Test
  @Ignore
  public void whenFilterTwoColumns() throws Exception {
    String data = String.join(
        System.lineSeparator(),
        "name;age;last_name;education",
        "Tom;20;Smith;Bachelor",
        "Jack;25;Johnson;Undergraduate",
        "William;30;Brown;Secondary special"
    );
    File file = temporaryFolder.newFile("source.csv");
    File target = temporaryFolder.newFile("target.csv");
    ArgsName argsName = ArgsName.of(new String[]{
        "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
    });
    Files.writeString(file.toPath(), data);
    String expected = String.join(
        System.lineSeparator(),
        "name;age",
        "Tom;20",
        "Jack;25",
        "William;30"
    ).concat(System.lineSeparator());
    CSVReader.handle(argsName);
    Assert.assertEquals(expected, Files.readString(target.toPath()));
  }

  @Test
  @Ignore
  public void whenFilterTreeColumns() throws Exception {
    String data = String.join(
        System.lineSeparator(),
        "name;age;last_name;education",
        "Tom;20;Smith;Bachelor",
        "Jack;25;Johnson;Undergraduate",
        "William;30;Brown;Secondary special"
    );
    File file = temporaryFolder.newFile("source1.csv");
    File target = temporaryFolder.newFile("target1.csv");
    ArgsName argsName = ArgsName.of(new String[]{
        "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age,education"
    });
    Files.writeString(file.toPath(), data);
    String expected = String.join(
        System.lineSeparator(),
        "name;age;education",
        "Tom;20;Bachelor",
        "Jack;25;Undergraduate",
        "William;30;Secondary special"
    ).concat(System.lineSeparator());
    CSVReader.handle(argsName);
    Assert.assertEquals(expected, Files.readString(target.toPath()));
  }

  @Test
  @Ignore
  public void whenFilterOneColumns() throws Exception {
    String data = String.join(
        System.lineSeparator(),
        "name;age;last_name;education",
        "Tom;20;Smith;Bachelor",
        "Jack;25;Johnson;Undergraduate",
        "William;30;Brown;Secondary special"
    );
    File file = temporaryFolder.newFile("source.csv");
    File target = temporaryFolder.newFile("target3.csv");
    ArgsName argsName = ArgsName.of(new String[]{
        "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=education"
    });
    Files.writeString(file.toPath(), data);
    String expected = String.join(
        System.lineSeparator(),
        "education",
        "Bachelor",
        "Undergraduate",
        "Secondary special"
    ).concat(System.lineSeparator());
    CSVReader.handle(argsName);
    Assert.assertEquals(expected, Files.readString(target.toPath()));
  }

  @Test
  @Ignore
  public void whenFilterChangeColumns() throws Exception {
    String data = String.join(
        System.lineSeparator(),
        "name;age;last_name;education",
        "Tom;20;Smith;Bachelor",
        "Jack;25;Johnson;Undergraduate",
        "William;30;Brown;Secondary special"
    );
    File file = temporaryFolder.newFile("source.csv");
    File target = temporaryFolder.newFile("target4.csv");
    ArgsName argsName = ArgsName.of(new String[]{
        "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=education,name"
    });
    Files.writeString(file.toPath(), data);
    String expected = String.join(
        System.lineSeparator(),
        "name;education",
        "Tom;Bachelor",
        "Jack;Undergraduate",
        "William;Secondary special"
    ).concat(System.lineSeparator());
    CSVReader.handle(argsName);
    Assert.assertEquals(expected, Files.readString(target.toPath()));
  }

  @Test
  @Ignore
  public void whenFilterTwoColumnsInConsole() throws Exception {
    String data = String.join(
        System.lineSeparator(),
        "name;age;last_name;education",
        "Tom;20;Smith;Bachelor",
        "Jack;25;Johnson;Undergraduate",
        "William;30;Brown;Secondary special"
    );
    File file = temporaryFolder.newFile("source.csv");
    ArgsName argsName = ArgsName.of(new String[]{
        "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=name,age"
    });
    Files.writeString(file.toPath(), data);
    CSVReader.handle(argsName);
  }

  @Test
  @Ignore
  public void whenFilterFiveColumns() throws Exception {
    String data = String.join(
        System.lineSeparator(),
        "name;age;last_name;re;education",
        "Tom;20;Smith;re;Bachelor",
        "Jack;25;Johnson;re;Undergraduate",
        "William;30;Brown;re;Secondary special"
    );
    File file = temporaryFolder.newFile("source.csv");
    File target = temporaryFolder.newFile("target.csv");
    ArgsName argsName = ArgsName.of(new String[]{
        "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=education"
    });
    Files.writeString(file.toPath(), data);
    String expected = String.join(
        System.lineSeparator(),
        "education",
        "Bachelor",
        "Undergraduate",
        "Secondary special"
    ).concat(System.lineSeparator());
    CSVReader.handle(argsName);
    Assert.assertEquals(expected, Files.readString(target.toPath()));
  }
}