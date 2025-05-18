import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    // Toolkit.getDefaultToolkit().getScreenSize()
    Dimension screenSize;
    Visualizer visualizer;
    public Window(int w, int h) {
        super("Visualizer");
        visualizer = new Visualizer(w, h);
        this.add(visualizer);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = new Dimension(w, h);
        setSize(screenSize);
        setVisible(true);
        setResizable(false);
    }

    public void set(int[] sarr, int sarrMax, int sarrMin){
        visualizer.set(sarr, sarrMax, sarrMin);
    }
}
