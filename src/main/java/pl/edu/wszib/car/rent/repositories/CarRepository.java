package pl.edu.wszib.car.rent.repositories;

import org.springframework.stereotype.Component;
import pl.edu.wszib.car.rent.exceptions.CarAlreadyRentException;
import pl.edu.wszib.car.rent.exceptions.CarNotFoundException;
import pl.edu.wszib.car.rent.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarRepository implements ICarRepository {
    private final List<Car> cars = new ArrayList<>();

    public CarRepository() {
        this.cars.add(new Car("BMW", "3", 300, "KR11", false));
        this.cars.add(new Car("Audi", "A3", 350, "KR22", false));
        this.cars.add(new Car("Toyota", "Corolla", 200, "KR33", false));
        this.cars.add(new Car("Fiat", "Multipla", 50, "KR44", false));
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public void rentCar(String plate) {
        Optional<Car> car = this.cars.stream()
                .filter(c -> c.getPlate().equals(plate))
                .findFirst();

        if(car.isEmpty()) {
            throw new CarNotFoundException();
        }
        if(car.get().isRent()) {
            throw new CarAlreadyRentException();
        }
        car.get().setRent(true);
    }
}
