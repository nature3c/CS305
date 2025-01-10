import java.io.*;
import java.util.*;

public class MatrixPrinter {
    public static void main(String[] args) {
        //specify the file path
        String filePath = "Colorado_844x480.dat";
        
        //define the dimensions of the matrix
        final int ROWS = 844;
        final int COLS = 480;

        //create a 2D array to store the matrix
        int[][] matrix = new int[ROWS][COLS];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                    } else {
                        throw new IOException("File does not contain enough data for the matrix.");
                    }
                }
            }

            //print the matrix
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
