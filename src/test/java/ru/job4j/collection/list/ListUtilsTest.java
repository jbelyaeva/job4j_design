package ru.job4j.collection.list;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.hamcrest.core.Is;
import org.junit.Test;

public class ListUtilsTest {

  @Test
  public void whenAddBefore() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addBefore(input, 1, 2);
    assertThat(Arrays.asList(1, 2, 3), Is.is(input));
  }

  @Test
  public void whenAddAfter() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
    ListUtils.addAfter(input, 1, 2);
    assertThat(Arrays.asList(1, 3, 2, 4), Is.is(input));
  }

  @Test
  public void whenAddAfter2() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addAfter(input, 1, 2);
    assertThat(Arrays.asList(1, 3, 2), Is.is(input));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenAddBeforeWithInvalidIndex() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addBefore(input, 3, 2);
  }

  @Test
  public void whenAddAfterLast() {
    List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
    ListUtils.addAfter(input, 2, 3);
    assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
  }

  @Test
  public void whenRemoveAll() {
    List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
    List<Integer> removeList = new ArrayList<>(Arrays.asList(2, 0));
    ListUtils.removeAll(input, removeList);
    assertThat(Collections.singletonList(1), Is.is(input));
  }

  @Test
  public void whenRemoveIf() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    ListUtils.removeIf(input, value -> value % 2 == 1);
    assertThat(Arrays.asList(2, 4, 6), Is.is(input));
  }

  @Test
  public void whenReplaceIf() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
    ListUtils.replaceIf(input, value -> value == 2, 5);
    assertThat(Arrays.asList(1, 5, 3), Is.is(input));
  }
}