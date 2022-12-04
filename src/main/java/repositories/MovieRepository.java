package repositories;

import entities.Movie;
import repositories.exception.RepositoryException;

import java.util.List;

public interface MovieRepository {

    List<Movie> getAll() throws RepositoryException;

    Movie get(int movieId) throws RepositoryException;

    Movie get(String movieName, String movieGenre, String movieCountry, String movieDuration, int moviePrice) throws RepositoryException;

    void add(Movie movie) throws RepositoryException;

    void update(Movie movie) throws RepositoryException;

    void delete(int movieId) throws RepositoryException;
}