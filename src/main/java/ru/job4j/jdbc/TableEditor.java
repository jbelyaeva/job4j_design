package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;
import org.postgresql.util.PSQLException;

public class TableEditor implements AutoCloseable {

  private java.sql.Connection connection;

  private Properties properties;

  public TableEditor(Properties properties) throws Exception {
    this.properties = properties;
    initConnection(properties);
  }

  private void initConnection(Properties properties) throws Exception {
    Class.forName(properties.getProperty("driver"));
    String url = properties.getProperty("url");
    String login = properties.getProperty("login");
    String password = properties.getProperty("password");
    connection = DriverManager.getConnection(url, login, password);
  }

  public void createTable(String tableName) {
    String sql = String.format(
        "create table if not exists %s(%s);",
        tableName,
        "id serial primary key"
    );
    perform(sql);
  }

  public void dropTable(String tableName) {
    String sql = String.format(
        "drop table if exists %s;",
        tableName
    );
    perform(sql);
  }

  public void addColumn(String tableName, String columnName, String type) {
    String sql = String.format(
        "alter table %s add column %s %s;",
        tableName,
        columnName,
        type
    );
    perform(sql);
  }

  public void dropColumn(String tableName, String columnName) {
    String sql = String.format(
        "alter table %s drop column %s;",
        tableName,
        columnName
    );
    perform(sql);
  }

  public void renameColumn(String tableName, String columnName, String newColumnName) {
    String sql = String.format(
        "alter table %s rename column %s to %s;",
        tableName,
        columnName,
        newColumnName
    );
    perform(sql);
  }

  private void perform(String request) {
    try (Statement statement = connection.createStatement()) {
      statement.execute(request);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public static String getTableScheme(java.sql.Connection connection, String tableName)
      throws Exception {
    var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
    var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
    var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
    buffer.add(header);
    try (var statement = connection.createStatement()) {
      try (var selection = statement.executeQuery(String.format(
          "select * from %s limit 1", tableName
      ))) {
        var metaData = selection.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
          buffer.add(String.format("%-15s|%-15s%n",
              metaData.getColumnName(i), metaData.getColumnTypeName(i))
          );
        }
      } catch (PSQLException e) {
        System.out.println("Таблица отсутствует в бд");
      }
    }
    return buffer.toString();
  }

  @Override
  public void close() throws Exception {
    if (connection != null) {
      connection.close();
    }
  }

  public static void main(String[] args) throws Exception {
    Properties properties = new Properties();
    try (FileInputStream fileInputStream = new FileInputStream(
        "./src/main/resources/app.properties")) {
      properties.load(fileInputStream);
    }
    try (TableEditor tableEditor = new TableEditor(properties)) {
      tableEditor.createTable("device");
      System.out.println(getTableScheme(tableEditor.connection, "device"));

      tableEditor.addColumn("device", "description", "text");
      System.out.println(getTableScheme(tableEditor.connection, "device"));

      tableEditor.renameColumn("device", "description", "desc1");
      System.out.println(getTableScheme(tableEditor.connection, "device"));

      tableEditor.dropColumn("device", "desc1");
      System.out.println(getTableScheme(tableEditor.connection, "device"));

      tableEditor.dropTable("device");
      System.out.println(getTableScheme(tableEditor.connection, "device"));
    }
  }
}
