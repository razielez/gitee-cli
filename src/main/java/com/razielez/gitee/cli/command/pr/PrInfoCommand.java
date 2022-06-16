package com.razielez.gitee.cli.command.pr;

import com.fasterxml.jackson.databind.JsonNode;
import com.razielez.gitee.cli.constants.GiteeConstants;
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
  @Option(names = "-b", required = false, description = "business link")
  private boolean business = true;

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
    System.out.println("Pull Request Url: " + getPrUrl(repo, cfg.owner(), number, business));
    return 0;
  }

  private String getPrUrl(String repo, String owner, int number, boolean business) {
    return business ?
        String.format("%s%s/repos/%s/%s/pulls/%d", GiteeConstants.BUSINESS_DOMAIN, cfg.businessName(), owner, repo, number)
        : String.format("%s%s/%s/pulls/%d", GiteeConstants.DOMAIN, owner, repo, number);
  }
}
