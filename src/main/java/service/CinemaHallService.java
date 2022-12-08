package service;

import service.exception.ServiceException;
import entities.CinemaHall;

public interface CinemaHallService {

    void addNewCinemaHall(String hallType, int hallSeatsNumber) throws ServiceException;

    void deleteCinemaHall(int hallId) throws ServiceException;

    //void editCinemaHall(int hallId, String hallType, int hallSeatsNumber) throws ServiceException;

    CinemaHall getById(int hallId) throws ServiceException;

}

