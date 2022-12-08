package repositories;

import entities.Booking;
import entities.Movie;
import repositories.exception.RepositoryException;

import java.util.List;

public interface BookingRepository {

    List<Booking> getAll() throws RepositoryException;

    Booking get(int bookingId) throws RepositoryException;

    void add(Booking booking) throws RepositoryException;

    void update(Booking booking) throws RepositoryException;

    void delete(int bookingId) throws RepositoryException;
}
