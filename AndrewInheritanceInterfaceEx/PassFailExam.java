
/**
 * Write a description of class PassFailExam here.
 *
 * @author (Andrew Bae)
 * @version (9/12/24)
 */
public class PassFailExam extends PassFailActivity {
    private int numQuestions;
    private double pointsEach;
    private int numMissed;

    
    public PassFailExam(int numQuestions, int numMissed, double minPassingScore) {
        //call the constructor of PassFailActivity to set the minimum passing score
        super(minPassingScore);

        this.numQuestions = numQuestions;
        this.numMissed = numMissed;

        //calculate the points for each question
        this.pointsEach = 100.0 / numQuestions;

        //calculate the exam score
        double score = 100.0 - (numMissed * pointsEach);

        //set the score using the inherited setScore method from GradeActivity
        setScore(score);
    }

    public double getPointsEach() {
        return pointsEach;
    }

    public int getNumMissed() {
        return numMissed;
    }
}

