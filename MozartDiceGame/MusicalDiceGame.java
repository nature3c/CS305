/**
 * Plays a Musikalisches Würfelspiel based off of the given WAV files
 *
 * @author (Andrew Bae)
 * @version (10/8/24)
 */

import java.util.Random;

public class MusicalDiceGame {

    //minuet section, 16 phrases with two dice rolls
    private static void playMinuet(String directory) {
        Random random = new Random();
        for (int i = 1; i < 17; i++) { //not 0-16 b/c there is no 0-x for the WAV files
            int diceRoll = random.nextInt(6) + 1 + random.nextInt(6) + 1; //two dice rolls (2-12)
            String fileName = "mozart/" + directory + "/minuet" + i + "-" + diceRoll + ".wav";
            StdAudio.play(fileName);
        }
    }

    //trio section,16 phrases with one dice roll
    private static void playTrio(String directory) {
        Random random = new Random();
        for (int i = 1; i < 17; i++) {
            int diceRoll = random.nextInt(6) + 1; //one dice roll (1-6)
            String fileName = "mozart/" + directory + "/trio" + i + "-" + diceRoll + ".wav";
            StdAudio.play(fileName);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MusicalDiceGame <directory>");
            return;
        }

        String directory = args[0]; //directory containing the WAV files

        //play the minuet section
        playMinuet(directory);

        //play the trio section
        playTrio(directory);
    }
}

