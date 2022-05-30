package com.razielez.gitee.cli.command.pr;

import com.razielez.gitee.cli.Application;
import org.junit.jupiter.api.Test;

class PrInfoCommandTest {

  @Test
  public void prInfo() {
    Application.main(new String[]{"pr", "info", "-r", "test-gitee-cli", "-n", "1"});
  }

}