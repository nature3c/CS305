import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class SearchComparison {

    //global counters for number of comparisons
    static long randomListComparisons = 0;
    static long sortedListComparisons = 0;
    static long binarySearchComparisons = 0;

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000};
        long[] randomTimes = new long[sizes.length];
        long[] sortedTimes = new long[sizes.length];
        long[] binaryTimes = new long[sizes.length];
        long[] randomComparisons = new long[sizes.length];
        long[] sortedComparisons = new long[sizes.length];
        long[] binaryComparisons = new long[sizes.length];

        //table Header
        System.out.printf("%-10s %-25s %-25s %-25s%n", 
                "Size", 
                "Random Array", 
                "Sorted Array", 
                "Binary Search");
        System.out.printf("%-10s %-12s %-12s %-12s %-12s %-12s %-12s%n", 
                "", 
                "Time (ns)", 
                "Comparisons", 
                "Time (ns)", 
                "Comparisons", 
                "Time (ns)", 
                "Comparisons");

        //loop through each size
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            int[] randomArray = generateRandomArray(size);
            int[] sortedArray = randomArray.clone();
            Arrays.sort(sortedArray);

            //reset comparison counters
            randomListComparisons = 0;
            sortedListComparisons = 0;
            binarySearchComparisons = 0;

            //measure and count for Random Array (linear search)
            randomTimes[i] = measureSearchTime(randomArray, false, false);
            randomComparisons[i] = randomListComparisons;

            //measure and count for Sorted Array (linear search)
            sortedTimes[i] = measureSearchTime(sortedArray, false, true);
            sortedComparisons[i] = sortedListComparisons;

            //measure and count for Binary Search
            binaryTimes[i] = measureSearchTime(sortedArray, true, false);
            binaryComparisons[i] = binarySearchComparisons;

            //print results for the current size
            System.out.printf("%-10d %-12dns %-12d %-12dns %-12d %-12dns %-12d%n", 
                    size, 
                    randomTimes[i], randomComparisons[i], 
                    sortedTimes[i], sortedComparisons[i], 
                    binaryTimes[i], binaryComparisons[i]);
        }

        //visualization
        JFrame frame = new JFrame("Search Performance Visualization");
        PlotGraph graph = new PlotGraph(sizes, randomTimes, sortedTimes, binaryTimes);
        frame.add(graph);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //generate a random array
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100000);
        }
        return array;
    }

    //measure search time
    public static long measureSearchTime(int[] array, boolean useBinarySearch, boolean isSorted) {
        Random rand = new Random();
        int searchCount = 10; //perform 10 searches
        long startTime = System.nanoTime();

        for (int i = 0; i < searchCount; i++) {
            int target = rand.nextInt(100000);
            if (useBinarySearch) {
                binarySearch(array, target);
            } else {
                if (isSorted) {
                    linearSearchSorted(array, target); //use sorted comparison counter
                } else {
                    linearSearchRandom(array, target); //use random array comparison counter
                }
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    //linear search for random array
    public static int linearSearchRandom(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            randomListComparisons++; //increment comparison count for random array
            if (array[i] == target) {
                return i;
            }
        }
        return -1; //target not found
    }

    //linear search for sorted array
    public static int linearSearchSorted(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            sortedListComparisons++; //increment comparison count for sorted array
            if (array[i] == target) {
                return i;
            }
        }
        return -1; //target not found
    }

    //binary search
    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            binarySearchComparisons++;
            int mid = (low + high) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; //target not found
    }
}

//PlotGraph Class
class PlotGraph extends JPanel {
    int[] sizes;
    long[] randomTimes, sortedTimes, binaryTimes;

    public PlotGraph(int[] sizes, long[] randomTimes, long[] sortedTimes, long[] binaryTimes) {
        this.sizes = sizes;
        this.randomTimes = randomTimes;
        this.sortedTimes = sortedTimes;
        this.binaryTimes = binaryTimes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int margin = 50;

        //normalize data for plotting
        long maxTime = Math.max(Math.max(Arrays.stream(randomTimes).max().getAsLong(), 
                                         Arrays.stream(sortedTimes).max().getAsLong()), 
                                Arrays.stream(binaryTimes).max().getAsLong());

        //draw axes
        g.drawLine(margin, height - margin, width - margin, height - margin); // X-axis
        g.drawLine(margin, margin, margin, height - margin); // Y-axis

        //labels
        g.drawString("Size", width / 2, height - 10);
        g.drawString("Time (ns)", 10, height / 2);

        //plot lines and points
        for (int i = 0; i < sizes.length - 1; i++) {
            int x1 = margin + i * (width - 2 * margin) / (sizes.length - 1);
            int x2 = margin + (i + 1) * (width - 2 * margin) / (sizes.length - 1);

            //normalize times for Y-axis
            int randomY1 = height - margin - (int) (randomTimes[i] * (height - 2 * margin) / maxTime);
            int randomY2 = height - margin - (int) (randomTimes[i + 1] * (height - 2 * margin) / maxTime);

            int sortedY1 = height - margin - (int) (sortedTimes[i] * (height - 2 * margin) / maxTime);
            int sortedY2 = height - margin - (int) (sortedTimes[i + 1] * (height - 2 * margin) / maxTime);

            int binaryY1 = height - margin - (int) (binaryTimes[i] * (height - 2 * margin) / maxTime);
            int binaryY2 = height - margin - (int) (binaryTimes[i + 1] * (height - 2 * margin) / maxTime);

            //draw lines
            g.setColor(Color.RED);
            g.drawLine(x1, randomY1, x2, randomY2);

            g.setColor(Color.BLUE);
            g.drawLine(x1, sortedY1, x2, sortedY2);

            g.setColor(Color.GREEN);
            g.drawLine(x1, binaryY1, x2, binaryY2);
        }
    }
}
