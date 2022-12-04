package repositories;

import entities.CinemaHall;
import repositories.exception.RepositoryException;

import java.util.List;

public interface CinemaHallRepository {

    List<CinemaHall> getAll() throws RepositoryException;

    CinemaHall get(int hallId) throws RepositoryException;

    void add(CinemaHall cinemaHall) throws RepositoryException;

    void delete(int id) throws RepositoryException;
    void update(CinemaHall cinemaHall) throws RepositoryException;


}