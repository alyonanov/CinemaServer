package command.implement.movies;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.exception.ServiceException;
import service.implement.AdminServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.Map;

public class DeleteMovieCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public DeleteMovieCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int movieId = (int) data.get("movieId");
        try {
            service.deleteMovie(movieId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
