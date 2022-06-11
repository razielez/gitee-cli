package com.razielez.gitee.cli.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class RuntimeUtils {

  public static String currentBranch() {
    try {
      Process process = Runtime.getRuntime().exec("git rev-parse --abbrev-ref HEAD");
      process.waitFor();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      return reader.readLine();
    } catch (IOException | InterruptedException e) {
      return null;
    }
  }
}
