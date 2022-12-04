package command.implement.user;

import service.UserService;
import service.implement.UserServiceImplement;
import command.Command;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;
import command.Command;
import command.exception.CommandException;
import service.exception.ServiceException;

import java.util.Map;

public class ChangePasswordCommand implements Command {

    private ClientRequest request;
    private ServerResponse response;
    private UserService service;

    public ChangePasswordCommand(ClientRequest request, ServerResponse response) {
        this.request = request;
        this.response = response;
        this.service = UserServiceImplement.getInstance();
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        String currentPassword = (String) data.get("currentPassword");
        String newPassword = (String) data.get("newPassword");
        try {
            service.changePassword(userId, currentPassword, newPassword);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}