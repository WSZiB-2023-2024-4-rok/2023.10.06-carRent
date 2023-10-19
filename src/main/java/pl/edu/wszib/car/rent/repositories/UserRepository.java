package pl.edu.wszib.car.rent.repositories;

import org.springframework.stereotype.Component;
import pl.edu.wszib.car.rent.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements IUserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        this.users.add(new User("mateusz", "mateusz1"));
        this.users.add(new User("janusz", "janusz1"));
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return this.users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
    }
}
