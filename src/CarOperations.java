public interface CarOperations {
    void gas(int amount);
    void brake(int amount);
    void startEngine();
    void stopEngine();
    void turboOn();
    void turboOff();
    void liftBed(int amount);
    void lowerBed(int amount);
}