package com.razielez.gitee.cli;

import picocli.CommandLine;

public class Application {

  public static void main(String[] args) {
    if (args.length == 0) {
      return;
    }
    CommandLine commandLine = new CommandLine(new Git());
    int exitCode = commandLine.execute(args);
    System.exit(exitCode);
  }

}
