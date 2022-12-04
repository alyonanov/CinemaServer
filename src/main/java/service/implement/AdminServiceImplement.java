package service.implement;

import entities.*;
import repositories.*;
import repositories.exception.RepositoryException;
import repositories.implement.*;
import service.AdminService;
import service.exception.ServiceException;

import java.util.List;
import java.util.Objects;

public class AdminServiceImplement implements AdminService {

    private static final AdminServiceImplement INSTANCE = new AdminServiceImplement();

    public static AdminServiceImplement getInstance() {
        return INSTANCE;
    }

    private AdminServiceImplement() {
    }

    private final MovieRepository movieRepository = MovieJpaRepository.getInstance();
    private final CinemaHallRepository cinemaHallRepository = CinemaHallJpaRepository.getInstance();
    private final UserRepository userRepository = UserJpaRepository.getInstance();
    private final UserStatusRepository userStatusRepository = UserStatusJpaRepository.getInstance();

    @Override
    public void addMovie(String movieName,
                         String movieGenre,
                         String movieCountry,
                         String movieDuration,
                         int moviePrice) throws ServiceException, RepositoryException {
        Movie existingMovie = movieRepository.get(movieName, movieGenre, movieCountry, movieDuration, moviePrice);
        if (existingMovie == null) {
            Movie movie = new Movie();
            movie.setMovieName(movieName);
            movie.setMovieGenre(movieGenre);
            movie.setMovieCountry(movieCountry);
            movie.setMovieDuration(movieDuration);
            movie.setMoviePrice(moviePrice);
            movieRepository.add(movie);
        } else {
            throw new ServiceException("Фильм с таким названием уже существует..");
        }
    }
    @Override
    public void editMovie(int movieId,
                          String movieName,
                           String movieGenre,
                           String movieCountry,
                           String movieDuration,
                           int moviePrice,
                           int hallId) throws ServiceException, RepositoryException {

            Movie existingMovie = movieRepository.get(movieName, movieGenre, movieCountry, movieDuration, moviePrice);
            if (Objects.nonNull(existingMovie) && existingMovie.getMovieId() == movieId) {
                CinemaHall cinemahall = cinemaHallRepository.get(hallId);
                Movie movie = movieRepository.get(movieId);

                movie.setMovieName(movieName);
                movie.setMovieGenre(movieGenre);
                movie.setMovieCountry(movieCountry);
                movie.setMovieDuration(movieDuration);
                movie.setMoviePrice(moviePrice);

                movieRepository.update(movie);
            } else {
                throw new ServiceException("Такой фильм уже существует");
        }
    }

    @Override
    public void deleteMovie(int movieId) throws ServiceException {
        try {
            movieRepository.delete(movieId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Movie getMovieById(int id) throws ServiceException {
        try {
            return movieRepository.get(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void changeUserStatus(int userId, int statusId) throws ServiceException {
        try {
            User user = userRepository.get(userId);
            UserStatus userStatus = userStatusRepository.get(statusId);
            user.setUserStatus(userStatus);
            userRepository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}