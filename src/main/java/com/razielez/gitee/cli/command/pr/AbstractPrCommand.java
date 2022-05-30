package com.razielez.gitee.cli.command.pr;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.freva.asciitable.AsciiTable;
import com.razielez.gitee.cli.command.AbstractCommand;
import com.razielez.gitee.cli.utils.Assert;

public abstract class AbstractPrCommand extends AbstractCommand {

  static String[] PR_HEADER = {"Id", "Title", "Body", "Number", "State", "Username", " Ref", "Sha"};

  protected String newPullApi(String owner, String repo) {
    return String.format(
        "v5/repos/%s/%s/pulls",
        owner,
        repo
    );
  }

  protected String newPullApi(String owner, String repo, Integer number) {
    return String.format(
        "v5/repos/%s/%s/pulls/%d",
        owner,
        repo,
        number
    );
  }

  protected void printPr(JsonNode data) {
    Object[] datas = parsePrData(data);
    print(PR_HEADER, null, new Object[][]{datas});
  }

  protected void printPrList(JsonNode dataList) {
    Assert.isTrue(dataList.isArray(), "Date is array");
    Object[][] datas = parsePrDataList(dataList);
    print(PR_HEADER, null, datas);
  }

  protected void print(String[] header, String[] footer, Object[][] datas) {
    // todo: 中文计算有问题
    String table = AsciiTable.getTable(
        header,
        footer,
        datas
    );
    System.out.println(table);
  }

  private Object[][] parsePrDataList(JsonNode data) {
    int size = data.size();
    Object[][] objs = new Object[size][];
    for (int i = 0; i < data.size(); i++) {
      JsonNode node = data.get(i);
      objs[i] = parsePrData(node);
    }
    return objs;
  }

  private Object[] parsePrData(JsonNode node) {
    return new Object[]{
        node.get("id").asText(),
        node.get("title").asText().trim(),
        node.get("body").asText(),
        node.get("number").asText(),
        node.get("state").asText().trim(),
        node.get("user").get("login").asText().trim(),
        node.get("base").get("ref").asText(),
        node.get("base").get("sha").asText().trim()
    };
  }

}
