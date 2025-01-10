
/**
 * Write a description of class Essay here.
 *
 * @author (Andrew Bae)
 * @version (9/12/24)
 */
public class Essay extends GradeActivity {
    private double grammar;
    private double spelling;
    private double correctLength;
    private double content;

    public void setScore(double grammar, double spelling, double correctLength, double content) {
        this.grammar = grammar;
        this.spelling = spelling;
        this.correctLength = correctLength;
        this.content = content;

        //total score is the sum of all parts
        double totalScore = grammar + spelling + correctLength + content;
        super.setScore(totalScore);  //calls the setScore method from GradeActivity
    }

    public double getGrammar() {
        return grammar;
    }

    public void setGrammar(double grammar) {
        this.grammar = grammar;
    }

    public double getSpelling() {
        return spelling;
    }

    public void setSpelling(double spelling) {
        this.spelling = spelling;
    }

    public double getCorrectLength() {
        return correctLength;
    }

    public void setCorrectLength(double correctLength) {
        this.correctLength = correctLength;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }
}
