import java.util.Random;

public class Powerup {


    public int x,y, width, height;

    public Powerup() {
        Random rd = new Random();

        x = rd.nextInt(50, 600);
        y = rd.nextInt(100, 300);
        width = 20;
        height = 20;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
