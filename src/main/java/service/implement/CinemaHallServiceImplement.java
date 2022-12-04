package service.implement;

import entities.CinemaHall;
import repositories.CinemaHallRepository;
import repositories.exception.RepositoryException;
import repositories.implement.CinemaHallJpaRepository;
import service.CinemaHallService;
import service.exception.ServiceException;

import java.util.Objects;

public class CinemaHallServiceImplement implements CinemaHallService {

    private static final CinemaHallServiceImplement INSTANCE = new CinemaHallServiceImplement();

    public static CinemaHallServiceImplement getInstance() {
        return INSTANCE;
    }

    private CinemaHallServiceImplement() {
    }

    private final CinemaHallRepository cinemaHallRepository = CinemaHallJpaRepository.getInstance();


    @Override
    public void addNewCinemaHall(String hallType,  int hallSeatsNumber) throws ServiceException {
        if (Objects.nonNull(hallType) && !hallType.isEmpty()) {
            CinemaHall cinemaHall = new CinemaHall();
            cinemaHall.setHallType(hallType);

            try {
                cinemaHallRepository.add(cinemaHall);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        } else {
            throw new ServiceException("Тип зала неверно введён");
        }
    }

    @Override
    public void deleteCinemaHall(int hallId) throws ServiceException {
        try {
            cinemaHallRepository.delete(hallId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void editCinemaHall(int hallId, String hallType, int hallSeatsNumber) throws ServiceException {

        if (Objects.nonNull(hallType) && !hallType.isEmpty()) {

            try {
                CinemaHall cinemaHall = cinemaHallRepository.get(hallId);
                cinemaHall.setHallType(hallType);
                try {
                    cinemaHallRepository.update(cinemaHall);
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
            } catch (RepositoryException ex) {
                throw new ServiceException("Зал не найден");
            }

        } else {
            throw new ServiceException("Неверно введен тип зала");
        }
    }

    @Override
    public CinemaHall getById(int hallId) throws ServiceException {
        try {
            return cinemaHallRepository.get(hallId);
        } catch (RepositoryException e) {
            throw new ServiceException("Зал не найден");
        }
    }

}
