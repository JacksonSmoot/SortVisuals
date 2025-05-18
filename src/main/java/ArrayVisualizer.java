import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ArrayVisualizer extends JPanel {
    Dimension screenSize;
    public ArrayVisualizer(int width, int height) {
        screenSize = new Dimension(width, height);
    }

    public ArrayVisualizer() {
        screenSize = new Dimension(0, 0);
    }

    public void init(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // recreate canvas at the new size
                screenSize = new Dimension(getWidth(),getHeight());
                // you can also trigger a re‑draw of your data here
            }
        });
    }
}
