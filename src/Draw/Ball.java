package Draw;

import Util.Directions;
import Main.Window;

import java.util.ArrayList;


public class Ball {

    private double x, y;
    private int width, height;
    public ArrayList<Particle> tail = new ArrayList<>();
    public Directions[] dir = new Directions[2];

    public double accX = 1, accY = 1;
    public Ball() {
        dir[0] = Directions.LEFT;
        dir[1] = Directions.DOWN;

        this.x = Math.random() * (Window.windowDimension.getWidth());
        this.y = 0;
        this.width = 10;
        this.height = 10;
    }

    public void transform(double d) {

        tail.add(new Particle(this.x + (this.width/2), this.y + (this.height / 2), this.width / 10, this.width / 10, true));

        switch (dir[1]) {
            case UP:
                switch (dir[0]){
                    case LEFT:
                        x -= d * accX;
                        y -= d * accY;
                        break;

                    case RIGHT:
                        x += d * accX;
                        y -= d *accY;
                        break;
                }
                break;

            case DOWN:
                switch (dir[0]) {
                    case LEFT:
                        x -= d * accX;
                        y += d * accY;
                        break;
                    case RIGHT:
                        x += d * accX;
                        y += d * accY;
                        break;

                }
                break;

        }
    }


    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
