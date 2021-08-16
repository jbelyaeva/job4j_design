package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ForwardLinkedTest {

  @Test(expected = NoSuchElementException.class)
  public void whenDeleteFirst() {
    ForwardLinked<Integer> linked = new ForwardLinked<>();
    linked.add(1);
    linked.deleteFirst();
    linked.iterator().next();
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
}