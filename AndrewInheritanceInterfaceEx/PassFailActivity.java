
/**
 * Write a description of class PassFailActivity here.
 *
 * @author (Andrew Bae)
 * @version (9/12/24)
 */

public class PassFailActivity extends GradeActivity {
    private double minPassingScore;

    //sets the minimum passing score
    public PassFailActivity(double minPassingScore) {
        this.minPassingScore = minPassingScore;
    }

    //return 'P' if the score is passing or 'F' if it is failing
    @Override
    public char getGrade() {
        if (getScore() >= minPassingScore) {
            return 'P';
        } else {
            return 'F';
        }
    }

    public double getMinPassingScore() {
        return minPassingScore;
    }
}
