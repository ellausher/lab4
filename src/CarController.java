import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController implements CarOperations {

    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    // The frame that represents this instance View of the MVC pattern
    private ViewInterface frame;
    // A list of cars, modify if needed
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final Workshop<Volvo240> volvoWorkshop = new Workshop<Volvo240>(10);

    //methods:
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.vehicles.add(new Volvo240(Color.BLACK));
        cc.vehicles.add(new Saab95(Color.BLACK));
        cc.vehicles.add(new Scania(Color.BLACK));
        cc.volvoWorkshop.setX(0);
        cc.volvoWorkshop.setY(300);
        int i = 0;
        for (Vehicle vehicle : cc.vehicles) {
            vehicle.setX(0);
            vehicle.setY(i);
            i = i + 100;
        }
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        // Start the timer
        cc.timer.start();
    }

    /*
     * Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                frame.updateView(vehicle, (int) vehicle.getX(), (int) vehicle.getY());
                try{
                    double tolerance = 5; //
                    if (Math.abs(vehicle.getX() - volvoWorkshop.getX()) < tolerance &&
                            Math.abs(vehicle.getY() - volvoWorkshop.getY()) < tolerance){
                        volvoWorkshop.LoadCar((Volvo240) vehicle);
                    }
                } catch (Exception ex) {
                }
                if (x > 800 || x < 0 || y > 500 || y < 0) {
                    vehicle.turnLeft();
                    vehicle.turnLeft();
                    vehicle.move(); // flytta bort från "danger zone"
                    vehicle.stopEngine();
                    vehicle.startEngine();
                }
            }
        }
    }

    // Calls the gas method for each car once
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

    // koppla på detta till amount knappen
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