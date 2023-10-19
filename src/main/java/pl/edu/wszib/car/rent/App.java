package pl.edu.wszib.car.rent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.wszib.car.rent.configuration.AppConfiguration;
import pl.edu.wszib.car.rent.core.Core;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        Core core = context.getBean(Core.class);
        core.start();
    }
}
