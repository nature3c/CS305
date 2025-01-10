import java.util.Random;

public class SticksGame {
    public static void main(String[] args) {
        int totalIterations = 1000;
        int maxSticks = 27;
        Random random = new Random();

        //table to record bot B's moves when it wins
        int[][] botBMoves = new int[maxSticks + 1][3]; //0-27 for sticks, 3 columns for choices

        for (int iteration = 0; iteration < totalIterations; iteration++) {
            int sticks = maxSticks;
            boolean isBotBTurn = random.nextBoolean(); //randomly decide who starts

            while (sticks > 0) {
                int chosenSticks;
                if (isBotBTurn) {
                    //bot B turn
                    chosenSticks = chooseSticks(sticks, random);
                    if (sticks - chosenSticks > 0) {
                        //record the move if it's not the last stick
                        botBMoves[sticks][chosenSticks - 1]++;
                    }
                    sticks -= chosenSticks;
                    if (sticks == 0) {
                        //bot B lost so skip recording the moves
                        break;
                    }
                } else {
                    //bot A turn
                    chosenSticks = chooseSticks(sticks, random);
                    sticks -= chosenSticks;
                    if (sticks == 0) {
                        //Bot B wins record the move
                        break;
                    }
                }
                //switch turn
                isBotBTurn = !isBotBTurn;
            }
        }

        //print the results table
        System.out.printf("%-10s%-10s%-10s%-10s%n", "Sticks", "1", "2", "3");
        for (int i = 0; i <= maxSticks; i++) {
            System.out.printf("%-10d%-10d%-10d%-10d%n", i, botBMoves[i][0], botBMoves[i][1], botBMoves[i][2]);
        }
    }

    //function to choose sticks randomly but no more than the current number of sticks
    public static int chooseSticks(int sticks, Random random) {
        return random.nextInt(Math.min(3, sticks)) + 1;
    }
}
