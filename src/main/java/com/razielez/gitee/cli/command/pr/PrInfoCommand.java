package com.razielez.gitee.cli.command.pr;

import com.fasterxml.jackson.databind.JsonNode;
import com.razielez.gitee.cli.utils.Result;
import java.util.HashMap;
import java.util.Map;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "info",
    description = "pr info"
)
public class PrInfoCommand extends AbstractPrCommand {

  public PrInfoCommand() {
    super.register();
  }

  @Option(names = "-r", description = "git repo")
  private String repo;
  @Option(names = "-n", required = true, description = "pr number")
  private Integer number;

  @Override
  public Integer call() throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("access_token", cfg.accessToken());
    repo = repo == null ? defaultRepo() : repo;
    Result<JsonNode> result = apiClient.get(
        newPullApi(cfg.owner(), repo, number),
        params,
        JsonNode.class
    );
    if (!result.isOk()) {
      System.out.println(result);
      return -1;
    }
    printPr(result.data());
    return 0;
  }
}
