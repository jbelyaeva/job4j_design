package ru.job4j;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TriggerTest {

  @Test
  public void test() {
    Assert.assertEquals(1, new Trigger().someLogic());
  }

}