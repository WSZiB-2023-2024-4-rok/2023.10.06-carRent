package pl.edu.wszib.car.rent.repositories;

import pl.edu.wszib.car.rent.model.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> getUserByLogin(String login);
}
