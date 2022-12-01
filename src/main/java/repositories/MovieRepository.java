package repositories;

import entities.Movie;
import repositories.exception.RepositoryException;

import java.util.List;

public interface MovieRepository {

    List<Movie> getAll() throws RepositoryException;

    Movie get(int movieId) throws RepositoryException;

    Movie get(String movieName, String movieGenre, String movieCountry) throws RepositoryException;

    void add(Movie Movie) throws RepositoryException;

    void update(Movie Movie) throws RepositoryException;

    void delete(int movieId) throws RepositoryException;
}