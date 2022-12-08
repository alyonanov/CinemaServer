package service;

import entities.Booking;
import entities.CinemaHall;
import entities.User;
import entities.Movie;

import repositories.exception.RepositoryException;
import service.exception.ServiceException;

import java.util.List;

public interface UserService {

//USERS
    User signIn(String username, String password) throws ServiceException;

    void signUp(String username, String firstName,
                String lastName, String email,
                String password) throws ServiceException;

    User getUser(int userId) throws ServiceException;

    void changePassword(int userId, String currentPassword, String newPassword) throws ServiceException;

//MOVIES
    List<Movie> getAllMovies() throws ServiceException;

    List<CinemaHall> getAllCinemaHalls() throws ServiceException;
    List<Booking> getAllBookings() throws ServiceException;

    void booking(int userId, int movieId) throws ServiceException, RepositoryException;

}
