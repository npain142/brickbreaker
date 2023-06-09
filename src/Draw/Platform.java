package Draw;


import Util.Directions;
import Main.Window;

public class Platform {

    public double x, y, accX, accY;
    public Directions dir;
    public int width = 100, height = 10;

    public Platform() {

        x = ((double) (Window.windowDimension.width / 2) - ((double) Window.insets.get("HORIZONTAL") / 2) - ((double)this.width / 2));
        y = ((Window.windowDimension.height) - (Window.insets.get("VERTICAL")) - (this.height));
        accX = 1;
        accY = 1;

        dir = Directions.DEFAULT;
    }


    public void transform(double p) {

        switch (dir) {
            case LEFT:
                x -= p * accX;
                break;

            case RIGHT:
                x += p * accX;
                break;

        }

        if(accX > 1 || accY > 1) {
            accX -= .1;
            accY -= .1;
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

    public int getHeight(){
        return height;
    }

}
