package Util;

import Draw.Brick;
import Main.Window;
import java.util.ArrayList;

public class BrickFactory {


    public  ArrayList<Brick> bricks = new ArrayList<>();
    private int cols, rows;
    private double currentHeight, currentWidth;
    public BrickFactory(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        currentWidth = calcWidth(cols);
        currentHeight = 10;

        for(int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                Brick b = new Brick();
                b.setWidth(currentWidth);
                b.setHeight(currentHeight);
                b.setX(i * b.getWidth());
                b.setY(j * b.getHeight());
                bricks.add(b);
            }
        }

    }


    public double calcWidth(int count) {
        return (Window.windowDimension.getWidth()) / (count);
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public double getCurrentHeight() {
        return currentHeight;
    }

    public double getCurrentWidth() {
        return currentWidth;
    }
}
