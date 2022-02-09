package ru.job4j.jdbc;

import static ru.job4j.jdbc.Connection.getConnection;

import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;
import org.postgresql.util.PSQLException;

public class TableEditor implements AutoCloseable {

  private java.sql.Connection connection;

  private Properties properties;

  public TableEditor(Properties properties) throws Exception {
    this.properties = properties;
    initConnection();
  }

  private void initConnection() throws Exception {
    connection = getConnection();
  }

  public static void createTable(String tableName) {
    try (java.sql.Connection connection = getConnection()) {
      try (Statement statement = connection.createStatement()) {
        String sql = String.format(
            "create table if not exists %s(%s, %s);",
            tableName,
            "id serial primary key",
            "name text"
        );
        statement.execute(sql);
      }
    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
  }

  public static void dropTable(String tableName) {
    try (java.sql.Connection connection = getConnection()) {
      try (Statement statement = connection.createStatement()) {
        String sql = String.format(
            "drop table if exists %s;",
            tableName
        );
        statement.execute(sql);
      }
    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
  }

  public static void addColumn(String tableName, String columnName, String type) {
    try (java.sql.Connection connection = getConnection()) {
      try (Statement statement = connection.createStatement()) {
        String sql = String.format(
            "alter table %s add column %s %s;",
            tableName,
            columnName,
            type
        );
        statement.execute(sql);
      }
    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
  }

  public static void dropColumn(String tableName, String columnName) {
    try (java.sql.Connection connection = getConnection()) {
      try (Statement statement = connection.createStatement()) {
        String sql = String.format(
            "alter table %s drop column %s;",
            tableName,
            columnName
        );
        statement.execute(sql);
      }
    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
  }

  public static void renameColumn(String tableName, String columnName, String newColumnName) {
    try (java.sql.Connection connection = getConnection()) {
      try (Statement statement = connection.createStatement()) {
        String sql = String.format(
            "alter table %s rename column %s to %s;",
            tableName,
            columnName,
            newColumnName
        );
        statement.execute(sql);
      }
    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
  }

  public static String getTableScheme(java.sql.Connection connection, String tableName) throws Exception {
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
    try (java.sql.Connection connection = getConnection()) {
      createTable("device");
      System.out.println(getTableScheme(connection, "device"));

      addColumn("device", "description", "text");
      System.out.println(getTableScheme(connection, "device"));

      renameColumn("device", "description", "desc1");
      System.out.println(getTableScheme(connection, "device"));

      dropColumn("device", "desc1");
      System.out.println(getTableScheme(connection, "device"));

      dropTable("device");
      System.out.println(getTableScheme(connection, "device"));
    }
  }
}
