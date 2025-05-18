import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static final int screenWidth = 800;
    public static final int screenHeight = 600;
    public static Window window = null;
    public static int arrMax = 0;
    public static int arrMin = 0;
    public static void main(String[] args) {
        window = new Window(screenWidth, screenHeight);
        Scanner scan = new Scanner(System.in);
        while(true){
            boolean ranSort = true;
            int[] arr = Visualizer.makeArray(10000);
            arrMax = Visualizer.getMax(arr);
            arrMin = Visualizer.getMin(arr);
            tryPaint(arr, arrMax, arrMin);
            System.out.println("0: exit");
            System.out.println("1: Bubble Sort");
            System.out.println("2: Selection Sort");
            System.out.println("3: Insertion Sort");
            System.out.println("4: Heap Sort");
            System.out.println("5: Quick Sort");
            System.out.println("6: Merge Sort");
            System.out.println("7: Shell Sort");
            int in = scan.nextInt();
            scan.nextLine();
            if(in==0){
                System.exit(0);
            }
            else if(in==1){
                bubbleSort(arr);
            }
            else if(in==2){
                selectionSort(arr);
            }
            else if(in==3){
                insertionSort(arr);
            }
            else if(in==4){
                heapSort(arr);
            }
            else if(in==5){
                quickSort(arr);
            }
            else if(in==6){
                mergeSort(arr);
            }
            else if(in==7){
                shellSort(arr);
            }
            else{
                System.out.println("Invalid Input");
                ranSort = false;
            }
            if(ranSort){
                System.out.println("Done Sorting! (enter to continue, 'e' to exit.)");
            }
            String wait = scan.nextLine();
            if(wait.equals("e")){
                System.exit(0);
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        int arrMax = Visualizer.getMax(arr);
        int arrMin = Visualizer.getMin(arr);
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            tryPaint(arr, arrMax, arrMin);
            if (!swapped) break;
        }
        window.repaint();
        window.set(arr,arrMax,arrMin);
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int arrMax = Visualizer.getMax(arr);
        int arrMin = Visualizer.getMin(arr);
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
            tryPaint(arr, arrMax, arrMin);
        }
    }

    public static void tryPaint(int[] arr, int max, int min){
        try{
            Thread.sleep(1);
        }
        catch (InterruptedException e){
            System.err.println("Sleep (500) Interrupted: "+e.getMessage());
        }
        window.set(arr,max,min);
        window.repaint();
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            tryPaint(arr, arrMax, arrMin);
        }
    }

    public static void mergeSort(int[] arr) {
        tryPaint(arr, arrMax, arrMin);
        if (arr.length < 2) return;
        mergeSort(arr, 0, arr.length - 1);
    }
    private static void mergeSort(int[] arr, int left, int right) {
        tryPaint(arr, arrMax, arrMin);
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        int[] L = new int[n1], R = new int[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int low, int high) {
        tryPaint(arr, arrMax, arrMin);
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = tmp;
        tryPaint(arr, arrMax, arrMin);
        return i + 1;
    }

    // 6. Heap Sort
    public static void heapSort(int[] arr) {
        tryPaint(arr, arrMax, arrMin);
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0]; arr[0] = arr[i]; arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }
    private static void heapify(int[] arr, int heapSize, int rootIdx) {
        tryPaint(arr, arrMax, arrMin);
        int largest = rootIdx;
        int left = 2 * rootIdx + 1;
        int right = 2 * rootIdx + 2;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != rootIdx) {
            int tmp = arr[rootIdx]; arr[rootIdx] = arr[largest]; arr[largest] = tmp;
            heapify(arr, heapSize, largest);
        }
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            tryPaint(arr, arrMax, arrMin);
            for (int i = gap; i < n; i++) {
                tryPaint(arr, arrMax, arrMin);
                int temp = arr[i], j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}
