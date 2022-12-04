package command.implement.user;

import command.Command;
import command.exception.CommandException;
import entities.User;
import service.UserService;
import service.exception.ServiceException;
import service.implement.UserServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.HashMap;
import java.util.Map;

public class GetUserCommand implements Command {


    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetUserCommand(ClientRequest request, ServerResponse response) {
        this.request = request;
        this.response = response;
        this.service = UserServiceImplement.getInstance();
    }
    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        try {
            User user = service.getUser(userId);
            Map<String, Object> dataSend = new HashMap<>();
            dataSend.put("user", user);
            response.setData(dataSend);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
