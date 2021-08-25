package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
  private Node<E> firstElement;
  private Node<E> lastElement;

  private int modCount;
  private int size;

  public SimpleLinkedList() {
  }

  @Override
  public void add(E value) {
    Node<E> tempLast = lastElement;
    Node<E> newNode = new Node<>(lastElement, value, null);
    lastElement = newNode;
    if (tempLast == null) {
      firstElement = newNode;
    } else {
      tempLast.nextElement = newNode;
    }
    size++;
  }

  @Override
  public E get(int index) {
    Objects.checkIndex(index, size);
    return searchNode(index).currentElement;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      private Node<E> lastReturn;
      private Node<E> next  = firstElement;
      private int point = 0;
      private int expectedModCount = modCount;

      @Override
      public boolean hasNext() {
        return point < size;
      }

      @Override
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
        lastReturn = next;
        next = next.nextElement;
        point++;
        return lastReturn.currentElement;
      }
    };
  }

  public Node<E> searchNode(int index) {
    Node<E> elem = firstElement;
      for (int i = 0; i < index; i++) {
        elem = elem.nextElement;
      }
    return elem;
  }

  private class Node<E> {

    private E currentElement;
    private Node<E> nextElement;
    private Node<E> previousElement;

    public Node(Node<E> previousElement, E currentElement, Node<E> nextElement) {
      this.currentElement = currentElement;
      this.nextElement = nextElement;
      this.previousElement = previousElement;
    }
  }
}