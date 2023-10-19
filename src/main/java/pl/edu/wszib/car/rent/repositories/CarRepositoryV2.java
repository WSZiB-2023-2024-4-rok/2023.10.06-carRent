package pl.edu.wszib.car.rent.repositories;

import org.springframework.stereotype.Component;
import pl.edu.wszib.car.rent.exceptions.CarAlreadyRentException;
import pl.edu.wszib.car.rent.exceptions.CarNotFoundException;
import pl.edu.wszib.car.rent.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepositoryV2 implements ICarRepository {
    private final List<Car> cars = new ArrayList<>();

    private static final CarRepositoryV2 instance = new CarRepositoryV2();

    private CarRepositoryV2() {
        this.cars.add(new Car("BMW", "3", 300, "KR111", false));
        this.cars.add(new Car("Audi", "A3", 350, "KR222", false));
        this.cars.add(new Car("Toyota", "Corolla", 200, "KR333", false));
        this.cars.add(new Car("Fiat", "Multipla", 50, "KR444", false));
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

    public static CarRepositoryV2 getInstance() {
        return instance;
    }
}
