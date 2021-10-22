package ru.job4j.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class ConfigTest {
  @Test
  public void whenPairWithoutComment() {
    String path = "./data/config.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("hibernate.connection.username"),is("postgres"));
  }

  @Test
  public void whenPairWithComment() {
    String path = "./data/config_with_comm.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("hibernate.connection.username"),is("postgres"));
  }

  @Test
  public void whenPairWithEmptyLine() {
    String path = "./data/config_with_comm.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("hibernate.connection.username"),is("postgres"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenErrorConfig() {
    String path = "./data/config_error.properties";
    Config config = new Config(path);
    config.load();
  }
}