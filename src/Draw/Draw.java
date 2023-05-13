package Draw;

import Util.*;
import Main.Window;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;


public class Draw extends JPanel {


    //Draw.Platform
    public static Platform platform = new Platform();
    //Bricks

    //Draw.Ball
    public static Ball ball = new Ball();
    //Util.CollisionDetection
    public static CollisionDetection collisionDetection = new CollisionDetection(platform, ball);
    public static BrickFactory bf = new BrickFactory(10, 10);

    public static ArrayList<Particle> particles = new ArrayList<>();
    public static ArrayList<Bounce> bounce = new ArrayList<>();
    public static ArrayList<Powerup> powerups = new ArrayList<>();
    public static ArrayList<Ball> ballHelper = new ArrayList<>();

    public Draw() {

        this.setLayout(null);
        this.setSize(Window.windowDimension);
        this.setBackground(Color.BLACK);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyHandler());
        powerups.add(new Powerup());
        ball.setY(bf.getRows() * bf.getCurrentHeight());
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Iterator<Brick> brickIterator = bf.bricks.iterator();
        Iterator<Particle> particleIterator = particles.iterator();
        Iterator<Bounce> bounceIterator = bounce.iterator();
        Iterator<Powerup> powerupIterator = powerups.iterator();
        Iterator<Particle> tailIterator = ball.tail.iterator();

        //Draw.Draw Draw.Platform
        g.setColor(Color.red);
        g.fillRect((int) platform.getX(), (int) platform.getY(), platform.getWidth(), platform.getHeight());


        //Draw.Draw Draw.Ball
        g.setColor(Color.WHITE);
        g.fillOval((int) ball.getX(), (int)ball.getY(), ball.getWidth(), ball.getHeight());

        while(tailIterator.hasNext()) {
            Particle tmp = tailIterator.next();
            if(tmp.opacity <= 0) {
                tailIterator.remove();
            }
            g.setColor(new Color(255, 255, 255,(int) tmp.opacity));
            g.fillRect((int) tmp.getX(), (int) tmp.getY(), tmp.getWidth(), tmp.getHeight());
            tmp.transformTail();
        }

        //CollisionAnimations

        while(bounceIterator.hasNext()) {
            Bounce tmp = bounceIterator.next();
            g.setColor(new Color(255, 255, 255, tmp.opacity));
            if(tmp.opacity <= 0) {
                bounceIterator.remove();
            }
            g.fillOval((int) tmp.getX(), (int) tmp.getY(), tmp.getWidth(), tmp.getHeight());
            tmp.transform();
        }



        while (particleIterator.hasNext()) {
            Particle tmp = particleIterator.next();
            g.setColor(new Color(200, 10, 200, (int)tmp.opacity));
            if(tmp.opacity <= 0) {
                particleIterator.remove();
            }
            g.fillRect((int)tmp.getX(), (int) tmp.getY(), tmp.getWidth(), tmp.getHeight());
            if(tmp.natural) {
                tmp.transformNat();
            } else {
                tmp.transform();
            }

        }

        //Draw.Draw Bricks
        g.setColor(Color.blue);
        while(brickIterator.hasNext()) {
            Brick tmp = brickIterator.next();
            g.fillRect((int)tmp.getX(),(int) tmp.getY(),(int) tmp.getWidth(),(int) tmp.getHeight());

        }

        g.setColor(Color.pink);
        while(powerupIterator.hasNext()) {
            Powerup tmp = powerupIterator.next();
            g.fillOval(tmp.getX(), tmp.getY(), 20, 20);
        }

        ball.transform(.5);
        platform.transform(1);
        collisionDetection.check();
    }

    public class KeyHandler implements KeyListener {

        public KeyHandler() {

        }
        @Override
        public void keyTyped(KeyEvent e) {


        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case VK_RIGHT:
                    platform.dir = Directions.RIGHT;
                    platform.accX += 2;
                    break;

                case VK_LEFT:
                    platform.dir = Directions.LEFT;
                    platform.accX += 2;
                    break;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
