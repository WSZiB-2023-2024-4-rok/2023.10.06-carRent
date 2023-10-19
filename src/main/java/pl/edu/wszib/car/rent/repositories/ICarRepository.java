package pl.edu.wszib.car.rent.repositories;

import pl.edu.wszib.car.rent.model.Car;

import java.util.List;

public interface ICarRepository {
    List<Car> getCars();
    void rentCar(String plate);
}
