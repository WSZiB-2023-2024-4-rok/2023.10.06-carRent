package pl.edu.wszib.car.rent.core;

import org.springframework.stereotype.Component;
import pl.edu.wszib.car.rent.auth.IAuthenticator;
import pl.edu.wszib.car.rent.exceptions.CarAlreadyRentException;
import pl.edu.wszib.car.rent.exceptions.CarNotFoundException;
import pl.edu.wszib.car.rent.exceptions.IncorrectPasswordException;
import pl.edu.wszib.car.rent.exceptions.UserNotFoundException;
import pl.edu.wszib.car.rent.gui.IGUI;
import pl.edu.wszib.car.rent.model.User;
import pl.edu.wszib.car.rent.repositories.ICarRepository;

@Component
public class Core {

    private ICarRepository carRepository;
    private IAuthenticator authenticator;
    private IGUI gui;

    public Core(ICarRepository carRepository, IAuthenticator authenticator, IGUI gui) {
        this.carRepository = carRepository;
        this.authenticator = authenticator;
        this.gui = gui;
    }

    public void start() {
        User user = gui.readLoginData();
        try {
            authenticator.authenticate(user);
        } catch (UserNotFoundException | IncorrectPasswordException e) {
            System.out.println("Incorrect authentication");
            return;
        }

        mainLoop: while(true) {
            switch(gui.menu()) {
                case "1":
                    gui.listCars();
                    break;
                case "2":
                    String plate = gui.readPlate();
                    try {
                        carRepository.rentCar(plate);
                    } catch (CarNotFoundException e) {
                        System.out.println("Car not found");
                    } catch (CarAlreadyRentException e) {
                        System.out.println("Car already rent");
                    }
                    break;
                case "3":
                    break mainLoop;
                default:
                    System.out.println("Incorrect choose !!");
            }
        }
    }
}
