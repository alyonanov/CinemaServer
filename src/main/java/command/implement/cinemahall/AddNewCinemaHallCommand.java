package command.implement.cinemahall;

import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;
import service.CinemaHallService;
import service.exception.ServiceException;
import service.implement.CinemaHallServiceImplement;
import command.Command;
import command.exception.CommandException;
import java.util.Map;

public class AddNewCinemaHallCommand implements Command {

    private CinemaHallService service;
    private ClientRequest request;
    private ServerResponse response;

    public AddNewCinemaHallCommand(ClientRequest request, ServerResponse response) {
        this.service = CinemaHallServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {

        Map<String, Object> data = request.getData();
        String text = (String) data.get("hallType");
        int hallSeatsNumber = (int) data.get("hallSeatsNumber");
        try {
            service.addNewCinemaHall(text, hallSeatsNumber);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
