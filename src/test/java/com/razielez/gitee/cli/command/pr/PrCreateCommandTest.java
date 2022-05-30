package com.razielez.gitee.cli.command.pr;

import com.razielez.gitee.cli.Application;
import com.razielez.gitee.cli.BaseTest;
import org.junit.jupiter.api.Test;

class PrCreateCommandTest extends BaseTest {

  @Test
  public void testCreatePr() {
    Application.main(new String[]{
        "pr", "create",
        "-t", "test title",
        "-r", "test-gitee-cli",
        "-h", "test2",
    });
  }

}