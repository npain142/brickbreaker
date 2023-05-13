import java.awt.*;

public class Bounce {


    double x, y;
    int accX = 0, accY = 0, width, height;
    int opacity = 100;
    Color color = new Color(24, 124, 200, opacity);
    public Bounce(double x, double y, int width, int height) {

        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void transform() {

            width += 1;
            height += 1;
            x -= .5;
            y -= .5;


            opacity -= 1;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
