package prioridate;

import java.util.ArrayList;

public class Student 
{
    private String name;
    private double gradePercent;
    private double completionPercent;
    protected ArrayList<Assignment> assignmentsCompleted;
    protected ArrayList<Assignment> assignmentsDue;

    public Student()
    {
        this.name = name;
        this.gradePercent = gradePercent;
        this.completionPercent = completionPercent;
        this.assignmentsCompleted = new ArrayList<Assignment>();
        this.assignmentsDue = new ArrayList<Assignment>();
    }

    private void viewAssignmentsCompleted(Assignment assignmentCompleted)
    {

    }

    private void viewAssignmentsDue(Assignment assignmentsDue)
    {

    }

    public double calculateCompletionPercent(Assignment assignmentsDue)
    {
        return this.completionPercent;
    }

    public double calculateGradePercent(Assignment assignmentsDue, Assignment assignmentsCompleted)
    {
        return this.gradePercent;
    }

    public void checkOffAssignment(Assignment assignmentsDue)
    {
        
    }

}