import java.util.Iterator;
import java.util.Random;

public class CollisionDetection {

    Platform platform;
    double[] levels = new double[3];
    Ball ball;
    public CollisionDetection(Platform platform, Ball ball) {

        this.platform = platform;
        this.ball = ball;
        levels[0] = platform.getX();
        levels[1] = (platform.getX()) + (double) 1/3 * platform.getWidth();
        System.out.println(levels[1]);
        levels[2] = (platform.getX())+ (double) 2/3 * platform.getWidth();
    }

    public void check() {

        Iterator<Brick> brickIterator = Draw.bf.bricks.iterator();
        Iterator<Powerup> powerupIterator = Draw.powerups.iterator();


        if(platform.getX() <= 0 || platform.getX() + platform.getWidth() >= Window.windowDimension.width - Window.insets.get("RIGHT")) {
            Main.gameOver = true;
        }

        if(platform.getY() == ball.getY() + ball.getHeight() && platform.getX() <= ball.getX() &&  platform.getX()+platform.getWidth() >= ball.getX() + ball.getWidth()) {
            ball.dir[1] = Directions.UP;
        }

        if(ball.getX() + ball.getWidth() >= Window.windowDimension.getWidth() - Window.insets.get("HORIZONTAL")) {
            ball.dir[0] = Directions.LEFT;
            bounce(ball.getX(), ball.getY());
        }
        if(ball.getX() <= 0) {
            ball.dir[0] = Directions.RIGHT;
            bounce(ball.getX(), ball.getY());
        }


        while (brickIterator.hasNext()) {
            Brick b = brickIterator.next();
            if(ball.getX() >= b.getX() && ball.getX() + ball.getWidth() <= b.getX() + b.getWidth() && b.getY() + b.getHeight() == ball.getY()) {
                ball.dir[1] = Directions.DOWN;
                brickIterator.remove();
                brickCollision(b.getX(), ball.getY(), b.getWidth(), b.getHeight());
            }
        }

        while (powerupIterator.hasNext()) {
            Powerup tmp = powerupIterator.next();
            if(ball.getX() >= tmp.getX() && ball.getX() + ball.getWidth() <= tmp.getX() + tmp.getWidth() && ball.getY() <= tmp.getY() + tmp.getHeight() && ball.getY() >= tmp.getY()){
                powerupIterator.remove();
            }
        }

    }

    public void brickCollision(double x, double y, double width, double height) {
        Random rd = new Random();
        for(int i = 0; i < 100; i++) {
            Draw.particles.add(new Particle(rd.nextDouble(x, x + width), rd.nextDouble(y, y + height), 5, 5, true));
        }
    }

    public void wallCollision(double x, double y) {

        for(int i = 0; i < 40; i++) {
            Draw.particles.add(new Particle(x, y, 5, 5, false));
        }

    }

    public void bounce(double x, double y) {
        Draw.bounce.add(new Bounce(x, y,0, 0));
    }
}
