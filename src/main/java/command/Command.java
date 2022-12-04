package command;

import command.exception.CommandException;
import usage.cooper.ServerResponse;

public interface Command {
    ServerResponse execute() throws CommandException;
}