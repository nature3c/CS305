
/**
 * Write a description of class CourseGrades here.
 *
 * @author (Andrew Bae)
 * @version (9/13/24)
 */
/**
 * The CourseGrades class represents the grades for different course components
 * and implements the Analyzable interface to provide statistical analysis of the grades.
 */
public class CourseGrades extends GradeActivity implements Analyzable {
    private GradeActivity[] grades;

    public CourseGrades() {
        grades = new GradeActivity[4];//initialize array with space for four graded activities
    }

    public void setLab(GradeActivity lab) {
        grades[0] = lab;
    }

    public void setPassFailExam(PassFailExam passFailExam) {
        grades[1] = passFailExam;
    }
    
    public void setEssay(Essay essay) {
        grades[2] = essay;
    }

    public void setFinalExam(FinalExam finalExam) {
        grades[3] = finalExam; 
    }

    //return the average score of the numeric scores stored in the grades array
    @Override
    public double getAverage() {
        double totalScore = 0;
        int count = 0;

        //iterate through the grades array to sum scores and count valid grades
        for (GradeActivity activity : grades) {
            if (activity != null) {
                totalScore += activity.getScore();  //add the score of each valid activity
                count++;
            }
        }
        return count > 0 ? totalScore / count : 0;  //return average or 0 if no valid grades
    }

    //finds and returns the GradeActivity with the highest score
    @Override
    public GradeActivity getHighest() {
        GradeActivity highest = null;

        //iterate through the grades array to find the highest score
        for (GradeActivity activity : grades) {
            if (activity != null && (highest == null || activity.getScore() > highest.getScore())) {
                highest = activity;  //update highest if current activity has a higher score
            }
        }
        return highest;
    }

    //finds and returns the GradeActivity with the lowest score.
    @Override
    public GradeActivity getLowest() {
        GradeActivity lowest = null;

        //iterate through the grades array to find the lowest score
        for (GradeActivity activity : grades) {
            if (activity != null && (lowest == null || activity.getScore() < lowest.getScore())) {
                lowest = activity;  //update lowest if current activity has a lower score
            }
        }
        return lowest;
    }
    
    //return a formatted string containing the details of the grades and analysis.
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        
        //append details for each graded activity
        result.append("Lab Score: ").append(grades[0].getScore())
              .append(", Grade: ").append(grades[0].getGrade()).append("\n");
        result.append("Pass/Fail Exam Score: ").append(grades[1].getScore())
              .append(", Grade: ").append(grades[1].getGrade()).append("\n");
        result.append("Essay Score: ").append(grades[2].getScore())
              .append(", Grade: ").append(grades[2].getGrade()).append("\n");
        result.append("Final Exam Score: ").append(grades[3].getScore())
              .append(", Grade: ").append(grades[3].getGrade()).append("\n");
        result.append("Average score: ").append(getAverage()).append("\n");
        result.append("Highest score: ").append(getHighest().getScore()).append("\n");
        result.append("Lowest score: ").append(getLowest().getScore()).append("\n");

        return result.toString();
    }
}




