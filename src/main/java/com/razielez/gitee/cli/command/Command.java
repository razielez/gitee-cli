package com.razielez.gitee.cli.command;

import java.util.concurrent.Callable;

public interface Command extends Callable<Integer> {

  void register() throws Exception;

  Integer call() throws Exception;

}
