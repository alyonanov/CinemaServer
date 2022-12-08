package command.implement.movies;

import command.Command;
import command.exception.CommandException;
import repositories.exception.RepositoryException;
import service.AdminService;
import service.UserService;
import service.exception.ServiceException;
import service.implement.AdminServiceImplement;
import service.implement.UserServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.Map;

public class EditMovieCommand implements Command {

    private UserService userService;
    private AdminService service;
    private ClientRequest request;
    private ServerResponse response;

    public EditMovieCommand(ClientRequest request, ServerResponse response) {
        this.service = AdminServiceImplement.getInstance();
        this.userService = UserServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }
    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();

        int movieId = (int) data.get("movieId");
        String movieName = (String) data.get("movieName");
        String movieGenre = (String) data.get("movieGenre");
        String movieCountry = (String) data.get("movieCountry");
        String movieDuration = (String) data.get("movieDuration");
        int moviePrice = (int) data.get("moviePrice");
        int cinemaHallId = (int) data.get("cinemaHallId");

        try {
            service.editMovie(movieId, movieName, movieGenre, movieCountry, movieDuration,
                    moviePrice, cinemaHallId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}