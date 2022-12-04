package repositories.implement;

import entities.CinemaHall;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repositories.CinemaHallRepository;
import repositories.exception.RepositoryException;
import usage.config.AppConfig;

import java.util.List;

public class CinemaHallJpaRepository implements CinemaHallRepository {

    private static final CinemaHallJpaRepository INSTANCE = new CinemaHallJpaRepository();

    public static CinemaHallJpaRepository getInstance() {
        return INSTANCE;
    }

    private CinemaHallJpaRepository() {
    }

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public List<CinemaHall> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM CinemaHall", CinemaHall.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public CinemaHall get(int hallId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM CinemaHall WHERE hallId = ?1", CinemaHall.class)
                    .setParameter(1, hallId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(CinemaHall cinemaHall) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(int id) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CinemaHall cinemaHall = session.createQuery("FROM CinemaHall WHERE hallId = ?1", CinemaHall.class)
                    .setParameter(1, id)
                    .uniqueResult();
            session.delete(cinemaHall);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }
    @Override
    public void update(CinemaHall cinemaHall) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(cinemaHall);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

}