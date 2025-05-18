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
        // setBackground(Color.WHITE);
        setBounds(0, 0, w, h);
        setLayout(null);
    }

    public void set(int[] sarr, int sarrMax, int sarrMin){
        this.arr = sarr;
        this.arrMax = sarrMax;
        this.arrMin = sarrMin;
        // repaint();
    }

//    @Override
//    public void addNotify() {
//        super.addNotify();
//        displayable = true;
//        start();
//    }

//    public void start(){
//        System.out.println("Starting Visualizer");
//        int[] arr1 = makeArray(1000);
//        arr = arr1.clone();
//        arrMax = getMax(arr);
//        bubbleSort(arr);
//        System.out.println("BubbleSort Done");
//        repaint();
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // if(arr!=null){
        if(arr!=null){
            paintArray(g,arr,arrMax,arrMin);
        }
        // }
    }

    private void paintArray(Graphics g, int[] a, int max, int min){
        // System.out.println("Painting Array");
        // g.setColor(Color.black);
        // g.fillRect(0,0, screenSize.width, screenSize.height);
//        for(int i=0;i<a.length;i++){
//            System.out.print(a[i]+" | ");
//        }
        Graphics2D g2d = (Graphics2D) g;
        double step = (double)screenSize.width/a.length;
        double scale = (double)screenSize.height/a.length;
        for(int i = 0; i < a.length; i++){
            int colorValue = 255-mapToByteRange(a[i], min, max);
            // System.out.println(a[i]+" : "+colorValue);
            g2d.setColor(new Color(colorValue, colorValue, colorValue, 255));
            // g2d.fillRect((screenSize.width/a.length)*i, 0, screenSize.width/a.length, screenSize.height);
            int myHeight = mapToRange(arr[i],min,max,6,screenSize.height);
            int myY = screenSize.height-myHeight;
            g2d.fillRect((int)(step*i), myY, ((int)step==0) ? 1 : (int)step, myHeight);
        }
        // System.out.println("Done Painting Array with Step: "+step+", Screen Width: "+screenSize.width+", Array Length: "+a.length);
    }

    public static int mapToRange(int value, int min, int max, int rangeMin, int rangeMax) {
        if (value <= min)   return rangeMin;
        if (value >= max)   return rangeMax;
        // normalize to 0.0–1.0
        double norm = (value - min) / (double)(max - min);
        // scale to 0–255 and round
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

//    public void bubbleSort(int[] arr) {
//        int n = arr.length;
//        int numRun = 0;
//        boolean swapped;
//        for (int i = 0; i < n - 1; i++) {
//            swapped = false;
//            for (int j = 0; j < n - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    int tmp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = tmp;
//                    swapped = true;
//                }
//            }
//            try{
//                Thread.sleep(300);
//            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            super.repaint();
//            // paintArray(this.getGraphics(), arr, arr1Max);
//            if (!swapped) break;
//        }
//        super.repaint();
//        // paintArray(this.getGraphics(), arr, max);
//    }
}
