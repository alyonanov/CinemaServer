package service;

import entities.CinemaHall;
import entities.User;
import entities.Movie;

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

}
