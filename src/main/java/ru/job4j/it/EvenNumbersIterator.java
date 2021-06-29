package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

  private final int[] data;
  private int point = 0;

  public EvenNumbersIterator(int[] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    int temp = -1;
    for (int i = point; i < data.length; i++) {
      if (data[i] % 2 == 0) {
        point = i;
        temp++;
        break;
      }
    }
    return temp == 0;
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    } else {
      return data[point++];
    }
  }
}