package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

  private static final float LOAD_FACTOR = 0.75f;

  private int capacity = 8;

  private int count = 0;

  private int modCount = 0;

  private MapEntry<K, V>[] table = new MapEntry[capacity];

  @Override
  public boolean put(K key, V value) {
    int index = this.indexCalc(key);
    if (count >= table.length) {
      expand();
    }
    if (table[index] == null) {
      table[index] = new MapEntry<>(key, value);
      modCount++;
      count++;
      return true;
    }
    MapEntry<K, V> elem = table[index];
    if (!elem.getKey().equals(key)) {
      return false;
    }
    elem.setValue(value);
    modCount++;
    return true;
  }

  private int hash(int hashCode) {
    int hash;
    hash = hashCode;
    hash = hash ^ (hash >>> 16);
    return hash;
  }

  private int indexCalc(K key) {
    int hashCode = key.hashCode();
    return indexFor(hash(hashCode));
  }

  private int indexFor(int hash) {
    return hash % table.length;
  }

  public int size() {
    return count;
  }

  private void expand() {
    if (count == table.length) {
      int newSize = (int) (table.length * LOAD_FACTOR);
      MapEntry<K, V>[] newContainer = new MapEntry[table.length + newSize];
      System.arraycopy(table, 0, newContainer, 0, table.length);
      table = newContainer;
      modCount++;
    }
  }

  @Override
  public V get(K key) {
    int index = this.indexCalc(key);
    MapEntry<K, V> elem = table[index];
    if (elem != null) {
      return elem.getValue();
    }
    return null;
  }

  @Override
  public boolean remove(K key) {
    int index = this.indexCalc(key);
    MapEntry<K, V> elem = table[index];
    if (elem != null) {
      table[index] = null;
      count--;
      modCount++;
      return true;
    }
    return false;
  }

  @Override
  public Iterator<K> iterator() {
    return new Iterator<>() {
      final MapEntry<K, V>[] tempTable = table;
      final int expectedModCount = modCount;
      int point = 0;

      @Override
      public boolean hasNext() {
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
        while (table[point] == null && point < table.length - 1) {
          point++;
        }
        return table[point] != null;
      }

      @Override
      public K next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return tempTable[point++].key;
      }
    };
  }

  private static class MapEntry<K, V> {

    K key;
    V value;

    public MapEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }

}
