package command.implement.movies;

import command.Command;
import command.exception.CommandException;
import service.AdminService;
import service.exception.ServiceException;
import service.implement.AdminServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;
import entities.Movie;

import java.util.HashMap;
import java.util.Map;

public class GetMovieByIdCommand implements Command {

    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetMovieByIdCommand(ClientRequest request, ServerResponse response) {
        this.request = request;
        this.response = response;
        this.service = AdminServiceImplement.getInstance();
    }

    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> requestData = request.getData();
        int movieId = (int) requestData.get("movieId");
        try {
            Movie movie = service.getMovieById(movieId);
            Map<String, Object> data = new HashMap<>();
            data.put("movie", movie);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}