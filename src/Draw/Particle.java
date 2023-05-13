package Draw;

import java.awt.*;
import java.util.Random;

public class Particle {

    double x, y;
    int width, height, accX = 0, accY = 0;
    double acceleration = .1;
    double opacity = 100;
    Color color = new Color(24, 124, 200, (int)opacity);
    public boolean natural;
    public Particle(double x, double y, int width, int heigth, boolean natural) {
        Random rd = new Random();
        this.x = x;
        this.y = y;
        this.height = heigth;
        this.width = width;
        while(accX == 0 && accY == 0) {
            accX = rd.nextInt() % 50;
            accY = rd.nextInt() % 50;
        }
        this.natural = natural;
    }

    public void transform() {

        x += .1 * accX;
        y += .1 * accY;

        opacity -= .1;

    }

    public void transformNat() {


        y +=  (.1  * (acceleration+=.1) * (Math.abs(accY) + 1) );


        opacity -= .001;
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

