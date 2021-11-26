package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

  private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

  public static void main(String[] args) {
    int var1 = 1000;
    long var2 = 1000;
    float var3 = 2.3211232f;
    double var4 = 2.678;
    char var5 = 'h';
    short var6 = 3;
    byte var7 = 6;
    boolean var8 = true;
    LOG.debug(
        "Vars of types - int : {}, long : {}, float : {}, double : {}, char : {}, short : {}, byte : {}, boolean : {}",
        var1, var2, var3, var4, var5, var6, var7, var8);
  }
}
