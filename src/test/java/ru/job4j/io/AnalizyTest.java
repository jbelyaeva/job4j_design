package ru.job4j.io;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.job4j.io.Analizy.unavailable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class AnalizyTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void serverAccess() throws IOException {
    File source = folder.newFile("server.log");
    File target = folder.newFile("unavailable.csv");
    try (PrintWriter out = new PrintWriter(source)) {
      out.println("200 10:56:01");
      out.println("200 10:57:01");
      out.println("400 10:58:01");
      out.println("200 10:59:01");
      out.println("500 11:01:02");
      out.println("200 11:02:02");
      out.println("200 12:56:01");
      out.println("500 12:57:01");
      out.println("400 12:58:01");
      out.println("500 12:59:01");
      out.println("400 13:01:02");
      out.println("200 13:02:02");
    }
    unavailable(source.getAbsolutePath(), target.getAbsolutePath());
    StringBuilder rsl = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new FileReader(target))) {
      in.lines().forEach(rsl::append);
    }
    assertThat(rsl.toString(), is(
        "10:58:01;10:59:01"
            + "11:01:02;11:02:02"
            + "12:57:01;13:02:02"));
  }

  @Test
  public void serverWorkAllTime() throws IOException {
    File source = folder.newFile("server.log");
    File target = folder.newFile("unavailable.csv");
    try (PrintWriter out = new PrintWriter(source)) {
      out.println("200 10:56:01");
      out.println("200 10:57:01");
      out.println("200 10:59:01");
      out.println("200 11:02:02");
      out.println("200 12:56:01");
      out.println("200 13:02:02");
    }
    unavailable(source.getAbsolutePath(), target.getAbsolutePath());
    StringBuilder rsl = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new FileReader(target))) {
      in.lines().forEach(rsl::append);
    }
    assertThat(rsl.toString(), is(
        ""));
  }

  @Test
  public void serverDontWorkAllTime() throws IOException {
    File source = folder.newFile("server.log");
    File target = folder.newFile("unavailable.csv");
    try (PrintWriter out = new PrintWriter(source)) {
      out.println("400 10:58:01");
      out.println("500 11:01:02");
      out.println("500 12:57:01");
      out.println("400 12:58:01");
      out.println("500 12:59:01");
      out.println("400 13:01:02");
    }
    unavailable(source.getAbsolutePath(), target.getAbsolutePath());
    StringBuilder rsl = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new FileReader(target))) {
      in.lines().forEach(rsl::append);
    }
    assertThat(rsl.toString(), is(
        "10:58:01"));
  }
}