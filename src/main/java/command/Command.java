package command;

import usage.cooperation.ServerResponse;

public interface Command {

    ServerResponse execute() throws CommandException;
}