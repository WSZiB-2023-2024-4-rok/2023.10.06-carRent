package pl.edu.wszib.car.rent.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.car.rent.exceptions.IncorrectPasswordException;
import pl.edu.wszib.car.rent.exceptions.UserNotFoundException;
import pl.edu.wszib.car.rent.model.User;
import pl.edu.wszib.car.rent.repositories.IUserRepository;
import pl.edu.wszib.car.rent.repositories.UserRepository;

import java.util.Optional;

@Component
public class Authenticator implements IAuthenticator {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void authenticate(User user) {
        Optional<User> userFromDb = this.userRepository.getUserByLogin(user.getLogin());
        if(userFromDb.isEmpty()) {
            throw new UserNotFoundException();
        }

        if(!userFromDb.get().getPassword().equals(user.getPassword())) {
            throw new IncorrectPasswordException();
        }
    }
}
