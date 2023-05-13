import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static boolean gameOver = false;

    public static void main(String[] args) throws IOException {
        startGame();
    }

    public static void startGame() throws IOException {
        Window window = new Window();
        Draw draw = new Draw();

        window.setContentPane(draw);
        draw.requestFocusInWindow();

        gameClock(draw);
    }

    public static void gameClock(Draw draw) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                draw.repaint();
                if(gameOver) {
                   timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5);
    }
}
