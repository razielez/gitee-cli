package com.razielez.gitee.cli.command;

import com.razielez.gitee.cli.config.GitConfig;
import com.razielez.gitee.cli.config.GiteeConfig;
import com.razielez.gitee.cli.utils.ApiClient;

public abstract class AbstractCommand implements Command {

  protected GitConfig cfg;
  protected ApiClient apiClient;

  @Override
  public void register() {
    initDefault();
  }

  private void initDefault() {
    cfg = new GiteeConfig();
    apiClient = new ApiClient();
  }

}
