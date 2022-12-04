package command.implement.user;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.exception.ServiceException;
import service.implement.AdminServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.Map;

public class ChangeUserStatusCommand implements Command {

    private ClientRequest request;
    private ServerResponse response;
    private AdminService service;

    public ChangeUserStatusCommand(ClientRequest request, ServerResponse response) {
        this.request = request;
        this.response = response;
        this.service = AdminServiceImplement.getInstance();
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        int statusId = (int) data.get("statusId");
        try {
            service.changeUserStatus(userId, statusId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}