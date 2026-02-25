import java.awt.*;
import java.util.Random;

public class VehicleFactory {

    public static Vehicle createVolvo240() {
        return new Volvo240(Color.BLACK);
    }

    public static Vehicle createSaab95() {
        return new Saab95(Color.BLACK);
    }

    public static Vehicle createScania() {
        return new Scania(Color.BLACK);
    }

    public static Vehicle createRandom() {
        Random random = new Random();
        int choice = random.nextInt(3);
        if (choice == 0) {
            return new Volvo240(Color.BLACK);
        }
        else if (choice == 1) {
            return new Saab95(Color.BLACK);
        }
        else {
            return new Scania(Color.BLACK);
        }


    }
}
