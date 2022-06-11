package com.razielez.gitee.cli.utils;

public final class FileUtils {

  public static String currentDir() {
    return System.getProperty("user.dir");
  }

  public static String currentProject() {
    String dir = currentDir();
    if (StringUtils.isEmpty(dir)) {
      return "";
    }
    String[] split = dir.split("/");
    return split[split.length - 1];
  }

}
