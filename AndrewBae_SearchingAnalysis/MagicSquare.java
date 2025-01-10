public class MagicSquare {
    public static void main(String[] args) {
        int n = 5; //size of the magic square 
        if (n % 2 == 0) {
            System.out.println("This algorithm works only for odd-sized magic squares.");
            return;
        }

        int[][] magicSquare = new int[n][n];

        //start position
        int row = n - 1;
        int col = n / 2;

        for (int k = 1; k <= n * n; k++) {
            magicSquare[row][col] = k;

            //calculate next position
            int nextRow = (row + 1) % n; //wrap around downward
            int nextCol = (col + 1) % n; //wrap around rightward

            if (magicSquare[nextRow][nextCol] != 0) {
                //if the next position is filled then move straight up
                nextRow = (row - 1 + n) % n;
                nextCol = col;
            }

            row = nextRow;
            col = nextCol;
        }

        //print the magic square
        printMagicSquare(magicSquare);
    }

    private static void printMagicSquare(int[][] magicSquare) {
        for (int[] row : magicSquare) {
            for (int value : row) {
                System.out.printf("%4d", value); //format the output for better alignment
            }
            System.out.println();
        }
    }
}
