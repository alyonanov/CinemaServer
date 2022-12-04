package command.works;

import command.Command;
import command.implement.user.*;
import command.implement.cinemahall.*;
import command.implement.movies.*;
import usage.cooper.ClientRequest;
import usage.cooper.ServerResponse;

public class CommandWorks {

    private static final CommandWorks INSTANCE = new CommandWorks();

    public static CommandWorks getInstance() {
        return INSTANCE;
    }

    private CommandWorks() {
    }
    public Command createCommand(String name, ClientRequest request, ServerResponse response) {

        System.out.println("Команда: " + name);

        switch (name) {
            //USER
            case "signIn":
                return new SignInCommand(request, response);
            case "signUp":
                return new SignUpCommand(request, response);
            case "getUser":
                return new GetUserCommand(request, response);
            case "changePassword":
                return new ChangePasswordCommand(request, response);
            case "getAllUsers":
                return new GetAllUsersCommand(request, response);
            case "changeUserStatus":
                return new ChangeUserStatusCommand(request, response);

            //MOVIE
            case "getAllMovies":
                return new GetAllMoviesCommand(request, response);
            case "addMovie":
                return new AddMovieCommand(request, response);
            case "deleteMovie":
                return new DeleteMovieCommand(request, response);
            case "editMovie":
                return new EditMovieCommand(request, response);
            case "getMovieById":
                return new GetMovieByIdCommand(request, response);

            //CINEMA HALL
            case "getAllCinemaHalls":
                return new GetAllCinemaHallsCommand(request, response);
            case "addCinemaHall":
                return new AddNewCinemaHallCommand(request, response);
            case "deleteCinemaHall":
                return new DeleteCinemaHallCommand(request, response);
            case "getCinemaHallById":
                return new GetCinemaHallByIdCommand(request, response);

        }
        throw new RuntimeException("Ошибка команды");
    }
}