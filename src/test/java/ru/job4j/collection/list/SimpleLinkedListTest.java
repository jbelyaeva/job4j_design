package ru.job4j.collection.list;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Iterator;
import org.hamcrest.core.Is;
import org.junit.Test;

public class SimpleLinkedListTest {
  @Test
  public void whenAddAndGet() {
    List<Integer> list = new SimpleLinkedList<>();
    list.add(1);
    list.add(2);
    assertThat(list.get(0), Is.is(1));
    assertThat(list.get(1), Is.is(2));
  }

 void whenGetFromOutOfBoundThenExceptionThrown() {
    List<Integer> list = new SimpleLinkedList<>();
    list.add(1);
    list.add(2);
    list.get(2);
  }

  @Test
  public void whenGetIteratorTwiceThenEveryFromBegin() {
    List<Integer> list = new SimpleLinkedList<>();
    list.add(1);
    list.add(2);

    Iterator<Integer> first = list.iterator();
    assertThat(first.hasNext(), Is.is(true));
    assertThat(first.next(), Is.is(1));
    assertThat(first.hasNext(), Is.is(true));
    assertThat(first.next(), Is.is(2));
    assertThat(first.hasNext(), Is.is(false));

    Iterator<Integer> second = list.iterator();
    assertThat(second.hasNext(), Is.is(true));
    assertThat(second.next(), Is.is(1));
    assertThat(second.hasNext(), Is.is(true));
    assertThat(second.next(), Is.is(2));
    assertThat(second.hasNext(), Is.is(false));
  }

}