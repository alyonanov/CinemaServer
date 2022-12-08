package command.implement.cinemahall;

import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;
import service.CinemaHallService;
import service.exception.ServiceException;
import service.implement.CinemaHallServiceImplement;
import command.Command;
import command.exception.CommandException;
import java.util.Map;

public class DeleteCinemaHallCommand implements Command {

    private ClientRequest request;
    private ServerResponse response;
    private CinemaHallService service;
    public DeleteCinemaHallCommand(ClientRequest request, ServerResponse response) {
        this.service = CinemaHallServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }
    @Override
    public ServerResponse execute() throws CommandException {
        Map<String, Object> data = request.getData();
        int hallId = (int) data.get("hallId");
        try {
            service.deleteCinemaHall(hallId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}





























