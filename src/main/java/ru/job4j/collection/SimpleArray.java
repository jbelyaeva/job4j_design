package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

  private static final int INIT_CAPACITY = 2;

  private Object[] elementData;
  private int modCount;
  private int size;

  public SimpleArray() {
    this.elementData = new Object[INIT_CAPACITY];
  }

  public T get(int index) {
    Objects.checkIndex(index, size);
    return (T) elementData[index];
  }

  public void add(T model) {
    modCount++;
    if (size == elementData.length) {
      elementData = grow();
    }
    elementData[size] = model;
    size = size + 1;
  }

  private Object[] grow() {
    return elementData = Arrays.copyOf(elementData, size * 2);
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private int point = 0;
      private int expectedModCount = modCount;

      @Override
      public boolean hasNext() {
        return point < size;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
        return (T) elementData[point++];
      }
    };
  }
}