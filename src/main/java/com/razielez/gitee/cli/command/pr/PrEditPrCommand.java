package com.razielez.gitee.cli.command.pr;

import com.fasterxml.jackson.databind.JsonNode;
import com.razielez.gitee.cli.utils.Assert;
import com.razielez.gitee.cli.utils.Result;
import java.util.HashMap;
import java.util.Map;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "edit",
    description = "edit pr"
)
public class PrEditPrCommand extends AbstractPrCommand {

  @Option(names = "-r", required = true, description = "git repo")
  private String repo;
  @Option(names = "-n", required = true, description = "pr number")
  private Integer number;
  @Option(names = "-t", required = false, description = "pr title")
  private String title;
  @Option(names = "-c", required = false, description = "pr content")
  private String content;
  @Option(names = "-s", required = false, description = "pr state")
  private State state;
  @Option(names = "-d", required = false, description = "pr bool draft")
  private Boolean draft;
  public PrEditPrCommand() {
    super.register();
  }

  @Override
  public Integer call() throws Exception {
    Map<String, Object> params = new HashMap<>();
    params.put("access_token", cfg.accessToken());
    params.put("title", title);
    params.put("body", content);
    if (state != null) {
      Assert.isTrue(State.closed.equals(state), "State must be closed");
      params.put("state", State.closed);
    }
    params.put("draft", draft);
    Result<JsonNode> result = apiClient.patch(
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

  enum State {
    open,
    closed
  }

}
