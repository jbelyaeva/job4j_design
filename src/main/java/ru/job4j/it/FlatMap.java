package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {

  private final Iterator<Iterator<T>> data;
  private Iterator<T> cursor = Collections.emptyIterator();

  public FlatMap(Iterator<Iterator<T>> data) {
    this.data = data;
    this.cursor = data.next();
  }

  @Override
  public boolean hasNext() {
    boolean temp = false;
    while (data.hasNext() || cursor.hasNext()) {
      if (cursor.hasNext()) {
        temp = true;
        break;
      }
      cursor = data.next();
    }
    return temp;
  }

  @Override
  public T next() {
    if (cursor.hasNext()) {
      return cursor.next();
    } else {
      cursor = data.next();
      if (cursor.hasNext()) {
        return cursor.next();
      } else {
        throw new NoSuchElementException();
      }
    }
  }

  public static void main(String[] args) {
    Iterator<Iterator<Integer>> data = List.of(
        List.of(1, 2, 3).iterator(),
        List.of(4, 5, 6).iterator(),
        List.of(7, 8, 9).iterator()
    ).iterator();
    FlatMap<Integer> flat = new FlatMap<>(data);
    while (flat.hasNext()) {
      System.out.println(flat.next());
    }
  }
}
