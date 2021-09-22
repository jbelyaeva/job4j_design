package ru.job4j.collection.map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

public class SimpleMapTest {

  @Test
  public void addOneInMap() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    Iterator<Integer> it = map.iterator();
    assertThat(it.next(), is(1));
  }

  @Test
  public void addThreeInMap() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    map.put(2, "2");
    map.put(3, "3");
    Iterator<Integer> it = map.iterator();
    assertThat(it.next(), is(1));
    assertThat(it.next(), is(2));
    assertThat(it.next(), is(3));
  }

  @Test
  public void checkExpandMap() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    map.put(2, "2");
    map.put(3, "3");
    map.put(4, "3");
    map.put(5, "3");
    map.put(6, "3");
    map.put(7, "3");
    map.put(8, "3");
    map.put(9, "3");
    map.put(10, "3");
    assertThat(map.size(), is(9));
  }

  @Test(expected = NoSuchElementException.class)
  public void checkReplaceElementsInMap() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    map.put(1, "1");
    assertThat(map.size(), is(1));
    Iterator<Integer> it = map.iterator();
    assertThat(it.next(), is(1));
    it.next();
  }

  @Test
  public void getValueByKey() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    map.put(2, "2");
    assertThat(map.get(1), is("1"));
  }

  @Test
  public void getErrorKey() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    assertThat(map.get(2), nullValue());
  }

  @Test
  public void deleteByKey() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    assertThat(map.remove(1), is(true));
  }

  @Test
  public void deleteNullElement() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    assertThat(map.remove(1), is(false));
  }

  @Test
  public void deleteErrorElement() {
    SimpleMap<Integer, String> map = new SimpleMap<>();
    map.put(1, "1");
    assertThat(map.remove(10), is(false));
  }
}