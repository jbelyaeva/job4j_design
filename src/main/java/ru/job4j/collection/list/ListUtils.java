package ru.job4j.collection.list;

import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtils {

  public static <T> void addBefore(java.util.List<T> list, int index, T value) {
    Objects.checkIndex(index, list.size());
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      if (i.nextIndex() == index) {
        i.add(value);
        break;
      }
      i.next();
    }
  }

  public static <T> void addAfter(java.util.List<T> list, int index, T value) {
    Objects.checkIndex(index, list.size());
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      if (i.nextIndex() == index) {
        i.next();
        i.add(value);
        break;
      }
      i.next();
    }
  }

  public static <T> void removeIf(java.util.List<T> list, Predicate<T> filter) {
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      if (filter.test(i.next())) {
        i.remove();
      }
    }
  }

  public static <T> void replaceIf(java.util.List<T> list, Predicate<T> filter, T value) {
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      if (filter.test(i.next())) {
        i.remove();
        i.add(value);
      }
    }
  }

  public static <T> void removeAll(java.util.List<T> list, java.util.List<T> elements) {
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      if (elements.contains(i.next())) {
        i.remove();
      }
    }
  }

}
