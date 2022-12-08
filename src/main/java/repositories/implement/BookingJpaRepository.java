package repositories.implement;

import entities.Booking;
import entities.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repositories.BookingRepository;
import repositories.exception.RepositoryException;
import usage.config.AppConfig;

import java.util.List;

public class BookingJpaRepository implements BookingRepository {
    private static final BookingJpaRepository INSTANCE = new BookingJpaRepository();

    public static BookingJpaRepository getInstance() {
        return INSTANCE;
    }

    private BookingJpaRepository() {
    }

    private SessionFactory sessionFactory = AppConfig.getInstance().getSessionFactory();

    @Override
    public List<Booking> getAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Booking", Booking.class)
                    .list();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Booking get(int bookingId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Booking WHERE bookingId = ?1", Booking.class)
                    .setParameter(1, bookingId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(Booking booking) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(booking);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Booking booking) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(booking);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(int bookingId) throws RepositoryException {

    }
}
