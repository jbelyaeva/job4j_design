package ru.job4j.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import ru.job4j.io.Config;

public class Connection {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Config config = new Config("./src/main/resources/app.properties");
    config.load();
    Class.forName(config.value("driver"));
    String url = config.value("url");
    String login = config.value("login");
    String password = config.value("password");

    try (java.sql.Connection connection = DriverManager.getConnection(url, login, password)) {
      DatabaseMetaData metaData = connection.getMetaData();
      System.out.println(metaData.getUserName());
      System.out.println(metaData.getURL());
    }
  }
}
