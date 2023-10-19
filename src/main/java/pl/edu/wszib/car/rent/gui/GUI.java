package pl.edu.wszib.car.rent.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.car.rent.model.Car;
import pl.edu.wszib.car.rent.model.User;
import pl.edu.wszib.car.rent.repositories.CarRepository;
import pl.edu.wszib.car.rent.repositories.CarRepositoryV2;
import pl.edu.wszib.car.rent.repositories.ICarRepository;

import java.util.Scanner;

@Component
public class GUI implements IGUI {

    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private ICarRepository carRepository;

    @Override
    public User readLoginData() {
        System.out.println("Login:");
        String login = this.scanner.nextLine();
        System.out.println("Password:");
        String password = this.scanner.nextLine();
        return new User(login, password);
    }

    @Override
    public String menu() {
        System.out.println("""
                1. List cars
                2. Rent car
                3. Exit
                """);
        return this.scanner.nextLine();
    }

    @Override
    public void listCars() {
        for(Car car : this.carRepository.getCars()) {
            System.out.println(new StringBuilder().append(car.getBrand())
                    .append(" ")
                    .append(car.getModel())
                    .append(" Plate: ")
                    .append(car.getPlate())
                    .append(" Price: ")
                    .append(car.getPrice())
                    .append(" ")
                    .append(car.isRent() ? "Not available" : "Available"));
        }
    }

    @Override
    public String readPlate() {
        System.out.println("Plate:");
        return this.scanner.nextLine();
    }
}
