
/**
 * Write a description of class Tester here.
 *
 * @author (Andrew Bae)
 * @version (9/13/24)
 */
public class Tester {
    public static void main(String[] args) {
        //create instances of each graded activity
        GradeActivity labActivity = new GradeActivity();
        labActivity.setScore(85);  //EXAMPLE lab score

        PassFailExam passFailExam = new PassFailExam(10, 2, 70);  //10 questions, EXAMPLE 2 missed, 70 as min passing score

        Essay essay = new Essay();
        essay.setScore(30, 20, 20, 10);  //EXAMPLE scores for grammar, spelling, correct length, content

        FinalExam finalExam = new FinalExam(50, 5);  //EXAMPLE 50 questions, 5 missed

        //create an instance of CourseGrades
        CourseGrades courseGrades = new CourseGrades();
        courseGrades.setLab(labActivity);
        courseGrades.setPassFailExam(passFailExam);
        courseGrades.setEssay(essay);
        courseGrades.setFinalExam(finalExam);

        //print the report card using CourseGrades toString() method
        System.out.println("Your Report Card:");
        System.out.println(courseGrades.toString());  //print the formatted report card
    }
}
