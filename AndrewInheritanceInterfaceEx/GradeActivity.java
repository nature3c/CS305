
/**
 * contains score: double
 * +setScore(s: double): void
 * +getScore(): double
 * +getGrade(): char
 *
 * @author (Andrew Bae)
 * @version (9/12/24)
 */
public class GradeActivity {
    private double score;

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public char getGrade() {
        char grade;

        if (score >= 90) {
            grade = 'A';
        } else if (score >= 80) {
            grade = 'B';
        } else if (score >= 70) {
            grade = 'C';
        } else if (score >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        return grade;
    }
}
