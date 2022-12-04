package command.implement.cinemahall;

import command.Command;
import command.exception.CommandException;
import entities.CinemaHall;
import service.CinemaHallService;
import service.exception.ServiceException;
import service.implement.CinemaHallServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.HashMap;
import java.util.Map;

public class GetCinemaHallByIdCommand implements Command {

    private CinemaHallService service;
    private ClientRequest request;
    private ServerResponse response;

    public GetCinemaHallByIdCommand(ClientRequest request, ServerResponse response) {
        this.service = CinemaHallServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }
    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> requestData = request.getData();
        int hallId = (int) requestData.get("hall_id");
        try {
            CinemaHall cinemaHall = service.getById(hallId);
            Map<String, Object> data = new HashMap<>();
            data.put("cinemaHall", cinemaHall);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}