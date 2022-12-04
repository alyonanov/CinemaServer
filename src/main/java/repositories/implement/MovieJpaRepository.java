package repositories.implement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repositories.MovieRepository;
import repositories.exception.RepositoryException;
import usage.config.AppConfig;
import entities.Movie;

import java.util.List;

public class MovieJpaRepository implements MovieRepository {

    private static final MovieJpaRepository INSTANCE = new MovieJpaRepository();

    public static MovieJpaRepository getInstance() {
        return INSTANCE;
    }

    private MovieJpaRepository() {
    }

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public List<Movie> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Movie", Movie.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Movie get(int movieId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Worker WHERE workerId = ?1", Movie.class)
                    .setParameter(1, movieId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Movie get(String movieName,
                     String movieGenre,
                     String movieCountry,
                     String movieDuration,
                     int moviePrice) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Movie WHERE movieName = ?1 and movieGenre = ?2 and movieCountry = ?3 and movieDuration = ?4 and moviePrice = ?5", Movie.class)
                    .setParameter(1, movieName)
                    .setParameter(2, movieGenre)
                    .setParameter(3, movieCountry)
                    .setParameter(4,movieDuration)
                    .setParameter(5,moviePrice)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(Movie movie) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Movie movie) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(movie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(int movieId) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Movie movie = session.createQuery("FROM Movie WHERE movieId = ?1", Movie.class)
                    .setParameter(1, movieId)
                    .uniqueResult();
            session.delete(movie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }
}