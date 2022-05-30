package com.razielez.gitee.cli.command.pr;

import com.razielez.gitee.cli.Application;
import com.razielez.gitee.cli.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrListQueryCommandTest extends BaseTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  public void testPrList() throws Exception {
    Application.main(new String[]{"pr", "list", "-r", "test-gitee-cli"});
  }

}