package ru.job4j.collection.tree;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class SimpleTreeTest {
  @Test
  public void when6ElFindLastThen6() {
    Tree<Integer> tree = new SimpleTree<>(1);
    tree.add(1, 2);
    tree.add(1, 3);
    tree.add(1, 4);
    tree.add(4, 5);
    tree.add(5, 6);
    assertThat(
        tree.findBy(6).isPresent(),
        is(true)
    );
  }

  @Test
  public void when6ElFindNotExitThenOptionEmpty() {
    Tree<Integer> tree = new SimpleTree<>(1);
    tree.add(1, 2);
    assertThat(
        tree.findBy(7).isPresent(),
        is(false)
    );
  }

  @Test
  public void whenChildExistOnLeafThenNotAdd() {
    Tree<Integer> tree = new SimpleTree<>(1);
    tree.add(1, 2);
    tree.add(1, 3);
    tree.add(1, 4);
    tree.add(4, 5);
    tree.add(5, 6);
    assertFalse(tree.add(5, 6));
  }

  @Test
  public void whenTreeIsBinary() {
    Tree<Integer> tree = new SimpleTree<>(1);
    tree.add(1, 2);
    tree.add(1, 3);
    tree.add(2, 5);
    tree.add(3, 6);
    assertTrue(tree.isBinary());
  }

  @Test
  public void whenTreeIsNotBinary() {
    Tree<Integer> tree = new SimpleTree<>(1);
    tree.add(1, 2);
    tree.add(1, 3);
    tree.add(1, 4);
    tree.add(2, 5);
    tree.add(3, 6);
    assertFalse(tree.isBinary());
  }

}