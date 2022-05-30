package com.razielez.gitee.cli.utils;

public final class Assert {


  public static void isTrue(boolean exp, String message) {
    if (!exp) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void isTrue(boolean exp) {
    if (!exp) {
      throw new IllegalArgumentException();
    }
  }

  public static void notNull(Object obj, String message) {
    if (obj == null) {
      throw new IllegalArgumentException(message);
    }
  }


}
