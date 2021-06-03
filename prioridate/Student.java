package prioridate;

import java.util.ArrayList;
import java.util.HashMap;

public class Student 
{
    private int studentId;
    private String studentName;
    private HashMap<Boolean, Assignment> assignments;

    public ArrayList<String> TEST;

    public Student()
    {
        this.studentId = studentId;
        this.studentName = studentName;
        this.assignments = new HashMap<Boolean, Assignment>();
        
        this.TEST = new ArrayList<String>();
    }

    public void run()
    {
        TEST.add("assignment 1");
        TEST.add("assignment 2");
        TEST.add("assignment 3");
        viewAssignmentsCompleted(TEST);

    }

    private void viewAssignmentsCompleted(ArrayList<String> assignmentCompleted)
    {
        for(int i = 0; i < assignmentCompleted.size(); i++)
        {
            System.out.println((i+1) + ". " + assignmentCompleted.get(i));
        }
    }

    private void viewAssignmentsDue(ArrayList<Assignment> assignmentsDue)
    {

    }

    public double calculateCompletionPercent(ArrayList<Assignment> assignmentsDue, ArrayList<Assignment> assignmentsCompleted)
    {
        return 1.0;
    }

   public void checkOffAssignment(ArrayList<Assignment> assignmentsDue)
   {

   }

   public int getStudentId()
   {
        return this.studentId;
   }

   public String getStudentName()
   {
       return this.studentName;
   }

   public HashMap<Boolean, Assignment> getAssignments()
   {
       return this.assignments;
   }




    public static void main(String[] args)
    {
        Student student = new Student();
        student.run();
    }

}