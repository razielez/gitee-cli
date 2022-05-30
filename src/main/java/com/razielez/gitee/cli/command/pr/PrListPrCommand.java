package com.razielez.gitee.cli.command.pr;

import com.fasterxml.jackson.databind.JsonNode;
import com.razielez.gitee.cli.utils.Result;
import java.util.HashMap;
import java.util.Map;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "list",
    mixinStandardHelpOptions = true,
    description = "pr list"
)
public class PrListPrCommand extends AbstractPrCommand {

  @Option(names = "-r", required = true, description = "repo")
  private String repo;

  public PrListPrCommand() {
    super.register();
  }

  @Override
  public Integer call() throws Exception {
    final String token = cfg.accessToken();
    final String owner = cfg.owner();
    Map<String, Object> params = new HashMap<>();
    params.put("access_token", token);
    Result<JsonNode> result = apiClient.get(
        newPullApi(owner, repo),
        params,
        JsonNode.class

    );
    if (!result.isOk()) {
      System.err.println(result);
      return -1;
    }
    printPrList(result.data());
    return 0;
  }


}
