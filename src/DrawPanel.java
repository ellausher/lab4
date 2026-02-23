
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    private final Map<String, BufferedImage> images = new HashMap<>();
    private final Map<String, Point> points = new HashMap<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(0,300);


    // TODO: Make this general for all cars
    void moveit(Vehicle vehicle, int x, int y){

         points.put(vehicle.getModelName(), new Point(x,y));
        }


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            points.put("Volvo240", new Point(0, 0));
            points.put("Saab95", new Point(0,100));
            points.put("Scania", new Point(0,200));

            images.put("Saab95",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            images.put("Volvo240",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            images.put("Scania",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));


        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (String vehicle : images.keySet()) {
            g.drawImage(images.get(vehicle),points.get(vehicle).x, points.get(vehicle).y,null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
