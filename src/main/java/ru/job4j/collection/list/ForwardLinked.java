package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

  private Node<T> head;
  private Node<T> tail;

  public void add(T value) {
    Node<T> node = new Node<>(value, null);
    if (head == null) {
      head = node;
      return;
    }
    Node<T> tail = head;
    while (tail.next != null) {
      tail = tail.next;
    }
    tail.next = node;
  }

  public T deleteFirst() {
    if (head == null) {
      throw new NoSuchElementException();
    }
    T value = head.value;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      head = head.next;
    }
    return value;
  }

  public void addFirst(T value) {
    Node<T> node = new Node<>(value, null);
    if (head == tail) {
      head = node;
      return;
    }
    tail = head;
    head = node;
    head.next = tail;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      Node<T> node = head;

      @Override
      public boolean hasNext() {
        return node != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        T value = node.value;
        node = node.next;
        return value;
      }
    };
  }

  private static class Node<T> {

    T value;
    Node<T> next;

    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }
}