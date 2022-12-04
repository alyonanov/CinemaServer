package repositories;

import entities.UserStatus;
import repositories.exception.RepositoryException;

public interface UserStatusRepository {

    UserStatus get(int userStatusId) throws RepositoryException;
}