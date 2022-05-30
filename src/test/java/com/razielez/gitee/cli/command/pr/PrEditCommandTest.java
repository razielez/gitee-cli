package com.razielez.gitee.cli.command.pr;

import com.razielez.gitee.cli.Application;
import org.junit.jupiter.api.Test;

class PrEditCommandTest {

  @Test
  public void edit() {
    Application.main(new String[]{"pr", "edit", "-r", "test-gitee-cli", "-n", "2", "-t", "test edit pr"});
  }

  @Test
  public void closePr() {
    Application.main(new String[]{"pr", "edit", "-r", "test-gitee-cli", "-n", "2", "-s", "closed"});
  }

}