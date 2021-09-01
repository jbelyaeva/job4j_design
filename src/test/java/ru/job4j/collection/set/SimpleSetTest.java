package ru.job4j.collection.set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class SimpleSetTest {
  @Test
  public void whenAddNonNull() {
    Set<Integer> set = new SimpleSet<>();
    assertTrue(set.add(1));
    assertTrue(set.contains(1));
    assertFalse(set.add(1));
  }


  @Test
  public void whenAddNull() {
    Set<Integer> set = new SimpleSet<>();
    assertTrue(set.add(null));
    assertTrue(set.contains(null));
    assertFalse(set.add(null));
  }
}