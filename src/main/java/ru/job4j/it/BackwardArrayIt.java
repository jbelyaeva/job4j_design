package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {

  private final int[] data;
  private int point;

  public BackwardArrayIt(int[] data) {
    this.data = data;
    this.point = data.length - 1;
  }

  @Override
  public boolean hasNext() {
    if (point >= 0) {
      return point < data.length;
    } else {
      return false;
    }
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return data[point--];
  }
}
