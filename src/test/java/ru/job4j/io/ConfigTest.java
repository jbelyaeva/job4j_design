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
    assertThat(config.value("hibernate.connection.username"), is("postgres"));
  }

  @Test
  public void whenPairWithComment() {
    String path = "./data/config_with_comm.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("hibernate.connection.username"), is("postgres"));
  }

  @Test
  public void whenPairWithEmptyLine() {
    String path = "./data/config_with_comm.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("hibernate.connection.username"), is("postgres"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenErrorConfigWithoutKey() {
    String path = "./data/config_error_without_key.properties";
    Config config = new Config(path);
    config.load();
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenErrorConfigWithoutEquals() {
    String path = "./data/config_error_without_equals.properties";
    Config config = new Config(path);
    config.load();
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenErrorConfigTwoEquals() {
    String path = "./data/config_error_two_equals.properties";
    Config config = new Config(path);
    config.load();
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenErrorConfigWithoutValue() {
    String path = "./data/config_error_without_value.properties";
    Config config = new Config(path);
    config.load();
  }
}