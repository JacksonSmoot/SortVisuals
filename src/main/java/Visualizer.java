import javax.swing.*;
import java.awt.*;

public class Visualizer extends JPanel {
    // waiting stuffs

    Dimension screenSize;

    int[] arr;
    int arrMax;
    int arrMin;
    public Visualizer(int w, int h) {
        this. screenSize = new Dimension(w, h);
        this.setPreferredSize(screenSize);
        setBounds(0, 0, w, h);
        setLayout(null);
    }

    public void set(int[] sarr, int sarrMax, int sarrMin){
        this.arr = sarr;
        this.arrMax = sarrMax;
        this.arrMin = sarrMin;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(arr!=null){
            paintArray(g,arr,arrMax,arrMin);
        }
    }

    private void paintArray(Graphics g, int[] a, int max, int min){
        Graphics2D g2d = (Graphics2D) g;
        double step = (double)screenSize.width/a.length;
        double scale = (double)screenSize.height/a.length;
        for(int i = 0; i < a.length; i++){
            int colorValue = 255-mapToByteRange(a[i], min, max);
            g2d.setColor(new Color(colorValue, colorValue, colorValue, 255));
            int myHeight = mapToRange(arr[i],min,max,6,screenSize.height);
            int myY = screenSize.height-myHeight;
            g2d.fillRect((int)(step*i), myY, ((int)step==0) ? 1 : (int)step, myHeight);
        }
    }

    public static int mapToRange(int value, int min, int max, int rangeMin, int rangeMax) {
        if (value <= min)   return rangeMin;
        if (value >= max)   return rangeMax;
        double norm = (value - min) / (double)(max - min);
        return (int)Math.round(norm * rangeMax);
    }

    public static int mapToByteRange(int value, int min, int max) {
        return mapToRange(value, min, max, 0, 255);
    }

    public static int[] makeArray(int size){
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = (int)(Math.random()*1000000);
        }
        return array;
    }

    public static int getMax(int[] arr){
        int maxIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > arr[maxIndex]){
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    public static int getMin(int[] arr){
        int minIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < arr[minIndex]){
                minIndex = i;
            }
        }
        return arr[minIndex];
    }
}
