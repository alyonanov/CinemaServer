package command.implement.user;

import command.Command;
import command.exception.CommandException;
import entities.Booking;
import service.UserService;
import service.exception.ServiceException;
import service.implement.UserServiceImplement;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllBookingsCommand implements Command {
    private ClientRequest request;
    private ServerResponse response;
    private UserService service;

    public GetAllBookingsCommand(ClientRequest request, ServerResponse response) {
        this.service = UserServiceImplement.getInstance();
        this.request = request;
        this.response = response;
    }

    @Override
    public ServerResponse execute() throws CommandException {
        try {
            List<Booking> bookings = service.getAllBookings();
            Map<String, Object> data = new HashMap<>();
            data.put("bookings", bookings);
            response.setData(data);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return response;
    }
}
