
/**
 * Write a description of class FinalExam here.
 *
 * @author (Andrew Bae)
 * @version (9/12/24)
 */
public class FinalExam extends GradeActivity {
    private int numQuestions;
    private double pointsEach;
    private int numMissed;

    //constructor that sets the number of questions and missed questions and calculates the score
    public FinalExam(int numQuestions, int numMissed) {
        this.numQuestions = numQuestions;
        this.numMissed = numMissed;

        //points for each question
        this.pointsEach = 100.0 / numQuestions;

        //final score
        double score = 100.0 - (numMissed * pointsEach);

        //set the score in GradeActivity using the inherited setScore method
        setScore(score);
    }

    public double getPointsEach() {
        return pointsEach;
    }

    public int getNumMissed() {
        return numMissed;
    }
}

