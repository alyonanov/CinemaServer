package service;

import entities.User;
import entities.Movie;

import repositories.exception.RepositoryException;
import service.exception.ServiceException;

import java.util.List;

public interface AdminService {
    // MOVIES
    void addMovie(String movieName, String movieGenre,
                   String movieCountry, String movieDuration,
                   int moviePrice) throws ServiceException, RepositoryException;
    void editMovie(int movieId, String movieName,
                    String movieGenre, String movieCountry,
                    String movieDuration, int moviePrice, int hallId) throws ServiceException, RepositoryException;
    void deleteMovie(int movieId) throws ServiceException;

    Movie getMovieById(int id) throws ServiceException;
    //USERS
    List<User> getAllUsers() throws ServiceException;
    void changeUserStatus(int userId, int statusId) throws ServiceException;

}
