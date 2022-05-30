package com.razielez.gitee.cli.config;

import com.razielez.gitee.cli.constants.GiteeConstants;
import com.razielez.gitee.cli.utils.Assert;
import com.razielez.gitee.cli.utils.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Properties;

public final class GiteeConfig implements GitConfig {

  private String owner;
  private String accessToken;
  private String baseBranch = "master";


  public GiteeConfig() {
    File file = new File(GiteeConstants.DEFAULT_CONFIG_FILE);
    if (!file.exists()) {
      loadEnv();
    } else {
      loadFile(file);
    }
    verify();
  }

  private void loadFile(File file) {
    try {
      Properties properties = new Properties();
      Files.readAllLines(file.toPath());
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      properties.load(bufferedReader);
      this.accessToken = properties.getProperty(GiteeConstants.GITEE_TOKEN);
      if (StringUtils.isNotEmpty(properties.getProperty(GiteeConstants.GITEE_OWNER))) {
        this.owner = properties.getProperty(GiteeConstants.GITEE_OWNER);
      }
      if (StringUtils.isNotEmpty(properties.getProperty(GiteeConstants.GITEE_BASE_BRANCH))) {
        this.baseBranch = properties.getProperty(GiteeConstants.GITEE_BASE_BRANCH);
      }
    } catch (Exception e) {
      throw new RuntimeException("加载配置文件失败: " + file.toString());
    }
  }

  private void loadEnv() {
    this.accessToken = System.getenv(GiteeConstants.GITEE_TOKEN);
    String giteeOwner = System.getenv(GiteeConstants.GITEE_OWNER);
    if (StringUtils.isNotEmpty(giteeOwner)) {
      this.owner = giteeOwner;
    }
    String giteeBaseBranch = System.getenv(GiteeConstants.GITEE_BASE_BRANCH);
    if (StringUtils.isNotEmpty(giteeBaseBranch)) {
      this.baseBranch = giteeBaseBranch;
    }
  }

  private void verify() {
    Assert.notNull(accessToken, "Gitee token not null!");
    Assert.notNull(baseBranch, "Gitee baseBranch not null!");
    Assert.notNull(owner, "Gitee owner not null!");
  }


  @Override
  public String baseBranch() {
    return baseBranch;
  }

  @Override
  public String owner() {
    return owner;
  }

  @Override
  public String accessToken() {
    return accessToken;
  }
}
