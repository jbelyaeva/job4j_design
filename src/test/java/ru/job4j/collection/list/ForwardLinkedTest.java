package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ForwardLinkedTest {

  @Test(expected = NoSuchElementException.class)
  public void whenDeleteFirst() {
    ForwardLinked<Integer> linked = new ForwardLinked<>();
    linked.add(1);
    linked.deleteFirst();
    linked.iterator().next();
  }

  @Test
  public void whenAddFirst() {
    ForwardLinked<Integer> linked = new ForwardLinked<>();
    linked.addFirst(1);
    linked.addFirst(2);
    Iterator<Integer> it = linked.iterator();
    assertThat(it.next(), is(2));
  }

  @Test(expected = NoSuchElementException.class)
  public void whenDeleteEmptyLinked() {
    ForwardLinked<Integer> linked = new ForwardLinked<>();
    linked.deleteFirst();
  }

  @Test
  public void whenMultiDelete() {
    ForwardLinked<Integer> linked = new ForwardLinked<>();
    linked.add(1);
    linked.add(2);
    assertThat(linked.deleteFirst(), is(1));
    Iterator<Integer> it = linked.iterator();
    assertThat(it.next(), is(2));
  }

  @Test
  public void whenAddAndRevertThenIter() {
    ForwardLinked<Integer> linked = new ForwardLinked<>();
    linked.add(1);
    linked.add(2);
    linked.revert();
    Iterator<Integer> it = linked.iterator();
    assertThat(it.next(), is(2));
    assertThat(it.next(), is(1));
  }

  @Test
  public void whenAddFiveAndRevertThenIter() {
    ForwardLinked<Integer> linked = new ForwardLinked<>();
    linked.add(1);
    linked.add(2);
    linked.add(3);
    linked.add(4);
    linked.add(5);
    linked.revert();
    Iterator<Integer> it = linked.iterator();
    assertThat(it.next(), is(5));
    assertThat(it.next(), is(4));
    assertThat(it.next(), is(3));
    assertThat(it.next(), is(2));
    assertThat(it.next(), is(1));
  }

  @Test
  public void whenSize0ThenReturnFalse() {
    ForwardLinked<Integer> emptyList = new ForwardLinked<>();
    assertFalse(emptyList.revert());
  }

  @Test
  public void whenSize1ThenReturnFalse() {
    ForwardLinked<Integer> singleList = new ForwardLinked<>();
    singleList.add(1);
    assertFalse(singleList.revert());
  }
}