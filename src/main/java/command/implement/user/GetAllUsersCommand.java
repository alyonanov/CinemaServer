package command.implement.user;

import service.AdminService;
import service.exception.ServiceException;
import command.Command;
import command.exception.CommandException;
import service.implement.AdminServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;
import entities.User;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class GetAllUsersCommand implements Command {

    private ClientRequest request;
    private ServerResponse response;
    private AdminService service;


    public GetAllUsersCommand(ClientRequest request, ServerResponse response) {
        this.request = request;
        this.response = response;
        this.service = AdminServiceImplement.getInstance();
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<User> users = service.getAllUsers();
            Map<String, Object> data = new HashMap<>();
            data.put("users", users);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
