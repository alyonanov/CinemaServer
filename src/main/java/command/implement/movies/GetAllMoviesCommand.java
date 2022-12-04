package command.implement.movies;

import command.Command;
import command.exception.CommandException;
import entities.Movie;
import service.UserService;
import service.exception.ServiceException;
import service.implement.UserServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GetAllMoviesCommand implements Command {

    private ClientRequest request;
    private ServerResponse response;
    private UserService service;
    public GetAllMoviesCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<Movie> movies = service.getAllMovies();
            Map<String, Object> data = new HashMap<>();
            data.put("movies", movies);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
