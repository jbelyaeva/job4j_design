package ru.job4j.collection.set;

import java.util.Iterator;
import java.util.Objects;
import ru.job4j.collection.SimpleArray;

public class SimpleSet<T> implements Set<T> {

  private SimpleArray<T> set = new SimpleArray<>();

  @Override
  public boolean add(T value) {
    if (!this.contains(value)) {
      set.add(value);
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(T value) {
    for (T element:set) {
      if  (Objects.equals(element, value)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return set.iterator();
  }
}