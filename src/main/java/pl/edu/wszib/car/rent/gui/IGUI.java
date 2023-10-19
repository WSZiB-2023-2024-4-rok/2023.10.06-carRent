package pl.edu.wszib.car.rent.gui;

import pl.edu.wszib.car.rent.model.User;

public interface IGUI {
    User readLoginData();
    String menu();
    void listCars();
    String readPlate();
}
