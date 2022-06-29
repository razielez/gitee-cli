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

  @Option(names = "-r", description = "repo")
  private String repo;
  @Option(names = "-t", required = true, description = "title")
  private String title;
  @Option(names = "-h", description = "origin branch, eg: username:branch")
  private String head;
  @Option(names = "-b", description = "base branch")
  private String base;
  @Option(names = "-c", description = "commit content")
  private String content;
  @Option(names = "-i", description = "issue id")
  private String issue;

  public PrCreatePrCommand() {
    super.register();
  }

  @Override
  public Integer call() throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("title", title);
    head = head == null ? defaultBranch() : head;
    params.put("head", cfg.owner() + ":" + head);
    params.put("base", StringUtils.isEmpty(base) ? cfg.baseBranch() : base);
    params.put("access_token", cfg.accessToken());
    params.put("body", content);
    params.put("issue", issue);
    repo = repo == null ? defaultRepo() : repo;
    Result<JsonNode> result = apiClient.post(
        newPullApi(cfg.owner(), repo),
        params,
        JsonNode.class
    );
    if (!result.isOk()) {
      System.out.println(result);
      return -1;
    }
    System.out.println("Pr create success");
    return 0;
  }
}
