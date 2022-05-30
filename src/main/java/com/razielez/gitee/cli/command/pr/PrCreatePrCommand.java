package com.razielez.gitee.cli.command.pr;

import com.fasterxml.jackson.databind.JsonNode;
import com.razielez.gitee.cli.utils.Result;
import com.razielez.gitee.cli.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "create",
    mixinStandardHelpOptions = true,
    description = "pr create"
)
public class PrCreatePrCommand extends AbstractPrCommand {

  @Option(names = "-r", required = true, description = "repo")
  private String repo;
  @Option(names = "-t", required = true, description = "title")
  private String title;
  @Option(names = "-h", required = true, description = "origin branch, eg: username:branch")
  private String head;
  @Option(names = "-b", required = false, description = "base branch")
  private String base;
  @Option(names = "-c", required = false, description = "commit content")
  private String content;
  @Option(names = "-i", required = false, description = "issue id")
  private String issue;

  public PrCreatePrCommand() {
    super.register();
  }

  @Override
  public Integer call() throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("title", title);
    params.put("head", cfg.owner() + ":" + head);
    params.put("base", StringUtils.isEmpty(base) ? cfg.baseBranch() : base);
    params.put("access_token", cfg.accessToken());
    params.put("body", content);
    params.put("issue", issue);
    Result<JsonNode> result = apiClient.post(
        newPullApi(cfg.owner(), repo),
        params,
        JsonNode.class
    );
    if (!result.isOk()) {
      System.out.println(result);
      return -1;
    }
    return 0;
  }
}
