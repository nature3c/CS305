
/**
 * Write a description of class Sorting here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;

public class Sorting {

    //bubble sort with time and comparison tracking
    public static long bubbleSort(int[] arr) {
        int comparisons = 0;
        long startTime = System.nanoTime();

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; //convert to milliseconds
        //System.out.println("Bubble Sort Comparisons: " + comparisons);
        return duration;
    }

    //selection sort with time and comparison tracking
    public static long selectionSort(int[] arr) {
        int comparisons = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;  
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; //convert to milliseconds
        //System.out.println("Selection Sort Comparisons: " + comparisons);
        return duration;
    }

    //insertion sort with time and comparison tracking
    public static long insertionSort(int[] arr) {
        int comparisons = 0;
        long startTime = System.nanoTime();

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j;
            comparisons++;
            for (j = i; j > 0; j--) {
                if (arr[j - 1] < tmp) {
                    break;
                }
                comparisons++;
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
        //System.out.println("Insertion Sort Comparisons: " + comparisons);
        return duration;
    }

    //swap method used in sorting algorithms
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //method to generate an array of random integers
    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] dataSizes = {500, 10000, 50000};

        System.out.println("Time analysis for the sorting algorithms:");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Data Size   Bubble Sort (ms)   Selection Sort (ms)   Insertion Sort (ms)");
        System.out.println("--------------------------------------------------------------------------");

        for (int size : dataSizes) {
            int[] arrBubble = generateRandomArray(size);
            int[] arrSelection = arrBubble.clone(); // To use same data for selection sort
            int[] arrInsertion = arrBubble.clone(); // To use same data for insertion sort

            long bubbleTime = bubbleSort(arrBubble);
            long selectionTime = selectionSort(arrSelection);
            long insertionTime = insertionSort(arrInsertion);

            System.out.printf("%-10d %-18d %-20d %-18d\n", size, bubbleTime, selectionTime, insertionTime);
        }

        System.out.println("--------------------------------------------------------------------------");
    }
}
