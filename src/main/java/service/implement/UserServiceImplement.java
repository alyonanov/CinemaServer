package service.implement;

import entities.*;
import repositories.*;
import repositories.exception.RepositoryException;
import repositories.implement.*;
import service.UserService;
import service.exception.ServiceException;

import java.util.List;

public class UserServiceImplement implements UserService {

    private static final UserServiceImplement INSTANCE = new UserServiceImplement();

    public static UserServiceImplement getInstance() {
        return INSTANCE;
    }

    private UserServiceImplement() {
    }

    private final UserRepository userRepository = UserJpaRepository.getInstance();
    private final MovieRepository movieRepository = MovieJpaRepository.getInstance();
    private final CinemaHallRepository cinemaHallRepository = CinemaHallJpaRepository.getInstance();

    @Override
    public User signIn(String username, String password) throws ServiceException {

        try {
            User user = userRepository.get(username, password);
            if (user != null) {
                return user;
            } else {
                throw new ServiceException("Неверный логин или пароль..");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signUp(String username, String firstName, String lastName,
                       String email, String password) throws ServiceException {
        try {
            User existingUserByUsername = userRepository.getByUsername(username);
            User existingUserByEmail = userRepository.getByEmail(email);
            if (existingUserByUsername == null && existingUserByEmail == null) {
                User user = new User(username, firstName, lastName,
                        new UserStatus(2, "Клиент"),
                        email, password);
                userRepository.add(user);
                userRepository.getByUsername(username);

            } else {
                throw new ServiceException("Такой клиент уже зарегестрирован");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUser(int userId) throws ServiceException {
        try {
            return userRepository.get(userId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public void changePassword(int userId, String currentPassword, String newPassword) throws ServiceException {
        try {
            User user = userRepository.get(userId);
            if (currentPassword.equals(user.getPassword())) {
                user.setPassword(newPassword);
                userRepository.update(user);
            } else {
                throw new ServiceException("Неверный пароль..");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Movie> getAllMovies() throws ServiceException {
        try {
            return movieRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<CinemaHall> getAllCinemaHalls() throws ServiceException {
        try {
            return cinemaHallRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


}
