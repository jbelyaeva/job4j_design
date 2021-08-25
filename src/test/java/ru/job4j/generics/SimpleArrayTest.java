package ru.job4j.generics;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class SimpleArrayTest {

  @Test
  public void addElement() {
    SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
    simpleArray.add(1);
    assertThat(simpleArray.get(0), is(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void addElementWithIndexMoreThenSize() {
    SimpleArray<String> simpleArray = new SimpleArray<>(1);
    simpleArray.add("1");
    simpleArray.add("2");
  }

  @Test()
  public void setElement() {
    SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
    simpleArray.add(1);
    simpleArray.add(2);
    simpleArray.set(0, 3);
    assertThat(simpleArray.get(0), is(3));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void setElementWithIndexMoreThenSize() {
    SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
    simpleArray.add(1);
    simpleArray.set(5, 3);
  }

  @Test()
  public void getElement() {
    SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
    simpleArray.add(1);
    simpleArray.add(2);
    assertThat(simpleArray.get(0), is(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void getElementWithIndexMoreThenSize() {
    SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
    simpleArray.add(1);
    simpleArray.add(2);
    assertThat(simpleArray.get(5), is(1));
  }

  @Test()
  public void removeElement() {
    SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
    simpleArray.add(1);
    simpleArray.add(2);
    simpleArray.add(3);
    simpleArray.remove(1);
    assertThat(simpleArray.get(0), is(1));
    assertThat(simpleArray.get(1), is(3));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void removeElementWithIndexMoreThenSize() {
    SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
    simpleArray.add(1);
    simpleArray.remove(2);
  }

}