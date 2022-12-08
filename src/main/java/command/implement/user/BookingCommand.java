package command.implement.user;

import command.Command;
import command.exception.CommandException;
import repositories.exception.RepositoryException;
import service.CinemaHallService;
import service.UserService;
import service.exception.ServiceException;
import service.implement.CinemaHallServiceImplement;
import service.implement.UserServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.Map;

public class BookingCommand implements Command {

    private UserService service;
    private ClientRequest request;
    private ServerResponse response;

    public BookingCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int userId = (int) data.get("userId");
        int movieId = (int) data.get("movieId");
        try {
            service.booking(userId, movieId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}