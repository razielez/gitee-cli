package com.razielez.gitee.cli;

import picocli.CommandLine;

public class Application {

  public static void main(String[] args) {
    CommandLine commandLine = new CommandLine(new Git());
    int exitCode = commandLine.execute(args);
    System.exit(exitCode);
  }

}
