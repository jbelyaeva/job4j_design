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

  public Boolean add(T model) {
    Objects.checkIndex(point, array.length - 1);
    int i = 0;
    while (array[i] != null && i != array.length - 1) {
      i++;
    }
    array[i] = model;
    return true;
  }

  public void set(int index, T model) {
    Objects.checkIndex(index, array.length);
    array[index] = model;
  }

  public void remove(int index) {
    Objects.checkIndex(index, array.length);
    System.arraycopy(array, index + 1, array, index, array.length - index - 1);
    array[array.length - 1] = null;
  }

  public T get(int index) {
    Objects.checkIndex(index, array.length);
    return array[index];
  }

  @Override
  public Iterator<T> iterator() {

    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return point < array.length;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return array[point++];
      }
    };
  }
  
}
