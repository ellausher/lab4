import java.util.ArrayList;

public class Workshop<Model extends Car> implements CanLoad<Model>{

    private final ArrayList<Model> loadedCars = new ArrayList<>();
    private final int maxCapacity;
    private double x;
    private double y;
    public Workshop(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void LoadCar(Model car) {
        if (maxCapacity > loadedCars.size()) {
            loadedCars.add(car);
            car.stopEngine();
            car.loaded();
        }
    }

    @Override
    public Model DropCar() {
        Model car = loadedCars.getLast();
        loadedCars.removeLast();
        car.unload();
        return car;
    }

    public double getX() {
        return x;
    }

    protected void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    protected void setY(double y) {
        this.y = y;
    }

    @Override
    public double getLoaded_capacity() {
        return loadedCars.size();
    }
}
