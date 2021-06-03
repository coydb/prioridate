package prioridate;

import java.util.ArrayList;

public class Student 
{
    private String name;
    private double gradePercent;
    private double completionPercent;
    protected ArrayList<Assignment> assignmentsCompleted;
    protected ArrayList<Assignment> assignmentsDue;

    private void viewAssignmentsCompleted(Assignment assignmentCompleted)
    {

    }

    private void viewAssignmentsDue(Assignment assignmentsDue)
    {

    }

    public double calculateCompletionPercent(Assignment assignmentsDue)
    {
        double percent = 100.0;
        return percent;
    }

    public double calculateGradePercent(Assignment assignmentsDue, Assignment assignmentsCompleted)
    {
        double percent = 100.0;
        return percent;
    }

    public void checkOffAssignment(Assignment assignmentsDue)
    {
        
    }

}
