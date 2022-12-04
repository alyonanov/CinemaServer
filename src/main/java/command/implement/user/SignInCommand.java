package command.implement.user;

import command.Command;
import command.exception.CommandException;
import entities.User;
import service.UserService;
import service.implement.UserServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import service.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class SignInCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public SignInCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        try {
            User user = service.signIn(username, password);
            Map<String, Object> dataSend = new HashMap<>();
            dataSend.put("user", user);

            response.setData(dataSend);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}