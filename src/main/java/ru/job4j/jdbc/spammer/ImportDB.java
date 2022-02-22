package ru.job4j.jdbc.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

  private Properties cfg;
  private String dump;

  public ImportDB(Properties cfg, String dump) {
    this.cfg = cfg;
    this.dump = dump;
  }

  public List<User> load() {
    List<User> users = new ArrayList<>();
    try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
      rd.lines().forEach(e -> {
        var str = e.split(";");
        if (str.length != 2 || str[0].equals("") || str[1].equals("")) {
          throw new IllegalArgumentException("Data error");
        }
        users.add(new User(str[0], str[1]));
      });
    } catch (IOException e) {
      throw new IllegalArgumentException("File is wrong");
    }
    return users;
  }

  public void save(List<User> users) throws ClassNotFoundException, SQLException {
    Class.forName(cfg.getProperty("jdbc.driver"));
    try (Connection cnt = DriverManager.getConnection(
        cfg.getProperty("jdbc.url"),
        cfg.getProperty("jdbc.username"),
        cfg.getProperty("jdbc.password")
    )) {
      for (User user : users) {
        try (PreparedStatement ps = cnt
            .prepareStatement("insert into spammer (name, email) values (?, ?)")) {
          ps.setString(1, user.name);
          ps.setString(2, user.email);
          ps.execute();
        }
      }
    }
  }

  private static class User {

    String name;
    String email;

    public User(String name, String email) {
      this.name = name;
      this.email = email;
    }
  }

  public static void main(String[] args) throws Exception {
    Properties cfg = new Properties();
    try (FileInputStream in = new FileInputStream("./src/main/resources/appSpam.properties")) {
      cfg.load(in);
    }
    ImportDB db = new ImportDB(cfg, "./testData/dump.txt");
    db.save(db.load());
  }
}
