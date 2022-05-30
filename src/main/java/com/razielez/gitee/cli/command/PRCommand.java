package com.razielez.gitee.cli.command;

import com.razielez.gitee.cli.command.pr.PrCreatePrCommand;
import com.razielez.gitee.cli.command.pr.PrEditPrCommand;
import com.razielez.gitee.cli.command.pr.PrInfoCommand;
import com.razielez.gitee.cli.command.pr.PrListPrCommand;
import picocli.CommandLine.Command;

@Command(
    name = "pr",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "gitee pr command",
    subcommands = {
        PrListPrCommand.class,
        PrCreatePrCommand.class,
        PrEditPrCommand.class,
        PrInfoCommand.class
    }
)
public class PRCommand {

}
