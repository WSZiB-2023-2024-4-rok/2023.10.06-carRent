package pl.edu.wszib.car.rent.auth;

import pl.edu.wszib.car.rent.model.User;

public interface IAuthenticator {
    void authenticate(User user);
}
