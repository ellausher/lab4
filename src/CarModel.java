import java.util.ArrayList;
import java.util.List;

public class CarModel {
    private final List<ViewInterface> observers = new ArrayList<>();
    private double x;
    private double y;

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
    public void addObserver(ViewInterface observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ViewInterface observer : observers) {
            observer.updateView();
        }
    }

    private ArrayList<Vehicle> vehicles = new ArrayList<>();



    public Workshop<Volvo240> getVolvoWorkshop() {
        return volvoWorkshop;
    }

    private final Workshop<Volvo240> volvoWorkshop;

    public CarModel(ArrayList<Vehicle> vehicles, Workshop<Volvo240> volvoWorkshop) {
        this.vehicles = vehicles;
        this.volvoWorkshop = volvoWorkshop;
    }

    public void update(){
        for (Vehicle vehicle : vehicles){
            vehicle.move();
            // krocka
            if (vehicle.getX() > 800 || vehicle.getX() < 0 || vehicle.getY() > 500 || vehicle.getY() < 0) {
                vehicle.turnLeft();
                vehicle.turnLeft();
                vehicle.move(); // flytta bort från "danger zone"
                vehicle.stopEngine();
                vehicle.startEngine();


        }   // workshop krock
            try{
                double tolerance = 5; //
                if (Math.abs(vehicle.getX() - volvoWorkshop.getX()) < tolerance &&
                        Math.abs(vehicle.getY() - volvoWorkshop.getY()) < tolerance){
                    volvoWorkshop.LoadCar((Volvo240) vehicle);
                }
            } catch (Exception ex) {
            }
            notifyObservers();
    }}

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    public void startEngine() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    public void stopEngine() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    public void brake(int amount) {
        for (Vehicle vehicle : vehicles) {
            vehicle.brake((double) amount / 100);
        }
    }

    public void turboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof hasTurbo) {
                ((hasTurbo) vehicle).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Vehicle car : vehicles) {
            if (car instanceof hasTurbo) {
                ((hasTurbo) car).setTurboOff();
            }
        }
    }

    public void liftBed(int amount) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof TipAble)
                ((TipAble) vehicle).tip(amount);
        }
    }

    public void lowerBed(int amount) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof TipAble)
                ((TipAble) vehicle).tip(-amount);
        }
    }
}
