import jdk.swing.interop.SwingInterOpUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;


public class Draw extends JPanel {


    //Platform
    public static Platform platform = new Platform();
    //Bricks

    //Ball
    public static Ball ball = new Ball();
    //CollisionDetection
    static CollisionDetection collisionDetection = new CollisionDetection(platform, ball);
    static BrickFactory bf = new BrickFactory(10, 10);

    static ArrayList<Particle> particles = new ArrayList<>();
    static ArrayList<Bounce> bounce = new ArrayList<>();
    static ArrayList<Powerup> powerups = new ArrayList<>();
    final BufferedImage platformImage = ImageIO.read(new File("D:\\Studium\\sommersemester23\\info-2\\training\\gui\\assets\\platform.png"));
    final BufferedImage brickImage = ImageIO.read(new File("D:\\Studium\\sommersemester23\\info-2\\training\\gui\\assets\\brick.png"));
    public Draw() throws IOException {

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

        //Draw Platform
        g.setColor(Color.red);
        //g.fillRect((int) platform.getX(), (int) platform.getY(), platform.getWidth(), platform.getHeight());
        g.drawImage(platformImage, (int)platform.getX(), (int) platform.getY(),platform.getWidth(), platform.getHeight(), null);

        //Draw Ball
        g.setColor(Color.WHITE);
        g.fillOval((int) ball.getX(), (int)ball.getY(), ball.getWidth(), ball.getHeight());

        //COLLISIONANIMATIONS

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

        //Draw Bricks
        g.setColor(Color.blue);
        while(brickIterator.hasNext()) {
            Brick tmp = brickIterator.next();
            g.fillRect((int)tmp.getX(),(int) tmp.getY(),(int) tmp.getWidth(),(int) tmp.getHeight());
            //g.drawImage(brickImage,(int) tmp.getX(), (int) tmp.getY(), (int) tmp.getWidth(), (int) tmp.getHeight(), Color.RED, null);
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

            switch (e.getKeyChar()) {
                case 'd':
                    platform.dir = Directions.RIGHT;
                    break;
                case 'a':
                    platform.dir = Directions.LEFT;
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
