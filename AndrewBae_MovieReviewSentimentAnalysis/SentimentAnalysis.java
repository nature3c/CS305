import java.io.*;
import java.util.*;

public class SentimentAnalysis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Calculate sentiment for a single word");
            System.out.println("2. Calculate sentiment for words in a file");
            System.out.println("3. Find most positive and negative words in a file");
            System.out.println("4. Sort words into positive and negative files");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> calculateWordSentiment(scanner);
                case 2 -> calculateFileSentiment(scanner);
                case 3 -> findMostPositiveAndNegative(scanner);
                case 4 -> sortWordsIntoFiles(scanner);
                case 5 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    //calculate sentiment for a single word
    private static void calculateWordSentiment(Scanner scanner) {
        System.out.print("Enter a word: ");
        String word = scanner.nextLine().toLowerCase();
        int totalScore = 0;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("movieReviews.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                int score = Integer.parseInt(parts[0]);
                String reviewText = parts[1].toLowerCase();
                
                if (reviewText.contains(word)) {
                    totalScore += score;
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading reviews file.");
        }

        if (count > 0) {
            double avgScore = (double) totalScore / count;
            System.out.printf("Average sentiment score for '%s': %.2f - %s%n", word, avgScore, getSentimentLabel(avgScore));
        } else {
            System.out.println("Word not found in any reviews.");
        }
    }

    //calculate sentiment for words in a file
    private static void calculateFileSentiment(Scanner scanner) {
        System.out.print("Enter filename with words: ");
        String filename = scanner.nextLine();
        int totalScore = 0;
        int wordCount = 0;

        try (BufferedReader wordReader = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = wordReader.readLine()) != null) {
                word = word.toLowerCase();
                int[] result = calculateWordScore(word);
                totalScore += result[0];
                wordCount += result[1];
            }
        } catch (IOException e) {
            System.out.println("Error reading words file.");
        }

        if (wordCount > 0) {
            double avgScore = (double) totalScore / wordCount;
            System.out.printf("Average sentiment score: %.2f - %s%n", avgScore, getSentimentLabel(avgScore));
        } else {
            System.out.println("No words found in the reviews.");
        }
    }

    //find most positive and negative words in a file
    private static void findMostPositiveAndNegative(Scanner scanner) {
        System.out.print("Enter filename with words: ");
        String filename = scanner.nextLine();
        String mostPositive = null, mostNegative = null;
        double highestScore = -1, lowestScore = 5;

        try (BufferedReader wordReader = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = wordReader.readLine()) != null) {
                word = word.toLowerCase();
                int[] result = calculateWordScore(word);
                double avgScore = result[1] > 0 ? (double) result[0] / result[1] : -1;

                if (avgScore > highestScore) {
                    highestScore = avgScore;
                    mostPositive = word;
                }
                if (avgScore >= 0 && avgScore < lowestScore) {
                    lowestScore = avgScore;
                    mostNegative = word;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading words file.");
        }

        System.out.printf("Most Positive Word: %s with score %.2f - %s%n", mostPositive, highestScore, getSentimentLabel(highestScore));
        System.out.printf("Most Negative Word: %s with score %.2f - %s%n", mostNegative, lowestScore, getSentimentLabel(lowestScore));
    }

    //sort words into positive and negative files
    private static void sortWordsIntoFiles(Scanner scanner) {
        System.out.print("Enter filename with words: ");
        String filename = scanner.nextLine();

        try (BufferedReader wordReader = new BufferedReader(new FileReader(filename));
             BufferedWriter posWriter = new BufferedWriter(new FileWriter("positive.txt"));
             BufferedWriter negWriter = new BufferedWriter(new FileWriter("negative.txt"))) {

            String word;
            while ((word = wordReader.readLine()) != null) {
                word = word.toLowerCase();
                int[] result = calculateWordScore(word);
                double avgScore = result[1] > 0 ? (double) result[0] / result[1] : -1;

                if (avgScore > 2.1) posWriter.write(word + "\n");
                else if (avgScore < 1.9) negWriter.write(word + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error processing word file.");
        }

        System.out.println("Words sorted into positive.txt and negative.txt.");
    }

    //helper method to calculate the score and count for a specific word
    private static int[] calculateWordScore(String word) {
        int totalScore = 0;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("movieReviews.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                int score = Integer.parseInt(parts[0]);
                String reviewText = parts[1].toLowerCase();

                if (reviewText.contains(word)) {
                    totalScore += score;
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading reviews file.");
        }
        return new int[]{totalScore, count};
    }

    //helper method to get sentiment label based on average score
    private static String getSentimentLabel(double score) {
        if (score < 1.0) return "Negative";
        else if (score < 2.0) return "Somewhat Negative";
        else if (score < 3.0) return "Neutral";
        else if (score < 4.0) return "Somewhat Positive";
        else return "Positive";
    }
}
