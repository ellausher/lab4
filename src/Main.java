import java.awt.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Volvo240(Color.BLACK));
        vehicles.add(new Saab95(Color.BLACK));
        vehicles.add(new Scania(Color.BLACK));
        Workshop<Volvo240> workshop = new Workshop<>(10);


        int i = 0;
        for (Vehicle vehicle : vehicles) {
            vehicle.setX(0);
            vehicle.setY(i);
            i = i + 100;
        }
        workshop.setX(0);
        workshop.setY(300);

        CarModel model = new CarModel(vehicles, workshop);
        CarController controller = new CarController(model);
        CarView view = new CarView("CarSim 1.0", controller, model);
        model.addObserver(view);
        controller.start();

        }
    }


