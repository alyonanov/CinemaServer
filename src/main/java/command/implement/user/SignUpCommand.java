package command.implement.user;

import command.Command;
import command.exception.CommandException;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;
import service.UserService;
import service.implement.UserServiceImplement;
import service.exception.ServiceException;

import java.util.Map;

public class SignUpCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public SignUpCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        String username = (String) data.get("username");
        String firstName = (String) data.get("firstName");
        String lastName = (String) data.get("lastName");
        String email = (String) data.get("email");
        String password = (String) data.get("password");

        try {
            service.signUp(username, firstName, lastName, email, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}