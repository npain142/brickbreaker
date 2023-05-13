import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Window extends JFrame {

    public static Dimension windowDimension = new Dimension(750, 500);
    public static HashMap<String, Integer> insets = new HashMap<String, Integer>();
    public Window() {

        this.setSize(windowDimension);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);

        setInsets();
    }

    public void setInsets() {
        insets.put("BOTTOM", this.getInsets().bottom);
        insets.put("TOP", this.getInsets().top);
        insets.put("LEFT", this.getInsets().left);
        insets.put("RIGHT", this.getInsets().right);
        insets.put("HORIZONTAL", (this.getInsets().right) + (this.getInsets().left));
        insets.put("VERTICAL", (this.getInsets().top) + (this.getInsets().bottom));
    }
}
