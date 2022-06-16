package com.razielez.gitee.cli.command.pr;

import com.fasterxml.jackson.databind.JsonNode;
import com.razielez.gitee.cli.constants.GiteeConstants;
import com.razielez.gitee.cli.utils.Result;
import com.razielez.gitee.cli.utils.RuntimeUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
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
  @Option(names = "-n", description = "pr number")
  private Integer number;
  @Option(names = "-b", description = "business link")
  private Boolean business = true;

  @Override
  public Integer call() throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("access_token", cfg.accessToken());
    repo = repo == null ? defaultRepo() : repo;
    final String owner = cfg.owner();
    String branch = RuntimeUtils.currentBranch();
    JsonNode prInfo = null;
    if (number == null) {
      Result<JsonNode> result = apiClient.get(
          newPullApi(owner, repo),
          params,
          JsonNode.class
      );
      if (!result.isOk()) {
        System.out.println(result);
        return -1;
      }
      JsonNode list = result.data();
      for (JsonNode node : list) {
        if (node.get("head").get("ref").asText().equals(branch)) {
          prInfo = node;
          break;
        }
      }
    } else {
      Result<JsonNode> result = apiClient.get(
          newPullApi(owner, repo),
          params,
          JsonNode.class
      );
      if (!result.isOk()) {
        System.out.println(result);
        return -1;
      }
      prInfo = result.data();
    }
    if (prInfo == null) {
      System.out.println("Pr not found!");
      return -1;
    }
    number = prInfo.get("number").asInt();
    printPr(prInfo);
    System.out.println("Pull Request Url: " + getPrUrl(repo, cfg.owner(), number, business));
    return 0;
  }

  private String getPrUrl(String repo, String owner, int number, boolean business) {
    return business ?
        String.format("%s%s/repos/%s/%s/pulls/%d", GiteeConstants.BUSINESS_DOMAIN, cfg.businessName(), owner, repo, number)
        : String.format("%s%s/%s/pulls/%d", GiteeConstants.DOMAIN, owner, repo, number);
  }
}
