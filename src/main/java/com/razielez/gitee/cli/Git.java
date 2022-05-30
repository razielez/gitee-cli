package com.razielez.gitee.cli;

import com.razielez.gitee.cli.command.PRCommand;
import picocli.CommandLine.Command;

@Command(
    name = "git",
    subcommands = {
        PRCommand.class,
    }
)
public class Git {

}
