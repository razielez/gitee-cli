package com.razielez.gitee.cli.utils;

import org.junit.jupiter.api.Test;

class RuntimeUtilsTest {

  @Test
  void currentBranch() {
    String branch = RuntimeUtils.currentBranch();
    System.out.println(branch);
  }
}