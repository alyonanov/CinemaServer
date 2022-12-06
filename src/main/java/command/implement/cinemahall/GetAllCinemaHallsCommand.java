package command.implement.cinemahall;

import entities.CinemaHall;
import service.implement.UserServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;
import service.UserService;
import service.exception.ServiceException;
import command.Command;
import command.exception.CommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllCinemaHallsCommand implements Command {

    private ClientRequest request;
    private ServerResponse response;
    private UserService service;
    public GetAllCinemaHallsCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<CinemaHall> cinemaHalls = service.getAllCinemaHalls();
            Map<String, Object> data = new HashMap<>();
            data.put("cinemaHalls", cinemaHalls);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}