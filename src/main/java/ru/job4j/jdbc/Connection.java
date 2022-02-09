package ru.job4j.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import ru.job4j.io.Config;

public class Connection {
private static java.sql.Connection connection;

  public static java.sql.Connection getConnection() throws Exception {
    Config config = new Config("./src/main/resources/app.properties");
    config.load();
    Class.forName(config.value("driver"));
    String url = config.value("url");
    String login = config.value("login");
    String password = config.value("password");
    connection = DriverManager.getConnection(url, login, password);
    return connection;
  }

  public static void main(String[] args) throws Exception {
    try (java.sql.Connection connection = getConnection()) {
      DatabaseMetaData metaData = connection.getMetaData();
      System.out.println(metaData.getUserName());
      System.out.println(metaData.getURL());
    }
  }
}
