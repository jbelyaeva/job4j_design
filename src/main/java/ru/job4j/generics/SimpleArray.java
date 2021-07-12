package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

  private T[] array;
  private int point = 0;

  public SimpleArray(int count) {
    array = (T[]) new Object[count];
  }

  public void add(T model) {
    array[point++] = model;
  }

  public void set(int index, T model) {
    Objects.checkIndex(index, point);
    array[index] = model;
  }

  public void remove(int index) {
    Objects.checkIndex(index, point);
    System.arraycopy(array, index + 1, array, index, array.length - index - 1);
    point--;
  }

  public T get(int index) {
    Objects.checkIndex(index, point);
    return array[index];
  }

  @Override
  public Iterator<T> iterator() {

    return new Iterator<T>() {
      private int it = 0;
      @Override
      public boolean hasNext() {
        return it < point;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return array[it++];
      }
    };
  }

}
